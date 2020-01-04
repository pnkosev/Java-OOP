package rpg_lab.interfaces;

import java.util.List;

public interface Target {
    int getHealth();
    void takeAttack(int attackPoints);
    int giveExperience();
    Weapon dropLoot();
    boolean isDead();
}
