import java.util.Scanner;
public class palindrome {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(longestPalindrome(s));
    }
    public static String longestPalindrome(String s) {
        if (s.length() < 1) {
            return s.substring(0, 1);
        } else if (s.length() == 1) {
            return s.substring(0, 2);
        }
        Corner best = new Corner(0, 0);
        for (int c = 0; c < s.length() - 1; c++) {
            //expand using it as only center for odd numbered palindromes
            //expand using it and next one as centers for even numbered palindromes
            Corner odd = expand(s, c, c);
            Corner curr = odd;
            if(s.charAt(c)==s.charAt(c+1)) {
                Corner even = expand(s, c, c + 1);
                curr = odd.getLength() > even.getLength() ? odd : even;
            }
            if (curr.getLength() > best.getLength()) {
                best = curr;
            }

        }
        return s.substring(best.start, best.end + 1);
    }

    private static Corner expand(String s, int left, int right) {
        boolean inBounds = left >= 0 && right < s.length();
        while (inBounds && (s.charAt(left) == s.charAt(right))) {
            left--;
            right++;
            inBounds = left >= 0 && right < s.length();
        }
        left++;
        right--;
        System.out.println("returning "+s.substring(left,right+1));
        return new Corner(left, right);
    }
}
class Corner {
    int start;
    int end; //inclusive

    public Corner(int s, int e) {
        start = s;
        end = e;
    }

    public int getLength() {
        return end - start + 1; //zero based indexing
    }

}
