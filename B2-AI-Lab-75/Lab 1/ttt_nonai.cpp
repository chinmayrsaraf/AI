#include <iostream>
#include <vector>

#define MATRIX_SIZE 9

struct Game
{
    int matrix[MATRIX_SIZE];
    int x_count;
    int y_count;
};

void initialize(Game &game)
{
    for (int i = 0; i < MATRIX_SIZE; i++)
    {
        game.matrix[i] = 0;
    }
    game.x_count = 0;
    game.y_count = 0;
}

bool isValid(const Game &game)
{
    if (abs(game.x_count - game.y_count) > 1)
    {
        std::cout << "The board position is not valid." << std::endl;
        return false;
    }
    else
    {
        std::cout << "This is a valid board position." << std::endl;
        return true;
    }
}

int calculate(const Game &game)
{
    int arr[MATRIX_SIZE];
    for (int i = 0; i < MATRIX_SIZE; i++)
    {
        arr[i] = game.matrix[MATRIX_SIZE - 1 - i];
    }
    int count = 0;
    int sum = 0;
    int base = 1;
    for (int i = 0; i < MATRIX_SIZE; i++)
    {
        sum = sum + arr[i] * base;
        count++;
        base *= 3;
    }
    return sum;
}

void generateMove(Game &game, int target)
{
    int move_matrix[MATRIX_SIZE][MATRIX_SIZE];
    int i = 0;
    int m = 0;
    std::cout << "Enter the number of empty positions: ";
    int empty_count;
    std::cin >> empty_count;
    while (i < empty_count)
    {
        int temp[MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++)
        {
            temp[j] = game.matrix[j];
        }
        if (temp[m] == 0)
        {
            temp[m] = target;
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                move_matrix[i][j] = temp[j];
            }
            i++;
        }
        m++;
    }
    std::cout << "All the possible moves for the current matrix position for target " << target << ":" << std::endl;
    for (int j = 0; j < empty_count; j++)
    {
        for (int k = 0; k < MATRIX_SIZE; k++)
        {
            std::cout << move_matrix[j][k] << " ";
        }
        std::cout << std::endl;
    }
}

void calculateScore(const Game &game, int target)
{
    std::vector<int> score(MATRIX_SIZE, 0);
    int reshaped_matrix[MATRIX_SIZE][3][3];
    int index = 0;
    for (int i = 0; i < MATRIX_SIZE; i++)
    {
        if (game.matrix[i] == 0)
        {
            int temp[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                temp[j] = game.matrix[j];
            }
            temp[i] = target;
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    reshaped_matrix[index][j][k] = temp[j * 3 + k];
                }
            }
            index++;
        }
    }

    for (int i = 0; i < MATRIX_SIZE; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (target == reshaped_matrix[i][j][0] || target == reshaped_matrix[i][j][1] || target == reshaped_matrix[i][j][2])
            {
                score[i]++;
                break;
            }
        }
    }

    for (int i = 0; i < MATRIX_SIZE; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (target == reshaped_matrix[i][0][j] || target == reshaped_matrix[i][1][j] || target == reshaped_matrix[i][2][j])
            {
                score[i]++;
                break;
            }
        }
    }
}

int main()
{
    Game game;
    initialize(game);
    int target;
    std::cout << "Enter the number of X in the current board position: ";
    std::cin >> game.x_count;
    std::cout << "Enter the number of O in the current board position: ";
    std::cin >> game.y_count;

    for (int i = 0; i < game.x_count; i++)
    {
        int pos;
        std::cout << "Enter the position for X: ";
        std::cin >> pos;
        game.matrix[pos] = 1;
    }

    for (int i = 0; i < game.y_count; i++)
    {
        int pos;
        std::cout << "Enter the position for O: ";
        std::cin >> pos;
        if (game.matrix[pos] != 0)
        {
            std::cout << "Already occupied by X" << std::endl;
            i--;
            continue;
        }
        game.matrix[pos] = 2;
    }

    std::cout << "Matrix Representation of the current position:" << std::endl;
    for (int i = 0; i < MATRIX_SIZE; i++)
    {
        if (i % 3 == 0 && i != 0)
        {
            std::cout << std::endl;
        }
        char temp = ' ';
        if (game.matrix[i] == 1)
        {
            temp = 'X';
        }
        else if (game.matrix[i] == 2)
        {
            temp = 'O';
        }
        std::cout << temp << " ";
    }
    std::cout << std::endl;

    if (isValid(game))
    {
        std::cout << "The score is: " << calculate(game) << std::endl;
        std::cout << "Enter the target variable: ";
        std::cin >> target;
        generateMove(game, target);
        calculateScore(game, target);
    }

    return 0;
}
