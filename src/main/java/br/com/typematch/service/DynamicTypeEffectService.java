package br.com.typematch.service;

import br.com.typematch.dto.NamedApiDTO;
import br.com.typematch.dto.PokeApiTypeResponseDTO;
import br.com.typematch.dto.TypeListResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DynamicTypeEffectService {

    private static final Logger log = LoggerFactory.getLogger(DynamicTypeEffectService.class);

    private final PokeApiTypeService service;

    private volatile Map<String, Map<String, Double>> chart = new ConcurrentHashMap<>();

    private static final Set<String> KNOWN_TYPES = Set.of(
            "normal","fire","water","electric","grass","ice",
            "fighting","poison","ground","flying","psychic",
            "bug","rock","ghost","dragon","dark","steel","fairy"
    );

    public DynamicTypeEffectService(PokeApiTypeService service) {
        this.service = service;
    }

    public void refreshAll() {
        log.info("Refreshing type effectiveness chart from PokeAPI...");
        Map<String, Map<String, Double>> newChart = new HashMap<>();
        try {
            TypeListResponseDTO list = service.listTypes(200);
            List<String> typeNames = Optional.ofNullable(list.getResults())
                    .orElse(List.of())
                    .stream()
                    .map(NamedApiDTO::getName)
                    .map(String::toLowerCase)
                    .filter(KNOWN_TYPES::contains)
                    .sorted()
                    .collect(Collectors.toList());

            for (String atk : typeNames) {
                PokeApiTypeResponseDTO typeResp = service.getType(atk);
                Map<String, Double> row = buildRowFromDamageRelations(typeResp);
                newChart.put(atk, row);
            }
            // Completa com 1.0 para ausentes
            for (String atk : typeNames) {
                Map<String, Double> row = newChart.get(atk);
                for (String def : typeNames) {
                    row.putIfAbsent(def, 1.0);
                }
            }
            this.chart = newChart;
            log.info("Type chart loaded for {} types.", newChart.size());
        } catch (Exception e) {
            log.warn("Failed to refresh type chart from PokeAPI. Keeping previous chart. Error: {}", e.getMessage());
        }
    }

    private Map<String, Double> buildRowFromDamageRelations(PokeApiTypeResponseDTO resp) {
        Map<String, Double> row = new HashMap<>();
        if (resp == null || resp.getDamage_relations() == null) return row;

        var dr = resp.getDamage_relations();

        if (dr.getDouble_damage_to() != null) {
            for (NamedApiDTO r : dr.getDouble_damage_to()) {
                String def = r.getName().toLowerCase();
                if (KNOWN_TYPES.contains(def)) row.put(def, 2.0);
            }
        }
        if (dr.getHalf_damage_to() != null) {
            for (NamedApiDTO r : dr.getHalf_damage_to()) {
                String def = r.getName().toLowerCase();
                if (KNOWN_TYPES.contains(def)) row.put(def, 0.5);
            }
        }
        if (dr.getNo_damage_to() != null) {
            for (NamedApiDTO r : dr.getNo_damage_to()) {
                String def = r.getName().toLowerCase();
                if (KNOWN_TYPES.contains(def)) row.put(def, 0.0);
            }
        }
        return row;
    }

    public double getMultiplier(String attackerType, String defenderType) {
        Map<String, Map<String, Double>> snapshot = this.chart;
        Map<String, Double> row = snapshot.get(attackerType.toLowerCase());
        if (row == null) return 1.0;
        return row.getOrDefault(defenderType.toLowerCase(), 1.0);
    }

    public boolean isLoaded() {
        return !chart.isEmpty();
    }

    public double effectivenessAgainst(List<String> attackerTypes, List<String> defenderTypes) {
        if (attackerTypes == null || attackerTypes.isEmpty() ||
                defenderTypes == null || defenderTypes.isEmpty()) {
            return 1.0;
        }

        if (chart.isEmpty()) {
            try {
                refreshAll();
            } catch (Exception e) {
                log.warn("Could not refresh type chart on demand: {}", e.getMessage());
            }
        }

        double max = 0.0;
        for (String atk : attackerTypes) {
            double product = 1.0;
            for (String def : defenderTypes) {
                product *= getMultiplier(atk, def);
            }
            if (product > max) max = product;
        }
        return max == 0.0 ? 1.0 : max;
    }
}