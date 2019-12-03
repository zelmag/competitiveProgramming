import java.util.*;
public class minBlocks {
    public static void main(String[] args) {
        Scanner in = new Scanner();
        int numTests = in.nextInt();
        int testCase = 0;
        while (testCase < numTests) {
            int sizeGrid = in.nextInt();
            int[][] arr = new int[sizeGrid][sizeGrid];
            for (int r = 0; r < sizeGrid; r++) {
                String currS = in.next();
                for (int c = 0; c < sizeGrid; c++) {
                    char currChar = currS.charAt(c);
                    arr[r][c] = currChar == '.' ? 0 : 1;
                }
            }
            System.out.println("Case #" + testCase + ": " + help(arr, 0, Integer.MAX_VALUE));
            testCase++;
        }
    }

    static int help(int[][] arr, int movesSoFar, int minMoves) {
        if (allBlack(arr)) {
            return minMoves;
        } else if (movesSoFar > minMoves) {
            return Integer.MAX_VALUE;
        }
        //alright so make a move
        //flip the longest diagonal of whites you can find
        //return 1 + help(with that board);

        //pointing to the right
        ArrayList<Point> bestIndices = new ArrayList<>();
        int maxWhites = 0;
        for (int c = 0; c < arr[0].length; c++) {
            ArrayList<Point> currIndices = new ArrayList<>();
            int currWhites = 0;
            int topEnd = arr[0][c];
            currIndices.add(new Point(0,c));
            if(topEnd == 0){
                currWhites++;
            }
            for(int r = 1; r < arr.length; r++){
                for(int col = c-1; c>=0; c--){
                    if(topEnd == 0){
                        currWhites++;
                    }
                    currIndices.add(new Point(r,col));
                }
            }
            if(currWhites > maxWhites){
                maxWhites = currWhites;
                bestIndices = currIndices;
            }
            //go down
        }

        for (int c = 0; c < arr[0].length; c++) {
            ArrayList<Point> currIndices = new ArrayList<>();
            int currWhites = 0;
            int topEnd = arr[0][c];
            currIndices.add(new Point(0,c));
            if(topEnd == 0){
                currWhites++;
            }
            for(int r = 1; r < arr.length; r++){
                for(int col = c-1; c>=0; c--){
                    if(topEnd == 0){
                        currWhites++;
                    }
                    currIndices.add(new Point(r,col));
                }
            }
            if(currWhites > maxWhites){
                maxWhites = currWhites;
                bestIndices = currIndices;
            }
            //go down
        }

    }

    static boolean allBlack(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                if (arr[r][c] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
class Point{
    int r;
    int c;
    public Point(int r, int c){
        this.r = r;
        this.c = c;
    }

    public int getC() {
        return c;
    }
    public int getR(){
        return r;
    }
}

