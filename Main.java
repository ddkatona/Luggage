import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ddkatona on 10/13/2017.
 */
public class Main {

    public static void main(String[] args) {

        Luggage L = null;
        ArrayList<Item> items = new ArrayList<Item>();

        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
            //sc = new Scanner(new FileReader("res/example3"));

            // read luggage size
            if(sc.hasNextLine()) {
                String[] luggageInfo = sc.nextLine().split("\t");
                if(luggageInfo.length > 1) {
                    int h = Integer.parseInt(luggageInfo[0]);
                    int w = Integer.parseInt(luggageInfo[1]);
                    L = new Luggage(h, w);
                }
            }

            // read number of items
            int numberOfItems = 0;
            if(sc.hasNextLine()) {
                String noiInfo = sc.nextLine();
                numberOfItems = Integer.parseInt(noiInfo);
            }

            // read items
            for(int i = 0; i < numberOfItems; i++) {
                if(sc.hasNextLine()) {
                    String[] itemInfo = sc.nextLine().split("\t");
                    if(itemInfo.length > 1) {
                        int h = Integer.parseInt(itemInfo[0]);
                        int w = Integer.parseInt(itemInfo[1]);
                        items.add(new Item(h, w, i+1));
                    }
                }
            }

            // create Organizer
            Organizer O = new Organizer(L, items);
            O.organize();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }

    }

}
