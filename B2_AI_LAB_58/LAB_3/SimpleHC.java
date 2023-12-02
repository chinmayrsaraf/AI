import java.util.Scanner;

public class SimpleHC {
    static int[][] goal = new int[3][3];
    static int[][] temp = new int[3][3];
    static int[][] arr = new int[4][2];
    static int mov;
    static float ans = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("entre the current state of bourd:");
        int[][] current = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                current[i][j] = sc.nextInt();
            }
        }
        System.out.println("entre the goal state of bourd:");
        // int[][] current = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                goal[i][j] = sc.nextInt();
            }
        }
        ans = Evaluate(goal, current);
        System.out.println("initial Hval: "+ ans);
        if (ans == 0) {
            System.out.println("your goal state is only ur intial state!");
        } else {
            int x = -1, y = -1;// finding the position of 0
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (current[i][j] == 0) {
                        x = i;
                        y = j;
                    }
                }
            }
            mov = findIndex(x, y, arr);
            float bestHval = ans;
            int[][] betterState = current;
            for (int i = 0; i < mov; i++) {
                DrawMatrix(x, y, arr[i][0], arr[i][1], current);
                float tem = Evaluate(goal, temp);
                if (tem < bestHval) {
                    bestHval = tem;
                    betterState = temp;
                    break;
                }
            }
            
            current = betterState;
            ans = bestHval;
            System.out.println("Current Hvalue: " + ans);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(current[i][j] + " ");
                }
                System.out.println();
            }
            if (ans == 0) {
                System.out.println("Goal state reached.");
                // break;
            }
        }

    }

    static void DrawMatrix(int i, int j, int p, int q, int current[][]) {
        for (int k = 0; k < current.length; k++) {
            for (int k2 = 0; k2 < current.length; k2++) {
                if (k == i && k2 == j) {
                    temp[k][k2] = current[p][q];
                } else if (k == p && k2 == q) {
                    temp[k][k2] = 0;
                } else {
                    temp[k][k2] = current[k][k2];
                }
            }

        }

    }

    static float Evaluate(int[][] goal, int[][] current) {
        float distance = 0;
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current.length; j++) {
                distance = (float) (distance + Math.pow(goal[i][j] - current[i][j], 2));
            }
        }
        float Hval = (float) Math.sqrt(distance);

        distance = 0;
        return Hval;
    }

    static int findIndex(int i, int j, int[][] arr) {
        int k = 0, cnt = 0;
        if ((3 > (i - 1) && i - 1 >= 0) && (3 > j && j >= 0)) {
            arr[k][0] = i - 1;
            arr[k][1] = j;
            k++;
            cnt++;
        }
        if (3 > i + 1 && i + 1 >= 0 && 3 > j && j >= 0) {
            arr[k][0] = i + 1;
            arr[k][1] = j;
            k++;
            cnt++;
        }
        if ((3 > i && i >= 0) && (3 > (j + 1) && j + 1 >= 0)) {
            arr[k][0] = i;
            arr[k][1] = j + 1;
            k++;
            cnt++;
        }
        if ((3 > i && i >= 0) && (3 > j - 1 && j - 1 >= 0)) {
            arr[k][0] = i;
            arr[k][1] = j - 1;
            k++;
            cnt++;
        }
        mov = cnt;
        return mov;
    }

}


