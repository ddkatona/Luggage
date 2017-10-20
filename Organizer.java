import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ddkatona on 10/13/2017.
 */
public class Organizer {

    private Luggage L;
    private ArrayList<Item> items = new ArrayList<Item>();

    public Organizer(Luggage l, ArrayList<Item> items) {
        L = l;
        this.items = items;
    }

    public void organize() {
        Collections.sort(items, new ItemComparator());
        //ArrayList<Item> itemsUsed = L.putOne(items);

        //items.removeAll(itemsUsed);

        /*for (Item i: items) {
            System.out.print(i.getID() + " ");
        }
        System.out.println();*/

        L.simplePlacement(items);

        L.printLuggage();
    }

}
