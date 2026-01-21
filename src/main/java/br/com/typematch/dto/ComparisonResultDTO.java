package br.com.typematch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ComparisonResultDTO {

    public static class PokemonView {
        private int id;
        private String name;
        private List<String> types;
        private int totalStats;

        public PokemonView() {}

        public PokemonView(int id, String name, List<String> types, int totalStats) {
            this.id = id;
            this.name = name;
            this.types = types;
            this.totalStats = totalStats;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public List<String> getTypes() { return types; }
        public int getTotalStats() { return totalStats; }

        public void setId(int id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setTypes(List<String> types) { this.types = types; }
        public void setTotalStats(int totalStats) { this.totalStats = totalStats; }
    }

    public static class TypeDetail {
        private String attackerType;
        private List<String> defenderTypes;
        private double multiplier;

        public TypeDetail() {}

        public TypeDetail(String attackerType, List<String> defenderTypes, double multiplier) {
            this.attackerType = attackerType;
            this.defenderTypes = defenderTypes;
            this.multiplier = multiplier;
        }

        public String getAttackerType() { return attackerType; }
        public List<String> getDefenderTypes() { return defenderTypes; }
        public double getMultiplier() { return multiplier; }

        public void setAttackerType(String attackerType) { this.attackerType = attackerType; }
        public void setDefenderTypes(List<String> defenderTypes) { this.defenderTypes = defenderTypes; }
        public void setMultiplier(double multiplier) { this.multiplier = multiplier; }
    }

    public static class TypeBreakdown {
        @JsonProperty("aToB")
        private double aToB;
        @JsonProperty("bToA")
        private double bToA;

        private List<TypeDetail> detailsAtoB = new ArrayList<>();
        private List<TypeDetail> detailsBtoA = new ArrayList<>();

        public double getAToB() { return aToB; }
        public double getBToA() { return bToA; }
        public List<TypeDetail> getDetailsAtoB() { return detailsAtoB; }
        public List<TypeDetail> getDetailsBtoA() { return detailsBtoA; }

        public void setAToB(double aToB) { this.aToB = aToB; }
        public void setBToA(double bToA) { this.bToA = bToA; }
        public void setDetailsAtoB(List<TypeDetail> detailsAtoB) { this.detailsAtoB = detailsAtoB; }
        public void setDetailsBtoA(List<TypeDetail> detailsBtoA) { this.detailsBtoA = detailsBtoA; }
    }

    public static class StatsBreakdown {
        private StatsDTO a;
        private StatsDTO b;
        private int totalA;
        private int totalB;

        public StatsDTO getA() { return a; }
        public StatsDTO getB() { return b; }
        public int getTotalA() { return totalA; }
        public int getTotalB() { return totalB; }

        public void setA(StatsDTO a) { this.a = a; }
        public void setB(StatsDTO b) { this.b = b; }
        public void setTotalA(int totalA) { this.totalA = totalA; }
        public void setTotalB(int totalB) { this.totalB = totalB; }
    }

    public static class Result {
        private String winner;
        private double probabilityA;
        private double probabilityB;
        private String rationale;

        private double typeScoreA;
        private double statsScoreA;

        public String getWinner() { return winner; }
        public double getProbabilityA() { return probabilityA; }
        public double getProbabilityB() { return probabilityB; }
        public String getRationale() { return rationale; }
        public double getTypeScoreA() { return typeScoreA; }
        public double getStatsScoreA() { return statsScoreA; }

        public void setWinner(String winner) { this.winner = winner; }
        public void setProbabilityA(double probabilityA) { this.probabilityA = probabilityA; }
        public void setProbabilityB(double probabilityB) { this.probabilityB = probabilityB; }
        public void setRationale(String rationale) { this.rationale = rationale; }
        public void setTypeScoreA(double typeScoreA) { this.typeScoreA = typeScoreA; }
        public void setStatsScoreA(double statsScoreA) { this.statsScoreA = statsScoreA; }
    }

    private PokemonView pokemonA;
    private PokemonView pokemonB;
    private TypeBreakdown typeBreakdown;
    private StatsBreakdown stats;
    private Result result;

    public PokemonView getPokemonA() { return pokemonA; }
    public PokemonView getPokemonB() { return pokemonB; }
    public TypeBreakdown getTypeBreakdown() { return typeBreakdown; }
    public StatsBreakdown getStats() { return stats; }
    public Result getResult() { return result; }

    public void setPokemonA(PokemonView pokemonA) { this.pokemonA = pokemonA; }
    public void setPokemonB(PokemonView pokemonB) { this.pokemonB = pokemonB; }
    public void setTypeBreakdown(TypeBreakdown typeBreakdown) { this.typeBreakdown = typeBreakdown; }
    public void setStats(StatsBreakdown stats) { this.stats = stats; }
    public void setResult(Result result) { this.result = result; }
}