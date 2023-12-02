import java.util.*;

public class TicTacToeAI {
    public static void printSoln(int[] copyspace) {
        for (int k = 0; k < copyspace.length; k++) {
            System.out.print(copyspace[k] + " ");
        }
        System.out.println();
    }

    public static int calculateScore(int[] board, int player) {
        int score = 0;

        for (int row = 0; row < 3; row++) {
            int count = 0;
            for (int col = 0; col < 3; col++) {
                if (board[row * 3 + col] == player) {
                    count++;
                }
            }
            if (count == 2) {
                score++;
            }
        }

        for (int col = 0; col < 3; col++) {
            int count = 0;
            for (int row = 0; row < 3; row++) {
                if (board[row * 3 + col] == player) {
                    count++;
                }
            }
            if (count == 2) {
                score++;
            }
        }

        // Check diagonals
        int countDiagonal1 = 0;
        int countDiagonal2 = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i * 3 + i] == player) {
                countDiagonal1++;
            }
            if (board[i * 3 + 2 - i] == player) {
                countDiagonal2++;
            }
        }
        if (countDiagonal1 == 2) {
            score++;
        }
        if (countDiagonal2 == 2) {
            score++;
        }

        return score;
    }

    public static void possibleSteps(int[] space) {
        System.out.println("Possible moves for O:");
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 9; i++) {
            if (space[i] == 0) {
                int[] copyspace = space.clone();
                copyspace[i] = 2;
                int score = calculateScore(copyspace, 2); 
                printSoln(copyspace);
                System.out.println("Score: " + score);

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        System.out.println("The best move for O is at position " + bestMove);
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

            System.out.println("The original position is:");
            for (int k = 0; k < space.length; k++) {
                System.out.print(space[k] + " ");
            }
            System.out.println();

            possibleSteps(space);
        }
    }
}
