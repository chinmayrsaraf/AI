#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>

int sgn(float net)
{
    if (net > 0)
    {
        return 1;
    }
    else
    {
        return -1;
    }
}

int main()
{
    int n, m;
    printf("Enter number of input vectors: ");
    scanf("%d", &n);
    printf("Enter number of features: ");
    scanf("%d", &m);

    float X[n][m];
    float W[m];
    printf("Enter input vectors:\n");
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            scanf("%f", &X[i][j]);
        }
    }

    printf("Enter weight vector:\n");
    for (int j = 0; j < m; j++)
    {
        scanf("%f", &W[j]);
    }

    int d[n];
    printf("Enter desired vector:\n");
    for (int j = 0; j < n; j++)
    {
        scanf("%d", &d[j]);
    }

    float c = 1, net;
    int o;
    double E = INT_MAX;
    int cycle = 0;

    while (fabs(E) > 0)
    {
        cycle++;
        E = 0;

        for (int i = 0; i < n; i++)
        {
            net = 0;

            for (int j = 0; j < m; j++)
            {
                net += W[j] * X[i][j];
            }

            o = sgn(net);
            E += fabs(d[i] - o); // o is output

            if (d[i] - o != 0)
            {
                printf("\nupdated weight vector:\n");
                for (int j = 0; j < m; j++)
                {
                    W[j] = W[j] + c * (d[i] - o) * X[i][j];
                    printf("%f ", W[j]);
                }
                printf("\n");
            }
        }
        printf("error: %f\n", E);
    }

    printf("\nnumber of cycles required: %d\n", cycle);
    return 0;
}
