package br.com.typematch.dto;

public class StatsDTO {
    private int specialDefense;
    private int defense;
    private int attack;
    private int hp;
    private int specialAttack;
    private int speed;

    public StatsDTO() {}

    public int getSpecialDefense() { return specialDefense; }
    public void setSpecialDefense(int specialDefense) { this.specialDefense = specialDefense; }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getSpecialAttack() { return specialAttack; }
    public void setSpecialAttack(int specialAttack) { this.specialAttack = specialAttack; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public int total() {
        return specialDefense + defense + attack + hp + specialAttack + speed;
    }
}