
import java.util.Arrays;
import java.util.Scanner;

public class RClass {
//    static int flag = 0;

    public static int signum(double net) {
        if (net > 0) {
            return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double c = 0.1;
        double E = Integer.MAX_VALUE;
        int cycle = 0;

        // double[][] T = { { 0.1, 0.1, 1 }, { 0.2, 0.1, 1 }, { 0.5, 0.1, 2 }, { 0.6, 0.1, 2 }, { 0.3, 0.3, 3 }, { 0.4, 0.3, 3 } };
        double[][] W = { { -0.1, 0.15, 0.2 }, { -0.2, 0.11, 0.17 }, { 0.17, 0.16, 0.11 } };
        int[][] D = { { 1, -1, -1 }, { -1, 1, -1 }, { -1, -1, 1 } };

        double[][] augmented = { { 0.1, 0.1, -1, 1 }, { 0.2, 0.1, -1, 1 }, { 0.5, 0.1, -1, 2 }, { 0.6, 0.1, -1, 2 }, { 0.3, 0.3, -1, 3 }, { 0.4, 0.3, -1, 3 } };

        int[] O = new int[3];
        double net = 0;
        while (E != 0) {
            cycle++;
            System.out.println("Cycle number: " + cycle);
            E = 0;
            for (int i = 0; i < 6; i++) {
                for (int k = 0; k < 3; k++) {
                    net = 0;
                    for (int j = 0; j < 3; j++) {
                        net += W[k][j] * augmented[i][j];
                    }
                    O[k] = signum(net);
                    int wno = (int) augmented[i][3] - 1;
                    if (D[wno][k] - O[k] != 0) {
                        for (int a = 0; a < 3; a++) {
                            W[k][a] = W[k][a] + 0.5 * c * (D[wno][k] - O[k]) * augmented[i][a];
                        }
                        E += 0.5 * Math.pow(D[wno][k] - O[k], 2);
                    }
                }
            }
            System.out.println("Cumulative Error after cycle " + cycle + ": " + E);
            System.out.println("Weight Matrix after cycle " + cycle + ":");
            for (int i = 0; i < 3; i++) {
                System.out.println(Arrays.toString(W[i]));
            }
        }
        System.out.println("Cycles required: " + cycle);
    }
}
