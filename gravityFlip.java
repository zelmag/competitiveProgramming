import java.util.Scanner; //to be able to read input from the keyboard...breaks the input into tokens using a delimiter
//(whitespace)

public class gravityFlip{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCols = input.nextInt();
        input.nextLine(); //to consume the enter. nextInt() only reads the int and not the enter.
        //make an array with the number of blocks in each column
        int[] blocks = new int[numCols];
        for (int i = 0; i < numCols; i++) {
            blocks[i] = input.nextInt();
        }
        //Sort that array...I'll do an insertion sort.
        //assume the first one is sorted.
        int key;
        int j;
        for (int i = 1; i < numCols; i++) {
            key = blocks[i];
            j = i - 1;
            while (j >= 0 && blocks[j] > key) { //loop through the elements in front of me until find one that is smaller than me.
                // stop there
                blocks[j + 1] = blocks[j]; //shift elements bigger than me to the right by one
                j--;
            }
            //found my position. 1 after where j stopped because blocks[j] < key now
            blocks[j + 1] = key;
        }
        //Print the array
        for(int i=0; i<numCols; i++){
            System.out.print(blocks[i]+" ");
        }
    }
}