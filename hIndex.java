import java.util.*;
public class hIndex {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numTests = in.nextInt();
        int testCase = 0;
        while (testCase < numTests) {
            int numPapers = in.nextInt();
            int currPaper = 0;
            ArrayList<Integer> citations = new ArrayList<>();
            while (currPaper < numPapers) {
                citations.add(in.nextInt());
                currPaper++;
            }
            testCase++;
            System.out.print("Case #"+testCase+": ");
            ArrayList<Integer> hIndex = help(citations);
            for(Integer h : hIndex){
                System.out.print(h+" ");
            }
            System.out.println();
        }
    }

    static ArrayList<Integer> help(ArrayList<Integer> citations){
        ArrayList<Integer> hIndex = new ArrayList<>();
        int currMax = 0; //assuming at least 1 paper with 1 citation
        for(int end = 0; end<citations.size(); end++){
            int numMeet = 0;
            if(citations.get(end) >= currMax+1){
                numMeet++;
                for(int back = end-1; back>=0; back--){
                    if(citations.get(back) >= currMax+1){
                        numMeet++;
                    }
                }
            }
            if(numMeet >= currMax+1){
                currMax+=1;
            }
            hIndex.add(currMax);
        }
        return hIndex;
    }
}

