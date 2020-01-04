package rpg_lab;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import java.util.List;

public class HeroTests {
    private static final int EXP = 10;
    private static final String name = "Pesho";

    @Test
    public void shouldGetExperienceIfTargetIsKilled() {
        Weapon fakeWeapon = Mockito.mock(Weapon.class);
        Target fakeTarget = Mockito.mock(Target.class);

        Mockito.when(fakeTarget.isDead()).thenReturn(true);
        Mockito.when(fakeTarget.giveExperience()).thenReturn(EXP);

        Hero hero = new Hero(name, fakeWeapon);
        hero.attack(fakeTarget);
        Assert.assertEquals(EXP, hero.getExperience());
    }

    @Test
    public void shouldGetLootIfTargetIsKilled() {
        Weapon fakeWeapon = Mockito.mock(Weapon.class);
        Weapon fakeLoot = Mockito.mock(Weapon.class);
        Target fakeTarget = Mockito.mock(Target.class);

        Mockito.when(fakeTarget.isDead()).thenReturn(true);
        Mockito.when(fakeTarget.dropLoot()).thenReturn(fakeLoot);

        Hero hero = new Hero(name, fakeWeapon);
        hero.attack(fakeTarget);

        Assert.assertTrue(hero.getInventory().contains(fakeLoot));
    }
}
