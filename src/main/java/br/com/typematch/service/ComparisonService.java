package br.com.typematch.service;

import br.com.typematch.dto.ComparisonResultDTO;
import br.com.typematch.dto.PokemonDTO;
import br.com.typematch.dto.StatsDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ComparisonService {

    private final PokedexClientService pokedexClient;
    private final DynamicTypeEffectService typeService;

    public ComparisonService(PokedexClientService pokedexClient, DynamicTypeEffectService typeService) {
        this.pokedexClient = pokedexClient;
        this.typeService = typeService;
    }

    public ComparisonResultDTO compare(String idOrNameA, String idOrNameB) {
        PokemonDTO a = pokedexClient.getPokemon(idOrNameA);
        PokemonDTO b = pokedexClient.getPokemon(idOrNameB);

        if (a == null || b == null) {
            throw new IllegalArgumentException("Pokémon não encontrado em um dos parâmetros.");
        }

        List<String> aTypes = normalizeTypes(a.getTypes());
        List<String> bTypes = normalizeTypes(b.getTypes());

        double aToB = typeService.effectivenessAgainst(aTypes, bTypes);
        double bToA = typeService.effectivenessAgainst(bTypes, aTypes);

        var detailsA = new ArrayList<ComparisonResultDTO.TypeDetail>();
        for (String atk : aTypes) {
            double mult = typeService.effectivenessAgainst(List.of(atk), bTypes);
            detailsA.add(new ComparisonResultDTO.TypeDetail(atk, bTypes, mult));
        }
        var detailsB = new ArrayList<ComparisonResultDTO.TypeDetail>();
        for (String atk : bTypes) {
            double mult = typeService.effectivenessAgainst(List.of(atk), aTypes);
            detailsB.add(new ComparisonResultDTO.TypeDetail(atk, aTypes, mult));
        }

        int totalA = total(a.getStats());
        int totalB = total(b.getStats());

        double typeScoreA = portion(aToB, bToA);
        double statsScoreA = portion(totalA, totalB);

        double probA = 0.6 * typeScoreA + 0.4 * statsScoreA;
        double probB = 1.0 - probA;

        String winner = probA >= probB ? a.getName() : b.getName();
        String rationale = String.format(Locale.ROOT,
                "Vantagem de tipo: A→B=%.2fx, B→A=%.2fx; Totais de stats: A=%d, B=%d.",
                aToB, bToA, totalA, totalB);

        ComparisonResultDTO res = new ComparisonResultDTO();

        var pvA = new ComparisonResultDTO.PokemonView(a.getId(), a.getName(), a.getTypes(), totalA);
        var pvB = new ComparisonResultDTO.PokemonView(b.getId(), b.getName(), b.getTypes(), totalB);

        var tb = new ComparisonResultDTO.TypeBreakdown();
        tb.setAToB(aToB);
        tb.setBToA(bToA);
        tb.setDetailsAtoB(detailsA);
        tb.setDetailsBtoA(detailsB);

        var sb = new ComparisonResultDTO.StatsBreakdown();
        sb.setA(a.getStats());
        sb.setB(b.getStats());
        sb.setTotalA(totalA);
        sb.setTotalB(totalB);

        var r = new ComparisonResultDTO.Result();
        r.setWinner(winner);
        r.setProbabilityA(round2(probA));
        r.setProbabilityB(round2(probB));
        r.setRationale(rationale);

        res.setPokemonA(pvA);
        res.setPokemonB(pvB);
        res.setTypeBreakdown(tb);
        res.setStats(sb);
        res.setResult(r);

        return res;
    }

    private int total(StatsDTO s) {
        if (s == null) return 0;
        return s.getHp() + s.getAttack() + s.getDefense()
                + s.getSpecialAttack() + s.getSpecialDefense() + s.getSpeed();
    }

    private double portion(double a, double b) {
        double sum = a + b;
        if (sum <= 0.0) return 0.5;
        return a / sum;
    }

    private double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    private List<String> normalizeTypes(List<String> types) {
        if (types == null) return List.of();
        return types.stream().map(t -> t == null ? "" : t.toLowerCase(Locale.ROOT)).toList();
    }
}
