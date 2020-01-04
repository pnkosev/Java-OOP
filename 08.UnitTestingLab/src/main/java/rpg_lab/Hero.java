package rpg_lab;

import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import java.util.*;

public class Hero {

    private String name;
    private int experience;
    private Weapon weapon;
    private List<Weapon> inventory;

    public Hero(String name, Weapon weapon) {
        this.name = name;
        this.experience = 0;
        this.weapon = weapon;
        this.inventory = new ArrayList<Weapon>();
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public List<Weapon> getInventory() {
        return this.inventory;
    }

    public void attack(Target target) {
        this.weapon.attack(target);

        if (target.isDead()) {
            this.inventory.add(target.dropLoot());
            this.experience += target.giveExperience();
        }
    }
}
