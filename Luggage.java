import java.util.ArrayList;

/**
 * Created by ddkatona on 10/13/2017.
 */
public class Luggage {

    private int H;
    private int W;
    private int[][] cells = null;

    public Luggage(int height, int width) {
        H = height;
        W = width;
        cells = new int[H][W];
        clearLuggage();
    }

    public void clearLuggage() {
        for(int height = 0; height < H; height++) {
            for(int width = 0; width < W; width++) {
                cells[height][width] = 0;
            }
        }
    }

    public int size() {
        return W*H;
    }

    public void printLuggage() {
        for(int height = 0; height < H; height++) {
            for(int width = 0; width < W; width++) {
                if(width == W-1) {
                    System.out.print(cells[height][width]);
                } else {
                    System.out.print(cells[height][width] + "\t");
                }
            }
            System.out.println();
        }
    }

    public boolean addItemAt(Item i, int height, int width) {
        if(height < 0 || height + i.getH() > H) return false;
        if(width < 0 || width + i.getW() > W) return false;

        for(int hItem = height; hItem < height + i.getH(); hItem++) {
            for(int wItem = width; wItem < width + i.getW(); wItem++) {
                if(cells[hItem][wItem] != 0) return false;
            }
        }

        fillArea(height, width,
                height + i.getH(), width + i.getW(),
                i.getID());

        return true;
    }

    public void fillArea(int h1, int w1, int h2, int w2, int itemID) {
        for(int h = h1; h < h2; h++)
            for(int w = w1; w < w2; w++)
                cells[h][w] = itemID;
    }

    public boolean simplePlacement(ArrayList<Item> items) {
        int i = 0;
        while(i < items.size()) {
            int itemsIn = i;
            for (int h = 0; h < H; h++) {
                for (int w = 0; w < W; w++) {
                    if (i < items.size()) {
                        boolean added = addItemAt(items.get(i), h, w);
                        if (added) {
                            i++;
                        } else {
                            items.get(i).rotate();
                            added = addItemAt(items.get(i), h, w);
                            if (added) {
                                i++;
                            } else {
                                items.get(i).rotate();
                            }
                        }
                    }
                }
            }
            if(itemsIn == i) break;
        }
        return i == items.size() ? true : false;
    }

    public ArrayList<Item> putOne(ArrayList<Item> items) {
        ArrayList<Item> itemsUsed = new ArrayList<Item>();

        if(size() == 0) return itemsUsed;
        if(items.size() == 0) return itemsUsed;

        for(int i = 0; i < items.size(); i++) {
            Item cornerItem = items.get(i);

            if (addItemAt(cornerItem, 0, 0)) {
                ArrayList<Item> remainingItems = new ArrayList<Item>();
                remainingItems.addAll(items);
                remainingItems.remove(cornerItem);
                itemsUsed = addTopAndBottom(remainingItems, cornerItem);
                if(itemsUsed != null) {
                    return itemsUsed;
                }
            }
            clearLuggage();
            cornerItem.rotate();
            if (addItemAt(cornerItem, 0, 0)) {
                ArrayList<Item> remainingItems = new ArrayList<Item>();
                remainingItems.addAll(items);
                remainingItems.remove(cornerItem);
                itemsUsed = addTopAndBottom(remainingItems, cornerItem);
                if(itemsUsed != null) {
                    return itemsUsed;
                } else {
                    clearLuggage();
                    cornerItem.rotate();
                    continue;
                }
            }
        }

        return null;
    }

    public ArrayList<Item> addTopAndBottom(ArrayList<Item> items, Item cornerItem) {
        ArrayList<Item> itemsUsed = new ArrayList<Item>();
        itemsUsed.add(cornerItem);
        Luggage topRight = new Luggage(cornerItem.getH(), W-cornerItem.getW());
        Luggage bottom = new Luggage(H-cornerItem.getH(), W);

        for(int i = 0; i < items.size(); i++) {
            topRight.clearLuggage();
            bottom.clearLuggage();
            ArrayList<Item> topRightItemSet = new ArrayList<Item>();
            ArrayList<Item> bottomItemSet = new ArrayList<Item>();
            bottomItemSet.addAll(items);
            for(int j = i; j < items.size(); j++) {
                topRightItemSet.add(items.get(j));
            }

            ArrayList<Item> itemsUsedtopRight = topRight.putOne(topRightItemSet);
            if(itemsUsedtopRight == null) {
                return null;
            }

            bottomItemSet.removeAll(itemsUsedtopRight);
            ArrayList<Item> itemsUsedBottom = bottom.putOne(bottomItemSet);

            if(itemsUsedtopRight != null && itemsUsedBottom != null) {
                mergeLuggage(topRight, 0, cornerItem.getW());
                mergeLuggage(bottom, cornerItem.getH(), 0);
                itemsUsed.addAll(itemsUsedtopRight);
                itemsUsed.addAll(itemsUsedBottom);
                return itemsUsed;
            }
        }

        return itemsUsed;
    }

    public int getH() {
        return H;
    }

    public int getW() {
        return W;
    }

    public int[][] getCells() {
        return cells;
    }

    public void mergeLuggage(Luggage small, int h, int w) {
        for(int height = 0; height < small.getH(); height++) {
            for(int width = 0; width < small.getW(); width++) {
                cells[height + h][width + w] = small.getCells()[height][width];
            }
        }
    }
}
