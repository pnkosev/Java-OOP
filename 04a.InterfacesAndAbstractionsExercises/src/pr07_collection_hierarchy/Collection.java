package pr07_collection_hierarchy;

import pr07_collection_hierarchy.interfaces.AddRemovable;
import pr07_collection_hierarchy.interfaces.Addable;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection implements Addable, AddRemovable {
    private final int MAX_SIZE = 100;
    private List<String> items;

    protected Collection() {
        this.items = new ArrayList<>();
    }

    public List<String> getItems() {
        return this.items;
    }
}
