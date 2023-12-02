/* import java.util.Scanner;
//tic tac toe non ai implementation!
public class TicTacToe {
    private static final int MATRIX_SIZE = 9;

    static class Game {
        int[] matrix = new int[MATRIX_SIZE];
        int x_count; // Number of X.
        int y_count; // Number of O.
    }

    public static void initialize(Game game) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            game.matrix[i] = 0;
        }
        game.x_count = 0;
        game.y_count = 0;
    }

    public static boolean isValid(Game game) {
        if (Math.abs(game.x_count - game.y_count) > 1) {
            System.out.println("The board position is not valid.");
            return false;
        } else {
            System.out.println("This is a valid board position.");
            return true;
        }
    }

    public static int calculate(Game game) {
        int[] arr = new int[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            arr[i] = game.matrix[MATRIX_SIZE - 1 - i];
        }
        int sum = 0;
        int base = 1;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            sum = sum + arr[i] * base;
            base *= 3;
        }
        return sum;
    }

    public static void generateMove(Game game, int target) {
        int[][] move_matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        int i = 0;
        int m = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of empty positions: ");
        int empty_count = scanner.nextInt();
        while (i < empty_count) {
            int[] temp = new int[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                temp[j] = game.matrix[j];
            }
            if (temp[m] == 0) {
                temp[m] = target;
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    move_matrix[i][j] = temp[j];
                }
                i++;
            }
            m++;
        }
        System.out.println("All the possible moves for the current matrix position for target " + target + ":");
        for (int j = 0; j < empty_count; j++) {
            for (int k = 0; k < MATRIX_SIZE; k++) {
                System.out.print(move_matrix[j][k] + " ");
            }
            System.out.println();
        }
    }

    public static void calculateScore(Game game, int target) {
        int[] score = new int[MATRIX_SIZE];
        int[][][] reshaped_matrix = new int[MATRIX_SIZE][3][3];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            score[i] = 0;
        }
        int index = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (game.matrix[i] == 0) {
                int[] temp = new int[MATRIX_SIZE];
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    temp[j] = game.matrix[j];
                }
                temp[i] = target;
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        reshaped_matrix[index][j][k] = temp[j * 3 + k];
                    }
                }
                index++;
            }
        }

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                if (target == reshaped_matrix[i][j][0] || target == reshaped_matrix[i][j][1] || target == reshaped_matrix[i][j][2]) {
                    score[i]++;
                    break;
                }
            }
        }

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                if (target == reshaped_matrix[i][0][j] || target == reshaped_matrix[i][1][j] || target == reshaped_matrix[i][2][j]) {
                    score[i]++;
                    break;
                }
            }
        }

        int[][] diagonal_elements = new int[MATRIX_SIZE][3];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                diagonal_elements[i][j] = reshaped_matrix[i][j][j];
            }
        }

        int[][] opposite_diagonal_elements = new int[MATRIX_SIZE][3];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                opposite_diagonal_elements[i][j] = reshaped_matrix[i][j][2 - j];
            }
        }

        // System.out.print("Scores: ");
        // for (int i = 0; i < MATRIX_SIZE; i++) {
        //     System.out.print(score[i] + " ");
        // }
        // System.out.println();
    }

    public static void main(String[] args) {
        Game game = new Game();
        initialize(game);
        int target;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of X in the current board position: ");
        game.x_count = scanner.nextInt();
        System.out.print("Enter the number of O in the current board position: ");
        game.y_count = scanner.nextInt();

        for (int i = 0; i < game.x_count; i++) {
            int pos;
            System.out.print("Enter the position for X: ");
            pos = scanner.nextInt();
            game.matrix[pos] = 1;
        }

        for (int i = 0; i < game.y_count; i++) {
            int pos;
            System.out.print("Enter the position for O: ");
            pos = scanner.nextInt();
            if (game.matrix[pos] != 0) {
                System.out.println("Already occupied by X");
                i--;
                continue;
            }
            game.matrix[pos] = 2;
        }

        System.out.println("Matrix Representation of the current position:");
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println();
            }
            char temp = ' ';
            if (game.matrix[i] == 1) {
                temp = 'X';
            } else if (game.matrix[i] == 2) {
                temp = 'O';
            }
            System.out.print(temp + " ");
        }
        System.out.println();

        if (isValid(game)) {
            System.out.println("The value is :: " + calculate(game));
            System.out.print("Enter the target variable: ");
            target = scanner.nextInt();
            generateMove(game, target);
            calculateScore(game, target);
        }
    }
}
 */

import java.util.Scanner;

public class TicTacToe {
    private static final int MATRIX_SIZE = 9;
    private static int[] matrix = new int[MATRIX_SIZE];
    private static int x_count; // Number of X.
    private static int y_count; // Number of O.

    public static void initialize() {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            matrix[i] = 0;
        }
        x_count = 0;
        y_count = 0;
    }

    public static boolean isValid() {
        if (Math.abs(x_count - y_count) > 1) {
            System.out.println("The board position is not valid.");
            return false;
        } else {
            System.out.println("This is a valid board position.");
            return true;
        }
    }

    public static int calculate() {
        int[] arr = new int[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            arr[i] = matrix[MATRIX_SIZE - 1 - i];
        }
        int sum = 0;
        int base = 1;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            sum = sum + arr[i] * base;
            base *= 3;
        }
        return sum;
    }

    public static void generateMove(int target) {
        int[][] move_matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        int i = 0;
        int m = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of empty positions: ");
        int empty_count = scanner.nextInt();
        while (i < empty_count) {
            int[] temp = new int[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                temp[j] = matrix[j];
            }
            if (temp[m] == 0) {
                temp[m] = target;
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    move_matrix[i][j] = temp[j];
                }
                i++;
            }
            m++;
        }
        System.out.println("All the possible moves for the current matrix position for target " + target + ":");
        for (int j = 0; j < empty_count; j++) {
            for (int k = 0; k < MATRIX_SIZE; k++) {
                System.out.print(move_matrix[j][k] + " ");
            }
            System.out.println();
        }
    }

    public static void calculateScore(int target) {
        int[] score = new int[MATRIX_SIZE];
        int[][][] reshaped_matrix = new int[MATRIX_SIZE][3][3];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            score[i] = 0;
        }
        int index = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (matrix[i] == 0) {
                int[] temp = new int[MATRIX_SIZE];
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    temp[j] = matrix[j];
                }
                temp[i] = target;
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        reshaped_matrix[index][j][k] = temp[j * 3 + k];
                    }
                }
                index++;
            }
        }

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                if (target == reshaped_matrix[i][j][0] || target == reshaped_matrix[i][j][1] || target == reshaped_matrix[i][j][2]) {
                    score[i]++;
                    break;
                }
            }
        }

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                if (target == reshaped_matrix[i][0][j] || target == reshaped_matrix[i][1][j] || target == reshaped_matrix[i][2][j]) {
                    score[i]++;
                    break;
                }
            }
        }

        int[][] diagonal_elements = new int[MATRIX_SIZE][3];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                diagonal_elements[i][j] = reshaped_matrix[i][j][j];
            }
        }

        int[][] opposite_diagonal_elements = new int[MATRIX_SIZE][3];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                opposite_diagonal_elements[i][j] = reshaped_matrix[i][j][2 - j];
            }
        }

        // System.out.print("Scores: ");
        // for (int i = 0; i < MATRIX_SIZE; i++) {
        //     System.out.print(score[i] + " ");
        // }
        // System.out.println();
    }

    public static void main(String[] args) {
        initialize();
        int target;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of X in the current board position: ");
        x_count = scanner.nextInt();
        System.out.print("Enter the number of O in the current board position: ");
        y_count = scanner.nextInt();

        for (int i = 0; i < x_count; i++) {
            int pos;
            System.out.print("Enter the position for X: ");
            pos = scanner.nextInt();
            matrix[pos] = 1;
        }

        for (int i = 0; i < y_count; i++) {
            int pos;
            System.out.print("Enter the position for O: ");
            pos = scanner.nextInt();
            if (matrix[pos] != 0) {
                System.out.println("Already occupied by X");
                i--;
                continue;
            }
            matrix[pos] = 2;
        }

        System.out.println("Matrix Representation of the current position:");
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println();
            }
            char temp = ' ';
            if (matrix[i] == 1) {
                temp = 'X';
            } else if (matrix[i] == 2) {
                temp = 'O';
            }
            System.out.print(temp + " ");
        }
        System.out.println();

        if (isValid()) {
            System.out.println("The value is :: " + calculate());
            System.out.print("Enter the target variable: ");
            target = scanner.nextInt();
            generateMove(target);
            calculateScore(target);
        }
    }
}
