import java.io.*;
import java.util.*;

/*The logic is that you sort two arrays and loop the Ruth's one from the biggest to smallest each time
you find one from name beginning with B with lower than the first num but the difference between them
is the smallest. Each time when you find it you break and then continue to find the next one.
If you didn't find it , just use the result plus the Ruth(current)
 */

public class rocks {
    static Map<Integer,ArrayList<Integer>> maps = new HashMap<>();
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int rocks = in.nextInt();
        int counter=0;
        ArrayList<Integer> ruthSorted = new ArrayList<>();
        while(counter<rocks){
            ruthSorted.add(in.nextInt());
            counter++;
        }
        Collections.sort(ruthSorted); //smallest to biggest
        Collections.reverse(ruthSorted);
        int[] ble = new int[rocks];
        ArrayList<Integer> bleSorted = new ArrayList<>();
        ArrayList<Integer> blePlaying = new ArrayList<>();
        counter=0;
        while(counter<rocks){
            int curr = in.nextInt();
            blePlaying.add(curr);
            bleSorted.add(curr);
            counter++;
        }
        Collections.sort(bleSorted);
        Collections.reverse(bleSorted);
        while(!bleSorted.isEmpty()){
            //System.out.print("against "+bleSorted.get(0));
            int against=bleSorted.remove(0);
            Integer againstI = new Integer(against);
            int pick;
            ArrayList<Integer> choices = new ArrayList<>();
            if(ruthSorted.get(0)<=against){
                //cant beat them so pair with smallest one
                pick = ruthSorted.remove(ruthSorted.size()-1);
                if(maps.containsKey(againstI)){
                    maps.get(againstI).add(pick);
                }else {
                    maps.put(againstI, choices);
                    maps.get(againstI).add(pick);
                }
            }else{
                //find the bare min to beat it
                int index=ruthSorted.size()-1;
                while(index>0 && ruthSorted.get(index)<=against){
                    index--;
                }
                pick=ruthSorted.remove(index);
                if(maps.containsKey(againstI)){
                    maps.get(againstI).add(pick);
                }else {
                    maps.put(againstI, choices);
                    maps.get(againstI).add(pick);
                }
            }
            //System.out.println("against "+against+" "+maps.get(against));
        }
        //Time to Play!!
        //pick the just enough to beat her OR smallest one
        int totalLost=0;
        for(int i=0; i<blePlaying.size(); i++){
            int against = blePlaying.get(i);
            int my = maps.get(blePlaying.get(i)).remove(0);
            //System.out.println(" against "+against+" my kgkjgfkg "+my);
            if(my<=against){
                totalLost+=my;
            }
        }
        System.out.println(totalLost);
    }
}