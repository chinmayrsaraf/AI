
import java.util.*;

public class ttt_ai {
    public static void main(String[] args) {
        String a[] = new String[9];
        int inta[] = new int[9];
        int i;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input data: ");
        for (i = 0; i < 9; i++) {
            a[i] = sc.next();
        }

        System.out.print("Tictactoe vector [");
        for (i = 0; i < 9; i++) {
            if (a[i].equals("X")) {
                a[i] = "1";
            } else if (a[i].equals("O")) {
                a[i] = "2";
            }
            System.out.print(" " + a[i]);
        }
        System.out.println("]");

        for (i = 0; i < 9; i++) {
            inta[i] = Integer.parseInt(a[i]);
        }

        // Checking for validity
        int count1 = 0, count2 = 0, count0 = 0;

        for (int j = 0; j < 9; j++) {
            if (inta[j] == 1) {
                count1++;
            } else if (inta[j] == 2) {
                count2++;
            } else {
                count0++;
            }
        }

        if (Math.abs(count1 - count2) <= 1) {
            System.out.println("Valid!");
        } else {
            System.out.println("Invalid!!");
            System.exit(0);
        }

        // Calculating Index
        double index = 0;
        for (int j = 0; j < 9; j++) {
            index += (inta[j] * Math.pow(3, 8 - j));
        }
        System.out.println("Index is: " + index);

        // Computing Score
        int[] computingScores = new int[9];

        System.out.println("Calculating Computing Score: ");
        System.out.println(" ");

        // Making a probability matrix
        System.out.println("M = ");
        if (count1 <= count2) {
            // X's Turn {The "X" player goes first always!}
            for (i = 0; i < 9; i++) {
                int[] copyInta = inta.clone();

                if (copyInta[i] == 0) {
                    copyInta[i] = 1;
                    printMatrix(copyInta);

                    int rowCounter = 0;
                    int colCounter = 0;
                    int diagCounter = 0;

                    for (int j = 0; j < 3; j++) {
                        if (checkRow(copyInta, j))
                            rowCounter++;
                    }

                    for (int j = 0; j < 3; j++) {
                        if (checkColumn(copyInta, j))
                            colCounter++;
                    }

                    if (checkDiagonal(copyInta))
                        diagCounter++;

                    computingScores[i] = rowCounter + colCounter + diagCounter;
                }
            }
        } else {
            // O's Turn
            for (i = 0; i < 9; i++) {
                int[] copyInta = inta.clone();

                if (copyInta[i] == 0) {
                    copyInta[i] = 2;
                    printMatrix(copyInta);

                    int rowCounter = 0;
                    int colCounter = 0;
                    int diagCounter = 0;

                    for (int j = 0; j < 3; j++) {
                        if (checkRow(copyInta, j))
                            rowCounter++;
                    }

                    for (int j = 0; j < 3; j++) {
                        if (checkColumn(copyInta, j))
                            colCounter++;
                    }

                    if (checkDiagonal(copyInta))
                        diagCounter++;

                    computingScores[i] = rowCounter + colCounter + diagCounter;
                }
            }
        }

        System.out.println("Computing Scores for each position: ");
        // int max = 0;
        int max = Integer.MIN_VALUE;
        int pos = 0;

        /* for (i = 0; i < 9; i++) {
            if (inta[i] == 0) {
                System.out.println("Position " + (i + 1) + ": " + computingScores[i]);
                max = Math.max(computingScores[i] , 0);
                pos = computingScores[max];
            }
        } */

        for (i = 0; i < 9; i++) {
            if (inta[i] == 0) {
                System.out.println("Position " + (i + 1) + ": " + computingScores[i]);
                if (computingScores[i] > max) {
                    max = computingScores[i];
                    pos = i;
                }
            }
        }

        
       
        System.out.println("best move schore is :" + max + " position " + (pos+1));
        
    }

    private static void printMatrix(int[] array) {
        for (int i = 0; i < 9; i++) {
            if (array[i] == 1)
                System.out.print("X ");
            else if (array[i] == 2)
                System.out.print("O ");
            else
                System.out.print("0 ");

            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private static boolean checkRow(int[] array, int row) {
        int start = row * 3;
        int end = start + 2;

        int value = array[start];
        if (value == 0)
            return false;

        for (int i = start + 1; i <= end; i++) {
            if (array[i] != value)
                return false;
        }

        return true;
    }

    private static boolean checkColumn(int[] array, int col) {
        int start = col;
        int end = start + 6;

        int value = array[start];
        if (value == 0)
            return false;

        for (int i = start + 3; i <= end; i += 3) {
            if (array[i] != value)
                return false;
        }

        return true;
    }

    private static boolean checkDiagonal(int[] array) {
        if ((array[0] != 0 && array[0] == array[4] && array[0] == array[8]) ||
                (array[2] != 0 && array[2] == array[4] && array[2] == array[6]))
            return true;

        return false;
    }
}

