package pr07_collection_hierarchy;

import pr07_collection_hierarchy.interfaces.MyList;

public class MyListImpl extends Collection implements MyList {
    public MyListImpl() {
        super();
    }

    @Override
    public int getUsed() {
        return super.getItems().size();
    }

    @Override
    public String remove() {
        return super.getItems().remove(0);
    }

    @Override
    public int add(String s) {
        super.getItems().add(0, s);
        return 0;
    }
}
