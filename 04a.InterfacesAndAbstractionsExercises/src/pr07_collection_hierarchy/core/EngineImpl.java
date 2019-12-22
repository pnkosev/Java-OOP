package pr07_collection_hierarchy.core;

import pr07_collection_hierarchy.AddCollection;
import pr07_collection_hierarchy.AddRemoveCollection;
import pr07_collection_hierarchy.Collection;
import pr07_collection_hierarchy.MyListImpl;

import java.util.Scanner;

public class EngineImpl implements Engine {
    private StringBuilder sb;

    public EngineImpl() {
        sb = new StringBuilder();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemovableCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        String[] lineOne = scanner.nextLine().split(" ");

        fillCollection(addCollection, lineOne);
        fillCollection(addRemovableCollection, lineOne);
        fillCollection(myList, lineOne);

        int n = Integer.parseInt(scanner.nextLine());

        removeFromCollection(addRemovableCollection, n);
        removeFromCollection(myList, n);

        System.out.println(this.sb.toString().trim());
    }

    private void removeFromCollection(Collection collection, int n) {
        for (int i = 0; i < n; i++) {
            this.sb.append(collection.remove()).append(" ");
        }

        sb.append(System.lineSeparator());
    }

    private void fillCollection(Collection collection, String[] line) {
        for (String string: line) {
            if (!string.equals("")) {
                this.sb.append(collection.add(string)).append(" ");
            }
        }

        sb.append(System.lineSeparator());
    }
}
