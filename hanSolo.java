import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class hanSolo {
    static int n;
    static Coordinate gun;
    static ArrayList<Coordinate> points;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        points = new ArrayList<>();
        n = in.nextInt();
        int myX = in.nextInt();
        int myY = in.nextInt();
        gun = new Coordinate(myX, myY);
        int counter = 0;
        while (counter < n) {
            int x = in.nextInt();
            int y = in.nextInt();
            Coordinate temp = new Coordinate(x, y);
            points.add(temp);
            counter++;
        }
        System.out.println(countLines());
    }

    static int countLines() {
        Set<Double> slopes = new HashSet<>();
        for (Coordinate p : points) {
            //calculate m and b from the gun point and compare to other existing lines
            double m;
            if (gun.x - p.x == 0) {
                m = Double.POSITIVE_INFINITY;
            }else if(gun.y-p.y == 0){
                m = Double.NEGATIVE_INFINITY;
            }
            else {
                m = (gun.y - p.y) / (gun.x - p.x);
            }
            //double b = (p.y - (m * p.x));
            //boolean partOfExistingLine = false;
//            for (Line l : lines) {
//                if (l.m == m && l.b == b) {
//                    partOfExistingLine = true;
//                    break;
//                }
//            }
//            if (!partOfExistingLine) {
                slopes.add(m);
            //}
        }
        return slopes.size();
    }
}
 class Coordinate{
    double x;
    double y;
    public Coordinate(double x, double y){
        this.x=x;
        this.y=y;
    }
    public double getX() {
        return x;
    }
    public double getY(){
        return y;
    }
}
 class Line{
    double m;
    double b;
    public Line(double m, double b) {
        this.m = m;
        this.b = b;
    }
}
