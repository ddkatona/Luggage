import java.util.Comparator;

/**
 * Created by ddkatona on 10/14/2017.
 */
public class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {

        /*int diff1 = Math.max(o1.getH(), o1.getW());
        int diff2 = Math.max(o2.getH(), o2.getW());

        return diff1 < diff2 ? 1 : -1;*/
        return o1.size() < o2.size() ? 1 : -1;
    }

}
