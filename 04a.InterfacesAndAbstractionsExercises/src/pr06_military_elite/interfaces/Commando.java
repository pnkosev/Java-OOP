package pr06_military_elite.interfaces;

import pr06_military_elite.models.MissionImpl;

import java.util.Collection;

public interface Commando extends SpecialisedSoldier {
    void addMission(MissionImpl m);
    Collection<MissionImpl> getMissions();
}
