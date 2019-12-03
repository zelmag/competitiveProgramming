import java.util.Scanner;
public class midnightOil {
    static int k;
    static int nLines;
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        nLines = input.nextInt();
        k = input.nextInt();
        System.out.println(searchForMin(k,nLines));
    }
    static int searchForMin(int low, int hi) {
        int mid;
        while (true) {
            if (hi - low <= 1) {
               return hi;
            }
            mid = low + (hi - low) / 2;
            int currRes = mid;
            int divisor = k;
            while (mid / divisor > 0) {
                currRes += mid / divisor;
                divisor *= k;
            }
//            System.out.println("divisor is "+divisor);
//            System.out.println("\nwith v "+mid);
//            System.out.println("currRes is "+currRes+" and N is "+nLines);
            if (currRes < nLines) {
                low = mid;
            }else if(currRes==nLines){
                return mid;
            }else {
                hi = mid;
            }
        }
    }
}
