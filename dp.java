import java.util.Scanner;
public class dp {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int counter = 0;
        String[] s = new String[n];
        while(counter<n){
            s[counter] = in.next();
            counter++;
        }
        int[][] dp = new int [n][n];
        dp[0][0] = 1;
        for(int i=1; i<n;i++){
            if(s[i-1].equals("f")) {
                for (int j = 0; j<i; j++) {
                    dp[i][j + 1] = dp[i - 1][j];
                }
            }else{
                int suffix=0;
                for(int j=i-1; j>=0; j--){
                    suffix=suffix+dp[i-1][j];
                    dp[i][j]=suffix;
                }
            }
        }
        int total=0;
        for(int j=0; j<n;j++){
            total+=dp[n-1][j];
        }
        System.out.println(total);
    }
}
