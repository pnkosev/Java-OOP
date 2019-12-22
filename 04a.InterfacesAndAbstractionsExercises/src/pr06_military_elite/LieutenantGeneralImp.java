package pr06_military_elite;

import pr06_military_elite.interfaces.LieutenantGeneral;
import pr06_military_elite.interfaces.Private;
import pr06_military_elite.interfaces.Soldier;

import java.util.List;

public class LieutenantGeneralImp extends PrivateImpl implements LieutenantGeneral {
    private List<Private> privateList;

    public LieutenantGeneralImp(int id, String firstName, String lastName, double salary, List<Private> privates) {
        super(id, firstName, lastName, salary);
        this.privateList = privates;
    }

    @Override
    public void addPrivate(Private p) {
        privateList.add(p);
    }

    @Override
    public String toString() {
        String sb = super.toString() + System.lineSeparator() +
                "Privates:" +
                System.lineSeparator() +
                privatesAsString();
        return sb.trim();
    }

    private String privatesAsString() {
        privateList.sort((a, b) -> b.getId() - a.getId());
        StringBuilder sb = new StringBuilder();
        for (Soldier p: privateList) {
            sb.append("  ")
                    .append(p.toString())
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
