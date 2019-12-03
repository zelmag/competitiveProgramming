import java.util.*;
public class divBy11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numTests = in.nextInt();
        int testCase = 0;
        while (testCase < numTests) {
            int[] digits = new int[10];
            int dig = 1;
            while(dig <= 9){
                digits[dig] = in.nextInt();
                dig++;
            }
            ArrayList<Integer> digitsPresent = new ArrayList<>();
            for(int i = 1; i<10; i++){
                while(digits[i]>0){
                    digitsPresent.add(i);
                    digits[i]--;
                }
            }
            testCase++;
            System.out.print("Case #"+testCase+": ");
            boolean res = help(0,1,digitsPresent);
            if(res){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    static boolean help(int currTotal, int s, ArrayList<Integer> options){
        if(options.size() == 0){
            return currTotal % 11 == 0;
        }else{
            // Create a new LinkedHashSet
            ArrayList<Integer> loopThru = new ArrayList<>();
            Set<Integer> set = new LinkedHashSet<>();
            // Add the elements to set
            set.addAll(options);
            loopThru.addAll(set);
            for(int i = 0; i < loopThru.size(); i++){
                currTotal += (loopThru.get(i))* s;
                Integer removed = loopThru.remove(i);
                options.remove(removed);
                if(help(currTotal,s*-1,options)){
                    return true;
                }
                options.add(removed);
                loopThru.add(removed);
                currTotal-= removed*s;
            }
        }
        return false;
    }


}

