package pr07_collection_hierarchy;

import pr07_collection_hierarchy.interfaces.AddRemovable;

public class AddRemoveCollection extends Collection implements AddRemovable {

    public AddRemoveCollection() {
        super();
    }

    @Override
    public String remove() {
        return super.getItems().remove(super.getItems().size() - 1);
    }

    @Override
    public int add(String s) {
        super.getItems().add(0, s);
        return 0;
    }
}
