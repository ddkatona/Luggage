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
        for (Item i: items) {
            if(i.getW() < i.getH())
                i.rotate();
        }

        boolean worked = L.simplePlacement(items);

        if(!worked) {
            L.clearLuggage();
            Collections.sort(items, new ItemComparator2());
            L.simplePlacement(items);
        }

        /*for (Item i: items) {
            System.out.print(i.getID() + " ");
        }
        System.out.println();*/

        //L.simplePlacement(items);

        L.printLuggage();
    }

}
