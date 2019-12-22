package pr06_military_elite;

import pr06_military_elite.enums.Corps;
import pr06_military_elite.interfaces.Engineer;
import pr06_military_elite.interfaces.Soldier;
import pr06_military_elite.models.RepairImpl;

import java.util.Collection;
import java.util.List;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    private List<RepairImpl> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corps corps, List<RepairImpl> repairs) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = repairs;
    }

    @Override
    public void addRepair(RepairImpl repair) {
        this.repairs.add(repair);
    }

    @Override
    public Collection<RepairImpl> getRepair() {
        return this.repairs;
    }

    @Override
    public String toString() {
        String sb = super.toString() + "Repairs: " +
                System.lineSeparator() +
                repairsAsString();
        return sb.trim();
    }

    private String repairsAsString() {
        StringBuilder sb = new StringBuilder();
        for (RepairImpl r: repairs) {
            sb.append("  ")
                    .append(r.toString())
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
