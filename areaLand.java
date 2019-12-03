import java.util.*;
import java.awt.*;

public class areaLand {
    static int nPoints;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        nPoints = in.nextInt();
        ArrayList<Point> points = new ArrayList<Point>();
        int counter = 0;
        while(counter<nPoints){
            int x = in.nextInt();
            int y = in.nextInt();
            points.add(new Point(x,y));
            counter++;
        }
        int area = 0;
        int j = points.size()-1;
        for(int i = 0; i < points.size(); i++){
            area+= (points.get(j).x + points.get(i).x) * (points.get(j).y - points.get(i).y);
            j=i;

        }
        area = area < 0 ? -area : area;
        System.out.println(area);
    }

}

