#include <iostream>
#include <cmath>
using namespace std;

int goal[3][3];
int arr[4][2];

int mov;
float ans = 100;

float calHeuristic(int goal[3][3], int current[3][3])
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

void drawMatrix(int current[3][3], int x, int y, int p, int q, int temp[3][3])
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

int findIndex(int i, int j, int arr[4][2])
{
    int k = 0, cnt = 0;
    if ((3 > (i - 1) && i - 1 >= 0) && (3 > j && j >= 0))
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
    if ((3 > i && i >= 0) && (3 > (j + 1) && j + 1 >= 0))
    {
        arr[k][0] = i;
        arr[k][1] = j + 1;
        k++;
        cnt++;
    }
    if ((3 > i && i >= 0) && (3 > j - 1 && j - 1 >= 0))
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
    cout << "Enter initial board state: ";
    int current[3][3];
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cin >> current[i][j];
        }
    }

    cout << "Enter goal board state: ";
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cin >> goal[i][j];
        }
    }

    ans = calHeuristic(goal, current);
    cout << "Initial value: " << ans << endl;
    if (ans == 0)
    {
        cout << "Initial state is the goal state." << endl;
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
        int SUCC[3][3];
        for (int i = 0; i < mov; i++)
        {
            int temp[3][3];
            drawMatrix(current, x, y, arr[i][0], arr[i][1], temp);
            float tans = calHeuristic(goal, temp);
            cout << "value " << tans << endl;
            for (int k = 0; k < 3; k++)
            {
                for (int j = 0; j < 3; j++)
                {
                    cout << temp[k][j] << " ";
                }
                cout << endl;
            }
            if (tans < bestHval)
            {
                bestHval = tans;
                for (int m = 0; m < 3; m++)
                {
                    for (int n = 0; n < 3; n++)
                    {
                        SUCC[m][n] = temp[m][n];
                    }
                }
            }
        }
        if (bestHval >= ans)
        {
            cout << "No operator left" << endl;
        }
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                current[i][j] = SUCC[i][j];
            }
        }
        ans = bestHval;
        cout << "\nBest move: " << endl;
        cout << "Current value: " << ans << endl;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cout << current[i][j] << " ";
            }
            cout << endl;
        }
        if (ans == 0)
        {
            cout << "Goal state reached." << endl;
        }
    }
    return 0;
}
