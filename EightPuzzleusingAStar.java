import java.util.*;

class PuzzleNode {
    int[][] state;
    PuzzleNode parent;
    int cost;
    int heuristic;

    public PuzzleNode(int[][] state, PuzzleNode parent, int cost, int heuristic) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
        this.heuristic = heuristic;
    }
}

class PuzzleComparator implements Comparator<PuzzleNode> {
    @Override
    public int compare(PuzzleNode node1, PuzzleNode node2) {
        return Integer.compare(node1.cost + node1.heuristic, node2.cost + node2.heuristic);
    }
}

public class EightPuzzleusingAStar {
    static int[][] goalState;
    static int[][] initialState;

    static int calculateHeuristic(int[][] state) {
        int h = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != goalState[i][j]) {
                    h++;
                }
            }
        }
        return h;
    }

    static void printState(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the initial state
        System.out.println("Enter the initial state (3x3 matrix):");
        initialState = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initialState[i][j] = scanner.nextInt();
            }
        }

        // Input the goal state
        System.out.println("Enter the goal state (3x3 matrix):");
        goalState = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                goalState[i][j] = scanner.nextInt();
            }
        }

        PriorityQueue<PuzzleNode> openList = new PriorityQueue<>(new PuzzleComparator());
        Set<String> closedSet = new HashSet<>();

        PuzzleNode initialNode = new PuzzleNode(initialState, null, 0, calculateHeuristic(initialState));
        openList.add(initialNode);

        int moves = 0;
        while (!openList.isEmpty() && moves <= 70) {
            PuzzleNode currentNode = openList.poll();
            closedSet.add(Arrays.deepToString(currentNode.state));

            if (Arrays.deepEquals(currentNode.state, goalState)) {
                // Goal state reached
                LinkedList<PuzzleNode> path = new LinkedList<>();
                while (currentNode != null) {
                    path.addFirst(currentNode);
                    currentNode = currentNode.parent;
                }

                for (PuzzleNode node : path) {
                    printState(node.state);
                    System.out.println("Heuristic: " + node.heuristic);
                }
                break;
            }

            // Expand nodes
            int[][] Moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

            // Find the coordinates of the empty cell
            int emptyX = -1;
            int emptyY = -1;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentNode.state[i][j] == 0) {
                        emptyX = i;
                        emptyY = j;
                        break;
                    }
                }
            }

            // Store all possible moves and their associated heuristics
            List<PuzzleNode> possibleMoves = new ArrayList<>();

            for (int[] move : Moves) {
                int newX = emptyX + move[0];
                int newY = emptyY + move[1];

                if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                    int[][] newState = new int[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[i][j] = currentNode.state[i][j];
                        }
                    }

                    // Swap the empty cell with the adjacent cell
                    newState[emptyX][emptyY] = newState[newX][newY];
                    newState[newX][newY] = 0;

                    // Calculate the cost and heuristic for the new state
                    int newCost = currentNode.cost + 1;
                    int newHeuristic = calculateHeuristic(newState);

                    PuzzleNode newNode = new PuzzleNode(newState, currentNode, newCost, newHeuristic);
                    possibleMoves.add(newNode);
                }
            }

            // Sort the possible moves based on the heuristic value
            possibleMoves.sort(Comparator.comparingInt(node -> node.heuristic));

            // Select the best move (lowest heuristic value)
            PuzzleNode bestMove = possibleMoves.get(0);

            // Print the selected move
            System.out.println("Selected Move:");
            printState(bestMove.state);
            System.out.println("Heuristic: " + bestMove.heuristic);
            System.out.println("Cost (g()): " + bestMove.cost);

            // Add the best move to the open list
            openList.add(bestMove);

            moves++;
        }
    }
}
