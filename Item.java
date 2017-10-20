/**
 * Created by ddkatona on 10/13/2017.
 */
public class Item {

    private int H;
    private int W;
    private int ID;

    public Item(int height, int width, int id) {
        H = height;
        W = width;
        ID = id;
    }

    public int getH() {
        return H;
    }

    public int getW() {
        return W;
    }

    public int getID() {
        return ID;
    }

    public int size() {
        return W*H;
    }

    public void rotate() {
        int tmp = H;
        H = W;
        W = tmp;
    }

}
