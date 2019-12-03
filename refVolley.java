import java.util.*;

class Touch{
    char team;
    char player;
    char gender;
    public Touch(char team, char player, char gender){
        this.team = team;
        this.player = player;
        this.gender = gender;
    }

}
public class refVolley {
    static ArrayList<Touch> record = new ArrayList<>();
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int touches = input.nextInt();
        int counter = 0;
        while(counter < touches){
            String line = input.next();
            char team = line.charAt(0);
            char player = line.charAt(1);
            char gender = line.charAt(2);
            record.add(new Touch(team,player,gender));
            counter++;
        }
        validate();
    }
    static void validate() {
        int girl = 0;
        int boy = 0;
        char prevTeam = '0';
        char prevPerson = '0';
        int sameTeam = 0;
        for (int i = 0; i < record.size(); i++) {
            Touch curr = record.get(i);
            if (curr.team == prevTeam) {
                sameTeam++;

                //IF SAME PLAYER TOUCH 2X, VIOLATION!
                if (curr.player == prevPerson) {
                    System.out.println(prevTeam);
                    return;
                }
                if (curr.gender=='W') {
                    girl++;
                } else {
                    boy++;
                }
                //TEAM CAN'T TOUCH >3 IN A ROW
                if (sameTeam >= 4) {
                    System.out.println(prevTeam);
                    return;
                }
            } else {
                /*if a team touches the ball twice or more before it is hit to the other team, both a man and a woman
                must have touched the ball at least once. A ball is considered to be “hit to the other team” once the
                other team makes contact with the ball, OR at the end of a rally.
                For example, if a rally ends with three consecutive hits from women on team A, that is a violation.
                 */
                if(sameTeam >= 2){
                    if(boy == 0 || girl == 0){
                        System.out.println(prevTeam);
                        return;
                    }
                }
                if (curr.gender == 'W') {
                    girl = 1;
                    boy = 0;
                } else {
                    girl = 0;
                    boy = 1;
                }
                prevTeam = curr.team;
                prevPerson = curr.player;
                sameTeam = 1;
            }
        }
        if(sameTeam >= 2){
            if(boy == 0 || girl == 0){
                System.out.println(prevTeam);
                return;
            }
        }
        System.out.println("No violation");
    }
}
