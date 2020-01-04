package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTests {
    private Dummy dummy;
    private static final int INITIAL_HEALTH = 10;
    private static final int INITIAL_EXP = 10;
    private static final int DMG_TO_KILL = 10;
    private static final int DMG_NOT_TO_KILL = 5;

    @Before
    public void setup() {
        this.dummy = new Dummy(INITIAL_HEALTH, INITIAL_EXP);
    }

    @Test
    public void shouldLoseHealthIfAttacked() {
        this.dummy.takeAttack(DMG_NOT_TO_KILL);
        Assert.assertEquals(INITIAL_HEALTH - DMG_NOT_TO_KILL, this.dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfDeadWhenAttacked() {
        this.dummy.takeAttack(DMG_TO_KILL);
        this.dummy.takeAttack(DMG_NOT_TO_KILL);
    }

    @Test
    public void shouldGiveExperienceIfKilled() {
        this.dummy.takeAttack(DMG_TO_KILL);
        int exp = this.dummy.giveExperience();

        Assert.assertEquals(INITIAL_EXP, exp);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotGiveExperienceIfNotKilled() {
        this.dummy.takeAttack(DMG_NOT_TO_KILL);

        int exp = this.dummy.giveExperience();
    }
}
