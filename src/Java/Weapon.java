package Java;

public class Weapon {
    private final int damageAmount;
    private final int limits;
    private int durability;

    public Weapon(boolean b){
        if (b){
            this.damageAmount = 2;
            this.limits = 80;
            this.durability = 100;
        }else{
            this.damageAmount = 1;
            this.limits = 50;
            this.durability = 150;
        }
    }

    public int getDamageAmount(){
        return damageAmount;
    }

    public int getLimits() {
        return limits;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
