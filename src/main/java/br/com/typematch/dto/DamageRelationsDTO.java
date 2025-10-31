package br.com.typematch.dto;

import java.util.List;

public class DamageRelationsDTO {
    private List<NamedApiDTO> double_damage_to;
    private List<NamedApiDTO> half_damage_to;
    private List<NamedApiDTO> no_damage_to;

    public DamageRelationsDTO() {}

    public List<NamedApiDTO> getDouble_damage_to() { return double_damage_to; }
    public List<NamedApiDTO> getHalf_damage_to() { return half_damage_to; }
    public List<NamedApiDTO> getNo_damage_to() { return no_damage_to; }

    public void setDouble_damage_to(List<NamedApiDTO> double_damage_to) { this.double_damage_to = double_damage_to; }
    public void setHalf_damage_to(List<NamedApiDTO> half_damage_to) { this.half_damage_to = half_damage_to; }
    public void setNo_damage_to(List<NamedApiDTO> no_damage_to) { this.no_damage_to = no_damage_to; }
}