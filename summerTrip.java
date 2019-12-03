import java.io.*;
import java.util.*;

/*
 * The problem statement can be found at http://www.spoj.com/problems/AGGRCOW/
 *
 * The online judge gives TLE, but the sample test case works. Can you find the
 * bug?
 */
public class summerTrip {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String summer = in.nextLine();
        StringBuilder s = new StringBuilder(summer);
        int counter = 0;
        for(int i = 0; i<s.length(); i++){
            boolean bad = false;
            Set<Character> seen = new HashSet<>();
            for(int j=i+1;j<s.length() && s.charAt(i)!= s.charAt(j);j++){
                char last = s.charAt(j);
                if(seen.contains(last)){
                    bad=true;
                    continue;
                }
                counter++;
                seen.add(last);
            }
            if(bad){
                continue;
            }
        }
        System.out.println(counter);
    }

}
