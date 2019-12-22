package pr06_military_elite;

import pr06_military_elite.enums.Corps;
import pr06_military_elite.interfaces.SpecialisedSoldier;

public abstract class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    private Corps corps;

    protected SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary);
        this.corps = corps;
    }

    @Override
    public String toString() {
        String sb = super.toString() + System.lineSeparator() +
                String.format("Corps: %s", this.corps.getName()) +
                System.lineSeparator();
        return sb;
    }
}
