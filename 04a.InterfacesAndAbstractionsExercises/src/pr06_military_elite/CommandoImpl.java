package pr06_military_elite;

import pr06_military_elite.enums.Corps;
import pr06_military_elite.interfaces.Commando;
import pr06_military_elite.models.MissionImpl;
import pr06_military_elite.models.RepairImpl;

import java.util.List;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private List<MissionImpl> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps, List<MissionImpl> missions) {
        super(id, firstName, lastName, salary, corps);
        this.missions = missions;
    }

    @Override
    public void addMission(MissionImpl m) {
        this.missions.add(m);
    }

    @Override
    public List<MissionImpl> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        String sb = super.toString() + "Missions: "
                + System.lineSeparator()
                + missionsAsString();

        return sb.trim();
    }

    private String missionsAsString() {
        StringBuilder sb = new StringBuilder();
        for (MissionImpl m: missions) {
            sb.append("  ")
                    .append(m.toString())
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
