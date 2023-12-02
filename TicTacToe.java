import java.util.*;

public class TicTacToe {
    public static void printSoln(int copyspace[]){
        for (int k = 0; k < copyspace.length; k++) {
            System.out.print(copyspace[k] + " ");
        }
        System.out.println();
    }
    
    public static void possibleSteps(int space[]) {
        System.out.println("Possible moves for O:");
        for(int i=0; i<9; i++){
            if(space[i]==0){
                int[] copyspace = space.clone();
                copyspace[i] = 2;
                printSoln(copyspace);
            }
        }
    }

    public static void main(String[] args) {
        int[] space = new int[9];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the moves: (0 = empty, 1 = X, 2 = O)");
        for (int i = 0; i < 9; i++) {
            space[i] = sc.nextInt();
        }

        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 9; i++) {
            if (space[i] == 1) {
                countX++;
            } else if (space[i] == 2) {
                countO++;
            }
        }

        if (Math.abs(countX - countO) > 1) {
            System.out.println("Invalid situation");
        } else {
            System.out.println("Valid Position");

            int index = 0;
            for (int i = 8; i >= 0; i--) {
                index = index * 3 + space[i]; 
                System.out.println(index+" at position "+i);
            }

            System.out.println("The original position is:");
            for (int k = 0; k < space.length; k++) {
                System.out.print(space[k] + " ");
            }
            System.out.println();

            possibleSteps(space);
        }
    }
}



