/*  import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class A_star {
    static Set<String> visitedStates = new HashSet<>();
    // static int[] goalState = {1, 2, 3, 8, 0, 4, 7, 6, 5};
    static int[] goalState = { 2, 3, 4, 6, 5, 0, 1, 8, 7 };
    static int totalMoves = 70;
    static int expanded = 0;
    static int count = 0;

    static class Node {
        int[] state;
        Node parent;
        String operator;
        int depth;
        int cost;

        public Node(int[] state, Node parent, String operator, int depth, int cost) {
            this.state = state;
            this.parent = parent;
            this.operator = operator;
            this.depth = depth;
            this.cost = cost;
        }
    }

    public static Node createNode(int[] state, Node parent, String operator, int depth, int cost) {
        return new Node(state, parent, operator, depth, cost);
    }

    public static List<Node> expandNode(Node node) {
        List<Node> expandedNodes = new ArrayList<>();

        int[] tempState;
        Node tempNode;

        tempState = moveDown(node.state);
        tempNode = createNode(tempState, node, "down", node.depth + 1, node.cost + 1);
        expandedNodes.add(tempNode);

        tempState = moveUp(node.state);
        tempNode = createNode(tempState, node, "up", node.depth + 1, node.cost + 1);
        expandedNodes.add(tempNode);

        tempState = moveLeft(node.state);
        tempNode = createNode(tempState, node, "left", node.depth + 1, node.cost + 1);
        expandedNodes.add(tempNode);

        tempState = moveRight(node.state);
        tempNode = createNode(tempState, node, "right", node.depth + 1, node.cost + 1);
        expandedNodes.add(tempNode);

        return expandedNodes;
    }

    public static int[] moveLeft(int[] state) {
        int[] swap = Arrays.copyOf(state, state.length);
        int idx = findBlankIndex(swap);

        if (idx == 0 || idx == 3 || idx == 6) {
            return swap;
        } else {
            swap[idx - 1] = state[idx];
            swap[idx] = state[idx - 1];
            return swap;
        }
    }

    public static int[] moveRight(int[] state) {
        int[] swap = Arrays.copyOf(state, state.length);
        int idx = findBlankIndex(swap);

        if (idx == 2 || idx == 5 || idx == 8) {
            return swap;
        } else {
            swap[idx + 1] = state[idx];
            swap[idx] = state[idx + 1];
            return swap;
        }
    }

    public static int[] moveUp(int[] state) {
        int[] swap = Arrays.copyOf(state, state.length);
        int idx = findBlankIndex(swap);

        if (idx == 0 || idx == 1 || idx == 2) {
            return swap;
        } else {
            swap[idx - 3] = state[idx];
            swap[idx] = state[idx - 3];
            return swap;
        }
    }

    public static int[] moveDown(int[] state) {
        int[] swap = Arrays.copyOf(state, state.length);
        int idx = findBlankIndex(swap);

        if (idx == 6 || idx == 7 || idx == 8) {
            return swap;
        } else {
            swap[idx + 3] = state[idx];
            swap[idx] = state[idx + 3];
            return swap;
        }
    }

    public static int findBlankIndex(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static Node bfs(int[] start, int[] goal) {
        if (Arrays.equals(start, goal)) {
            return null;
        } else {
            Queue<Node> toBeExpanded = new LinkedList<>();
            Node currentNode = createNode(start, null, null, 0, 0);
            toBeExpanded.add(currentNode);// storing evry state in linked list!

            for (int i = 0; i < totalMoves; i++) {
                List<Node> tempExpanded = new ArrayList<>();
                int size = toBeExpanded.size();

                for (int j = 0; j < size; j++) {
                    Node expandedNode = toBeExpanded.poll();//to remove head elemrnt of the queue!
                    String stateString = Arrays.toString(expandedNode.state);

                    if (visitedStates.contains(stateString)) {
                        continue;
                    }

                    List<Node> nodeArray = expandNode(expandedNode);

                    for (int x = 0; x < 4; x++) {
                        if (Arrays.equals(nodeArray.get(x).state, goal)) {
                            count = i + 1;
                            System.out.println("\nGOAL STATE FOUND!!! " + Arrays.toString(nodeArray.get(x).state));
                            return nodeArray.get(x);
                        } else {
                            tempExpanded.add(nodeArray.get(x));
                            visitedStates.add(stateString);
                        }
                    }
                }

                toBeExpanded.clear();
                toBeExpanded.addAll(tempExpanded);
                tempExpanded.clear();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        String board = "1,2,3,8,0,4,7,6,5"; // Updated initial state of the A_star.
        String[] boardSplit = board.split(",");
        int[] startingState = new int[boardSplit.length];
        for (int i = 0; i < boardSplit.length; i++) {
            startingState[i] = Integer.parseInt(boardSplit[i]);
        }

        System.out.println("_________ Printing state _________");
        System.out.println(Arrays.toString(startingState));

        if (startingState.length == 9) {
            Node result = bfs(startingState, goalState);
            if (result == null) {
                System.out.println("No solution found");
            } else {
                System.out.println("\nTotal number of moves needed = " + result.cost);
                List<int[]> path = new ArrayList<>();
                path.add(result.state);
                Node current = result;
                boolean flag = true;

                while (flag) {
                    Node parent = current.parent;
                    int[] prevState = parent.state;
                    path.add(prevState);
                    current = parent;

                    if (Arrays.equals(prevState, startingState)) {
                        flag = false;
                    }
                }

                for (int[] state : path) {
                    System.out.println(state[0] + " | " + state[1] + " | " + state[2]);
                    System.out.println(state[3] + " | " + state[4] + " | " + state[5]);
                    System.out.println(state[6] + " | " + state[7] + " | " + state[8]);
                    System.out.println();
                }
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}
 

 */


// ..................................................................................................



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class A_star {
    static Set<String> visitedStates = new HashSet<>();
    static int[] goalState = {2, 3, 4, 6, 5, 1, 0, 8, 7};
    static int totalMoves = 70;

    static class Node {
        int[] state;
        Node parent;
        String operator;
        int depth;
        int cost;

        public Node(int[] state, Node parent, String operator, int depth, int cost) {
            this.state = state;
            this.parent = parent;
            this.operator = operator;
            this.depth = depth;
            this.cost = cost;
        }
    }

    public static Node createNode(int[] state, Node parent, String operator, int depth, int cost) {
        return new Node(state, parent, operator, depth, cost);
    }

    public static List<Node> expandNode(Node node) {
        List<Node> expandedNodes = new ArrayList<>();

        int[] tempState;
        Node tempNode;

        tempState = moveDown(node.state);
        tempNode = createNode(tempState, node, "down", node.depth + 1, node.depth + 1 + heuristic(tempState));
        expandedNodes.add(tempNode);

        tempState = moveUp(node.state);
        tempNode = createNode(tempState, node, "up", node.depth + 1, node.depth + 1 + heuristic(tempState));
        expandedNodes.add(tempNode);

        tempState = moveLeft(node.state);
        tempNode = createNode(tempState, node, "left", node.depth + 1, node.depth + 1 + heuristic(tempState));
        expandedNodes.add(tempNode);

        tempState = moveRight(node.state);
        tempNode = createNode(tempState, node, "right", node.depth + 1, node.depth + 1 + heuristic(tempState));
        expandedNodes.add(tempNode);

        return expandedNodes;
    }

    public static int heuristic(int[] state) {
        int sum = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] != 0) {
                int goalIndex = Arrays.asList(goalState).indexOf(state[i]);
                int goalRow = goalIndex / 3;
                int goalCol = goalIndex % 3;
                int currentRow = i / 3;
                int currentCol = i % 3;
                sum += Math.abs(goalRow - currentRow) + Math.abs(goalCol - currentCol);
            }
        }
        return sum;
    }

    public static int[] moveLeft(int[] state) {
        int[] swap = Arrays.copyOf(state, state.length);
        int idx = findBlankIndex(swap);

        if (idx == 0 || idx == 3 || idx == 6) {
            return swap;
        } else {
            swap[idx - 1] = state[idx];
            swap[idx] = state[idx - 1];
            return swap;
        }
    }

    public static int[] moveRight(int[] state) {
        int[] swap = Arrays.copyOf(state, state.length);
        int idx = findBlankIndex(swap);

        if (idx == 2 || idx == 5 || idx == 8) {
            return swap;
        } else {
            swap[idx + 1] = state[idx];
            swap[idx] = state[idx + 1];
            return swap;
        }
    }

    public static int[] moveUp(int[] state) {
        int[] swap = Arrays.copyOf(state, state.length);
        int idx = findBlankIndex(swap);

        if (idx == 0 || idx == 1 || idx == 2) {
            return swap;
        } else {
            swap[idx - 3] = state[idx];
            swap[idx] = state[idx - 3];
            return swap;
        }
    }

    public static int[] moveDown(int[] state) {
        int[] swap = Arrays.copyOf(state, state.length);
        int idx = findBlankIndex(swap);

        if (idx == 6 || idx == 7 || idx == 8) {
            return swap;
        } else {
            swap[idx + 3] = state[idx];
            swap[idx] = state[idx + 3];
            return swap;
        }
    }

    public static int findBlankIndex(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static Node bfs(int[] start, int[] goal) {
        if (Arrays.equals(start, goal)) {
            return null;
        } else {
            Queue<Node> toBeExpanded = new LinkedList<>();
            Node currentNode = createNode(start, null, null, 0, 0);
            toBeExpanded.add(currentNode);// storing evry state in linked list!
            int count = 0;

            for (int i = 0; i < totalMoves; i++) {
                List<Node> tempExpanded = new ArrayList<>();
                int size = toBeExpanded.size();

                for (int j = 0; j < size; j++) {
                    Node expandedNode = toBeExpanded.poll();//to remove head elemrnt of the queue!
                    String stateString = Arrays.toString(expandedNode.state);

                    if (visitedStates.contains(stateString)) {
                        continue;
                    }

                    List<Node> nodeArray = expandNode(expandedNode);

                    for (int x = 0; x < 4; x++) {
                        if (Arrays.equals(nodeArray.get(x).state, goal)) {
                            count = i + 1;
                            System.out.println("\nGOAL STATE FOUND!!! " + Arrays.toString(nodeArray.get(x).state));
                            return nodeArray.get(x);
                        } else {
                            tempExpanded.add(nodeArray.get(x));
                            visitedStates.add(stateString);
                        }
                    }
                }

                toBeExpanded.clear();
                toBeExpanded.addAll(tempExpanded);
                tempExpanded.clear();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        String board = "1,2,3,8,0,4,7,6,5"; // Updated initial state of the A_star.
        String[] boardSplit = board.split(",");
        int[] startingState = new int[boardSplit.length];
        for (int i = 0; i < boardSplit.length; i++) {
            startingState[i] = Integer.parseInt(boardSplit[i]);
        }

        System.out.println("_________ Printing state _________");
        System.out.println(Arrays.toString(startingState));

        if (startingState.length == 9) {
            Node result = bfs(startingState, goalState);
            if (result == null) {
                System.out.println("No solution found");
            } else {
                System.out.println("\nTotal number of moves needed = " + result.cost);
                List<int[]> path = new ArrayList<>();
                path.add(result.state);
                Node current = result;
                boolean flag = true;

                while (flag) {
                    Node parent = current.parent;
                    int[] prevState = parent.state;
                    path.add(prevState);
                    current = parent;

                    if (Arrays.equals(prevState, startingState)) {
                        flag = false;
                    }
                }

                for (int[] state : path) {
                    System.out.println(state[0] + " | " + state[1] + " | " + state[2]);
                    System.out.println(state[3] + " | " + state[4] + " | " + state[5]);
                    System.out.println(state[6] + " | " + state[7] + " | " + state[8]);
                    System.out.println();
                }
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}





