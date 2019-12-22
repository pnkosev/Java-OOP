package pr06_military_elite.core;

import pr06_military_elite.*;
import pr06_military_elite.enums.Corps;
import pr06_military_elite.enums.MissionState;
import pr06_military_elite.interfaces.Private;
import pr06_military_elite.interfaces.Soldier;
import pr06_military_elite.models.MissionImpl;
import pr06_military_elite.models.RepairImpl;
import pr06_military_elite.utils.Validator;
import pr06_military_elite.utils.readers.InputReader;

import java.util.ArrayList;
import java.util.List;

public class EngineImpl implements Engine {
    public static final String END_LINE = "End";
    private InputReader reader;

    public EngineImpl(InputReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        String line;

        List<Soldier> soldiers = new ArrayList<>();

        while (!(line = this.reader.readLine()).equals(END_LINE)) {
            String[] input = line.split(" ");

            String type = input[0];
            int id = Integer.parseInt(input[1]);
            String firstName = input[2];
            String lastName = input[3];
            double salary = Double.parseDouble(input[4]);
            String corpsAsString;

            switch (type) {
                case "Private":
                    soldiers.add(new PrivateImpl(id, firstName, lastName, salary));
                    break;
                case "LeutenantGeneral":
                    List<Private> privates = getPrivatesOfLieutenantGeneral(soldiers, input);
                    soldiers.add(new LieutenantGeneralImp(id, firstName, lastName, salary, privates));
                    break;
                case "Engineer":
                    corpsAsString = input[5];

                    if (Validator.isCorpsValid(corpsAsString)) {
                        Corps corps = corpsAsString.equals(Corps.AIRFORCE.getName()) ? Corps.AIRFORCE : Corps.MARINE;
                        List<RepairImpl> repairs = getRepairs(input);
                        soldiers.add(new EngineerImpl(id, firstName, lastName, salary, corps, repairs));
                    }

                    break;
                case "Commando":
                    corpsAsString = input[5];

                    if (Validator.isCorpsValid(corpsAsString)) {
                        Corps corps = corpsAsString.equals(Corps.AIRFORCE.getName()) ? Corps.AIRFORCE : Corps.MARINE;
                        List<MissionImpl> missions = getMissions(input);
                        soldiers.add(new CommandoImpl(id, firstName, lastName, salary, corps, missions));
                    }
                    break;
                case "Spy":
                    soldiers.add(new SpyImpl(id, firstName, lastName, input[4]));
                    break;
                default:
            }
        }

        for (Soldier s: soldiers) {
            System.out.println(s.toString());
        }
    }

    private List<MissionImpl> getMissions(String[] input) {
        List<MissionImpl> missions = new ArrayList<>();

        for (int i = 6; i < input.length; i += 2) {
            if (Validator.isStateValid(input[i + 1])) {
                String missionCodeName = input[i];
                MissionState missionState = input[i + 1].equals(MissionState.FINISHED.getState()) ? MissionState.FINISHED : MissionState.IN_PROGRESS;
                missions.add(new MissionImpl(missionCodeName, missionState));
            }
        }
        return missions;
    }

    private List<RepairImpl> getRepairs(String[] input) {
        List<RepairImpl> repairs = new ArrayList<>();

        for (int i = 6; i < input.length; i += 2) {
            String repairPart = input[i];
            int repairHours = Integer.parseInt(input[i + 1]);
            repairs.add(new RepairImpl(repairPart, repairHours));
        }

        return repairs;
    }

    private List<Private> getPrivatesOfLieutenantGeneral(List<Soldier> soldiers, String[] input) {
        List<Private> privates = new ArrayList<>();

        for (int i = 5; i < input.length; i++) {
            int privateId = Integer.parseInt(input[i]);
            Private filteredPrivate = (Private) soldiers.stream().filter(p -> p.getId() == privateId).findFirst().orElse(null);
            privates.add(filteredPrivate);
        }

        return  privates;
    }
}
