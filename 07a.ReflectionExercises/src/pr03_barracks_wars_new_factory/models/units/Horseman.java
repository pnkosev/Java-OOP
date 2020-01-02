package pr03_barracks_wars_new_factory.models.units;

public class Horseman extends AbstractUnit {
    private static final int HEALTH = 50;
    private static final int ATTACK_DAMAGE = 10;

    public Horseman() {
        super(HEALTH, ATTACK_DAMAGE);
    }
}
