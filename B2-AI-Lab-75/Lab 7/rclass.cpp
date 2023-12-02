#include <iostream>
#include <vector>
#include <cmath>

int signum(double net)
{
    if (net > 0)
    {
        return 1;
    }
    return -1;
}

int main()
{
    double c = 0.1;
    double E = INT_MAX;
    int cycle = 0;

    double T[6][3] = {{0.1, 0.1, 1}, {0.2, 0.1, 1}, {0.5, 0.1, 2}, {0.6, 0.1, 2}, {0.3, 0.3, 3}, {0.4, 0.3, 3}};
    double W[3][3] = {{-0.1, 0.15, 0.2}, {-0.2, 0.11, 0.17}, {0.17, 0.16, 0.11}};
    int D[3][3] = {{1, -1, -1}, {-1, 1, -1}, {-1, -1, 1}};

    double augmented[6][4] = {{0.1, 0.1, -1, 1}, {0.2, 0.1, -1, 1}, {0.5, 0.1, -1, 2}, {0.6, 0.1, -1, 2}, {0.3, 0.3, -1, 3}, {0.4, 0.3, -1, 3}};

    int O[3];
    double net = 0;

    while (E != 0)
    {
        cycle++;
        std::cout << "Cycle number: " << cycle << std::endl;
        E = 0;

        for (int i = 0; i < 6; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                net = 0;
                for (int j = 0; j < 3; j++)
                {
                    net += W[k][j] * augmented[i][j];
                }
                O[k] = signum(net);
                int wno = static_cast<int>(augmented[i][3]) - 1;
                if (D[wno][k] - O[k] != 0)
                {
                    for (int a = 0; a < 3; a++)
                    {
                        W[k][a] = W[k][a] + 0.5 * c * (D[wno][k] - O[k]) * augmented[i][a];
                    }
                    E += 0.5 * pow(D[wno][k] - O[k], 2);
                }
            }
        }

        std::cout << "Cumulative Error after cycle " << cycle << ": " << E << std::endl;
        std::cout << "Weight Matrix after cycle " << cycle << ":" << std::endl;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                std::cout << W[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }

    std::cout << "Cycles required: " << cycle << std::endl;

    return 0;
}