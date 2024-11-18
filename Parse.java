import java.awt.*;
import java.util.ArrayList;

public class Parse {
    public Parse(){

    }
    public void parseLine(String line, Sketch sketch){
        //FORMAT:
        // add: command + shapename
        // delete: command + id
        // recolor: command + id + color
        // move : command + id + x + y

        //make all of this a class
        String[] split = line.split(" ");
        if (split[0].equals("add")){
            int[] splitString = parse(line.substring(split[0].length() + split[1].length() + 2));
            if (split[1].equals("rectangle")){
                sketch.addShape(new Rectangle(splitString[0], splitString[1], splitString[2], splitString[3], new Color(splitString[4])));
            }
            else if (split[1].equals("ellipse")){
                sketch.addShape(new Ellipse(splitString[0], splitString[1], splitString[2], splitString[3], new Color(splitString[4])));
            }
            else if (split[1].equals("segment")){
                sketch.addShape(new Segment(splitString[0], splitString[1], splitString[2], splitString[3], new Color(splitString[4])));
            }
            else if (split[1].equals("polyline")){
                //collect points from message, pass in to
                ArrayList<Point> points = new ArrayList<Point>();
                for (int i = 0; i < splitString.length - 1; i += 2){
                    points.add(new Point(splitString[i], splitString[i + 1]));
                }
                sketch.addShape(new Polyline(points, new Color(splitString[splitString.length - 1])));
            }
        }
        if (split[0].equals("delete")){
            sketch.delete(Integer.parseInt(split[1]));
        }
        if (split[0].equals("drag")){
            //server.getSketch().move(Integer.parseInt(split[1]));
        }
        else if (split[0].equals("move")){
            sketch.move(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
        }
        else if (split[0].equals("recolor")){
            sketch.recolor(Integer.parseInt(split[1]), new Color(Integer.parseInt(split[2])));
        }

    }
    private int[] parse(String line){
        String[] split = line.split(" ");
        int[] toInt = new int[split.length];
        for (int i = 0; i < split.length; i++){
            toInt[i] = Integer.parseInt(split[i]);
        }
        return toInt;
    }
}
