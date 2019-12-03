import java.io.*;
import java.util.*;

public class missingNumbers {
    static int entered;
    static ArrayList<Integer> vals = new ArrayList<>();
    public static void main(String[] args) throws IOException {
       Scanner in = new Scanner(System.in);
       entered=in.nextInt();
       in.nextLine();
       int counter = 0;
       while(counter<entered){
           vals.add(in.nextInt());
           counter++;
       }
       int max=vals.get(entered-1);
       boolean fail=false;
       for(int i=1;i<=max;i++){
           if(!vals.contains(i)){
               fail=true;
               System.out.println(i);
           }
       }if(!fail){
           System.out.println("good job");
        }
    }
}
