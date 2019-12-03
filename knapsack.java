import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
class Jewel{
    int value;
    int weight;
    public Jewel(int val, int wt){
        value=val;
        weight=wt;
    }

    public int getValue() {
        return value;
    }
    public int getWeight(){
        return weight;
    }
}
public class knapsack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numItems = in.nextInt();
        int capacity = in.nextInt();
        List<Jewel> jewels = new ArrayList<>();
        jewels.add(new Jewel(0,0));
        int counter = 0;
        while (counter < numItems) {
            jewels.add(new Jewel(in.nextInt(), in.nextInt()));
            counter++;
        }
        System.out.println(knapsack(jewels,capacity));
    }
    static int knapsack(List<Jewel> jewels, int maxCapacity) {
        int n = jewels.size();
        int[][] bestValue = new int[n+1][maxCapacity + 1];
        for (int i = 1; i <=n; i++)
            for (int cap = 1; cap <=maxCapacity; cap++) {
                Jewel curr = jewels.get(i - 1);
                if (curr.weight <= cap) {
                    bestValue[i][cap] = Math.max(bestValue[i-1][cap], curr.value + bestValue[i - 1][cap - curr.weight]);
                }else{
                    bestValue[i][cap]=bestValue[i-1][cap];
                }
            }
            return bestValue[n][maxCapacity];
    }

}

