import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Sketch {
    private TreeMap<Integer, Shape> shapes;
    Integer currID = 0;

    public Sketch() {
        shapes = new TreeMap<>();
    }

    public synchronized void addShape(Shape shape){
        shapes.put(currID, shape);
        currID++;
    }

    //id of most recently created shape that contains keypress at (x, y)
    public synchronized Integer findID(int x, int y){
        Set<Integer> sortedID = shapes.descendingKeySet();
        for(Integer id : sortedID){
            if(shapes.get(id).contains(x, y)){
                return id;
            }
        }
        return null;
    }

    public synchronized void move(Integer id, int x, int y){
        shapes.get(id).moveBy(x, y);
    }

    public synchronized void recolor(Integer id, Color color){
        shapes.get(id).setColor(color);
    }

    public synchronized void delete(Integer id){
        shapes.remove(id);
    }
}
