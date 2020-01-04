package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTests {
    private Axe axe;
    private Dummy dummy;
    private static final int DMG = 10;
    private static final int DURABILITY = 1;
    private static final int HEALTH = 10;
    private static final int EXP = 10;

    @Before
    public void setup() {
        this.axe = new Axe(DMG, DURABILITY);
        this.dummy = new Dummy(HEALTH, EXP);
    }

    @Test
    public void shouldLoseDurabilityOnAttack() {
        this.axe.attack(this.dummy);

        Assert.assertEquals(DURABILITY - 1, this.axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfAttackingWithBrokenAxe() {
        this.axe.attack(this.dummy);
        this.axe.attack(this.dummy);
    }
}
