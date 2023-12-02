#include <iostream>
#include <cmath>

static int goal[3][3];
static int arr[4][2];
static int temp[3][3];
static int mov;
static float ans = 100;

float evaluate(const int goal[][3], const int current[][3])
{
    int dist = 0;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            dist += pow(goal[i][j] - current[i][j], 2);
        }
    }
    float hval = sqrt(dist);
    dist = 0;
    return hval;
}

void drawMatrix(int current[][3], int x, int y, int p, int q)
{
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (i == x && j == y)
            {
                temp[i][j] = current[p][q];
            }
            else if (i == p && j == q)
            {
                temp[i][j] = 0;
            }
            else
            {
                temp[i][j] = current[i][j];
            }
        }
    }
}

int findIndex(int i, int j, int arr[][2])
{
    int k = 0, cnt = 0;
    if (3 > (i - 1) && i - 1 >= 0 && 3 > j && j >= 0)
    {
        arr[k][0] = i - 1;
        arr[k][1] = j;
        k++;
        cnt++;
    }
    if (3 > i + 1 && i + 1 >= 0 && 3 > j && j >= 0)
    {
        arr[k][0] = i + 1;
        arr[k][1] = j;
        k++;
        cnt++;
    }
    if (3 > i && i >= 0 && 3 > (j + 1) && j + 1 >= 0)
    {
        arr[k][0] = i;
        arr[k][1] = j + 1;
        k++;
        cnt++;
    }
    if (3 > i && i >= 0 && 3 > (j - 1) && j - 1 >= 0)
    {
        arr[k][0] = i;
        arr[k][1] = j - 1;
        k++;
        cnt++;
    }
    mov = cnt;
    return mov;
}

int main()
{
    std::cout << "Enter goal board state: ";
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            std::cin >> goal[i][j];
        }
    }

    std::cout << "Enter initial board state: ";
    int current[3][3];
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            std::cin >> current[i][j];
        }
    }

    ans = evaluate(goal, current);
    std::cout << "Initial Hvalue: " << ans << std::endl;

    if (ans == 0)
    {
        std::cout << "Initial state is the goal state." << std::endl;
    }
    else
    {
        int x = -1, y = -1;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (current[i][j] == 0)
                {
                    x = i;
                    y = j;
                }
            }
        }

        mov = findIndex(x, y, arr);
        float bestHval = ans;
        int betterState[3][3];

        for (int i = 0; i < mov; i++)
        {
            drawMatrix(current, x, y, arr[i][0], arr[i][1]);
            float tans = evaluate(goal, temp);

            if (tans < bestHval)
            {
                bestHval = tans;
                for (int p = 0; p < 3; p++)
                {
                    for (int q = 0; q < 3; q++)
                    {
                        betterState[p][q] = temp[p][q];
                    }
                }
                break;
            }
        }

        if (bestHval >= ans)
        {
            std::cout << "No operator left" << std::endl;
        }

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                current[i][j] = betterState[i][j];
            }
        }

        ans = bestHval;
        std::cout << "Current Hvalue: " << ans << std::endl;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                std::cout << current[i][j] << " ";
            }
            std::cout << std::endl;
        }

        if (ans == 0)
        {
            std::cout << "Goal state reached." << std::endl;
        }
    }

    return 0;
}