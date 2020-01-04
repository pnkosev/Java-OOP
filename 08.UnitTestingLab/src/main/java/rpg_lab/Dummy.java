package rpg_lab;

import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dummy implements Target {

    private int health;
    private int experience;
    private List<Weapon> loot;

    public Dummy(int health, int experience) {
        this.health = health;
        this.experience = experience;
        setLoot();
    }

    private void setLoot() {
        this.loot = new ArrayList<Weapon>();
        this.loot.add(new Axe(10, 10));
        this.loot.add(new Axe(20, 20));
    }

    public int getHealth() {
        return this.health;
    }

    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    public Weapon dropLoot() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        Collections.shuffle(loot);
        return loot.get(0);
    }

    public boolean isDead() {
        return this.health <= 0;
    }
}
