import java.util.*;

class PuzzleState {
    private int[][] board;
    int emptyRow;
    int emptyCol;

    public PuzzleState(int[][] board) {
        this.board = board;
        findEmptyTile();
    }

    private void findEmptyTile() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    emptyRow = row;
                    emptyCol = col;
                    return;
                }
            }
        }
    }

    public PuzzleState generateNeighbor(int newRow, int newCol) {
        int[][] newBoard = deepCopy(board);
        swap(newBoard, emptyRow, emptyCol, newRow, newCol);
        return new PuzzleState(newBoard);
    }

    boolean isValidMove(int row, int col) {
        return row >= 0 && col >= 0 && row < 3 && col < 3;
    }

    private int[][] deepCopy(int[][] array) {
        int[][] newArray = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i].clone();
        }
        return newArray;
    }

    private void swap(int[][] array, int r1, int c1, int r2, int c2) {
        int temp = array[r1][c1];
        array[r1][c1] = array[r2][c2];
        array[r2][c2] = temp;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getHeuristic() {
        int[][] goalState = {
                {1, 2, 3},
                {8, 0, 4},
                {7, 6, 5}
        };

        int distance = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                
                if(board[row][col]!=goalState[row][col]){
                    distance++;
                }
                
            }
        }
        return distance;
    }
}

public class SteepestHillClimbing {
    private static final int[] dr = {0, 0, -1, 1}; 
    private static final int[] dc = {-1, 1, 0, 0}; 

    public static void main(String[] args) {
        int[][] initialBoard = new int[3][3];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the initial state of the 8-puzzle:");

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                initialBoard[row][col] = scanner.nextInt();
            }
        }

        PuzzleState initialState = new PuzzleState(initialBoard);
        solveEightPuzzle(initialState);
    }

    public static void solveEightPuzzle(PuzzleState initialState) {
        System.out.println("Initial State:");
        printBoard(initialState);
        int g=0;

        PuzzleState currentState = initialState;
        while (true) {
            int[][] currentBoard = currentState.getBoard();
            int currentHeuristic = currentState.getHeuristic();

            PuzzleState bestNeighbor = null;
            int bestNeighborHeuristic = Integer.MAX_VALUE;

            for (int move = 0; move < 4; move++) {
                int newRow = currentState.emptyRow + dr[move];
                int newCol = currentState.emptyCol + dc[move];

                if (currentState.isValidMove(newRow, newCol)) {
                    PuzzleState neighbor = currentState.generateNeighbor(newRow, newCol);
                    int neighborHeuristic = neighbor.getHeuristic();
                    if (neighborHeuristic < bestNeighborHeuristic) {
                        bestNeighbor = neighbor;
                        bestNeighborHeuristic = neighborHeuristic;
                    }
                }
            }
            g++;
            System.out.println(g);
            int f = currentState.getHeuristic() + g;
            if (bestNeighbor == null || bestNeighborHeuristic >= currentHeuristic) {
                System.out.println("\nLocal minimum reached. Stopping hill climbing.");
                break;
            }

            currentState = bestNeighbor;
            System.out.println("\nNext State (Heuristic: " + currentState.getHeuristic() + "):");
            printBoard(currentState);

            if (currentState.getHeuristic() == 0) {
                System.out.println("\nGoal state reached!");
                break;
            }
        }
    }

    private static void printBoard(PuzzleState state) {
        int[][] board = state.getBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
