package br.com.typematch.dto;

public class PokeApiTypeResponseDTO {
    private int id;
    private String name;
    private DamageRelationsDTO damage_relations;

    public PokeApiTypeResponseDTO() {}

    public int getId() { return id; }
    public String getName() { return name; }
    public DamageRelationsDTO getDamage_relations() { return damage_relations; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDamage_relations(DamageRelationsDTO damage_relations) { this.damage_relations = damage_relations; }
}
