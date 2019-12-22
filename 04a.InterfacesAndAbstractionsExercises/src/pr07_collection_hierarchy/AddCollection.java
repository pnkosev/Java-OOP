package pr07_collection_hierarchy;

import pr07_collection_hierarchy.interfaces.Addable;

public class AddCollection extends Collection implements Addable {
    public AddCollection() {
        super();
    }

    @Override
    public int add(String s) {
        int index = super.getItems().size();
        super.getItems().add(s);
        return index;
    }

    @Override
    public String remove() {
        return null;
    }
}
