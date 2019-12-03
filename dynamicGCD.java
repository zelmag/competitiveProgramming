import java.util.*;
public class dynamicGCD {
    public static void main(String[] args){
        /*n and m
        } n integers, A
        } m lines, each describing a single query (type 1 "1 i x" -- change ith element of A to x
						 		                   type 2 "2 l r" --- GCD of A[i] from [l,r)
								                   all indices are  i,l, r */
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] arr = new int[n];
        int counter = 0;
        while(counter < n){
            arr[counter] = input.nextInt();
            counter++;
        }
        int line = 0;
        while(line < m){
            int type = input.nextInt();
            int l = input.nextInt();
            int x = input.nextInt();
          if(type == 1){
              arr[l-1] = x;
          }else if(type == 2){
              int from = l;
              int exTo = x;
              int[] segArr = Arrays.copyOfRange(arr,from-1,exTo);
              if(segArr.length >= 1) {
                  int res = segArr[0];
                  for (int i = 1; i < segArr.length; i++) {
                      res = gcd(res, segArr[i]);
                  }
                  System.out.print(res + " ");
              }
          }
          line++;
        }
    }
    public static int gcd(int a, int b){
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}
