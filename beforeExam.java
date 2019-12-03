import java.io.*;
import java.util.*;


public class beforeExam {
    static int[] timeSpent;
    static Map<Integer,Range> choices = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int days = in.nextInt();
        int sumTime = in.nextInt();
        timeSpent=new int[days];
        int counter = 0;
        while (counter < days) {
            int minTime = in.nextInt();
            int maxTime = in.nextInt();
            timeSpent[counter]=maxTime;
            choices.put(counter, new Range(minTime, maxTime));
            counter++;
        }
        int currTotal=0;
        for (int i = 0; i < timeSpent.length; i++) {
            currTotal+=timeSpent[i];
        }
        if(currTotal<sumTime){
            //I cant reach it even picking maxTime every time || sumTime < than all my mins
            System.out.println("NO");
        }else if(currTotal==sumTime){
            //im done!
            printTime();
        }else {
            //i passed sumtime< currTotal must subtract until done
            int diff = currTotal - sumTime; //Must go down by
            for (int i = 0; i < timeSpent.length && diff > 0; i++) {
                //take away as much as I can from diff
                int currMin = choices.get(i).minTime;
                int currChoice=timeSpent[i]-1;
                if(currChoice>=currMin) {
                    diff--;
                    while (diff > 0 && currChoice > currMin) {
                        currChoice--;
                        diff--;
                    }
                    timeSpent[i] = currChoice;
                }
            }
            if(diff==0) {
                printTime();
            }else{
                System.out.println("NO");
            }
        }
    }
    static void printTime(){
        System.out.println("YES");
        for(int i=0; i<timeSpent.length;i++){
            System.out.print(timeSpent[i]+" ");
        }
    }
    static class Range{
        int minTime;
        int maxTime;
        public Range(int minTime,int maxTime){
            this.minTime=minTime;
            this.maxTime=maxTime;
        }
        public int getMinTime() {
            return minTime;
        }
    }

}
