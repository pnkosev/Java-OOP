package pr03_barracks_wars_new_factory.models.units;

public class Gunner extends AbstractUnit {
    private static final int HEALTH = 20;
    private static final int ATTACK_DAMAGE = 20;

    public Gunner() {
        super(HEALTH, ATTACK_DAMAGE);
    }
}
