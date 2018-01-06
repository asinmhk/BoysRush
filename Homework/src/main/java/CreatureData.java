//package main;

public class CreatureData {
    private double life;

    private double damage;

    private double armor;

    private double cure;

    public CreatureData() { }

    public void init(double life, double damage, double armor) {
        this.life = life;
        this.damage = damage;
        this.armor = armor;
    }

    public double getLife() { return life; }

    public double getDamage() { return damage; }

    public double getArmor() { return armor; }

    public void setCure(double cure) { this.cure = cure; }

    public double getCure() { return cure; }

    public double beCured(double curedata) {
        if (life >= 100)
            return -1;

        life = life + curedata;

        if (life > 100)
            life = 100;

        return curedata;
    }

    public double beAttacked(double damagedata) {
        double data = damagedata - armor;
        if (data < 0)
            return 0;
        else
            life = life - data;

        if (life < 0)
            life = 0;

        return data;
    }

}
