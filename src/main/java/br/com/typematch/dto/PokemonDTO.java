package br.com.typematch.dto;

import java.util.List;

public class PokemonDTO {
    private int id;
    private String name;
    private List<String> types;
    private int height;
    private int weight;
    private String spriteUrl;
    private StatsDTO stats;

    public PokemonDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getTypes() { return types; }
    public void setTypes(List<String> types) { this.types = types; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public String getSpriteUrl() { return spriteUrl; }
    public void setSpriteUrl(String spriteUrl) { this.spriteUrl = spriteUrl; }

    public StatsDTO getStats() { return stats; }
    public void setStats(StatsDTO stats) { this.stats = stats; }
}