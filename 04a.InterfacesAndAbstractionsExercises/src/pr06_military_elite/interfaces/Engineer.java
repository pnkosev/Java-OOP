package pr06_military_elite.interfaces;

import pr06_military_elite.models.RepairImpl;

import java.util.Collection;

public interface Engineer extends SpecialisedSoldier {
    void addRepair(RepairImpl repair);
    Collection<RepairImpl> getRepair();
}
