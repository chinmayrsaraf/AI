#include <stdio.h>
#include <stdlib.h>

#define MATRIX_SIZE 9

typedef struct {
    int matrix[MATRIX_SIZE];
    int x_count; //these are number of x.
    int y_count; //these are number of o.
} Game;

void initialize(Game *game) {
    for (int i = 0; i < MATRIX_SIZE; i++) {
        game->matrix[i] = 0;
    }
    game->x_count = 0;
    game->y_count = 0;
}

int isValid(const Game *game) {
    if (abs(game->x_count - game->y_count) > 1) {
        printf("The board position is not valid.\n");
        return 0;
    } else {
        printf("This is a valid board position.\n");
        return 1;
    }
}

int calculate(const Game *game) {
    int arr[MATRIX_SIZE];
    for (int i = 0; i < MATRIX_SIZE; i++) {
        arr[i] = game->matrix[MATRIX_SIZE - 1 - i];
    }
    int count = 0;
    int sum = 0;
    int base = 1;
    for (int i = 0; i < MATRIX_SIZE; i++) {
        sum = sum + arr[i] * base;
        count++;
        base *= 3;
    }
    return sum;
}

/* void view(const Game *game) {
    printf("Matrix Representation of the current position:\n");
    for (int i = 0; i < MATRIX_SIZE; i++) {
        if (i % 3 == 0 && i != 0) {
            printf("\n");
        }
        char temp = ' ';
        if (game->matrix[i] == 1) {
            temp = 'X';
        } else if (game->matrix[i] == 2) {
            temp = 'O';
        }
        printf("%c ", temp);
    }
    printf("\n");
} */

void generateMove(Game *game, int target) {
    int move_matrix[MATRIX_SIZE][MATRIX_SIZE];
    int i = 0;
    int m = 0;
    printf("Enter the number of empty positions: ");
    int empty_count;
    scanf("%d", &empty_count);
    while (i < empty_count) {
        int temp[MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++) {
            temp[j] = game->matrix[j];
        }
        if (temp[m] == 0) {
            temp[m] = target;
            for (int j = 0; j < MATRIX_SIZE; j++) {
                move_matrix[i][j] = temp[j];
            }
            i++;
        }
        m++;
    }
    printf("All the possible moves for the current matrix position for target %d:\n", target);
    for (int j = 0; j < empty_count; j++) {
        for (int k = 0; k < MATRIX_SIZE; k++) {
            printf("%d ", move_matrix[j][k]);
        }
        printf("\n");
    }
}

void calculateScore(const Game *game, int target) {
    int score[MATRIX_SIZE];
    int reshaped_matrix[MATRIX_SIZE][3][3];
    for (int i = 0; i < MATRIX_SIZE; i++) {
        score[i] = 0;
    }
    int index = 0;
    for (int i = 0; i < MATRIX_SIZE; i++) {
        if (game->matrix[i] == 0) {
            int temp[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                temp[j] = game->matrix[j];
            }
            temp[i] = target;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    reshaped_matrix[index][j][k] = temp[j * 3 + k];
                }
            }
            index++;
        }
    }

    for (int i = 0; i < MATRIX_SIZE; i++) {
        for (int j = 0; j < 3; j++) {
            if (target == reshaped_matrix[i][j][0] || target == reshaped_matrix[i][j][1] || target == reshaped_matrix[i][j][2]) {
                score[i]++;
                break;
            }
        }
    }

    for (int i = 0; i < MATRIX_SIZE; i++) {
        for (int j = 0; j < 3; j++) {
            if (target == reshaped_matrix[i][0][j] || target == reshaped_matrix[i][1][j] || target == reshaped_matrix[i][2][j]) {
                score[i]++;
                break;
            }
        }
    }

    int diagonal_elements[MATRIX_SIZE][3];
    for (int i = 0; i < MATRIX_SIZE; i++) {
        for (int j = 0; j < 3; j++) {
            diagonal_elements[i][j] = reshaped_matrix[i][j][j];
        }
    }

    int opposite_diagonal_elements[MATRIX_SIZE][3];
    for (int i = 0; i < MATRIX_SIZE; i++) {
        for (int j = 0; j < 3; j++) {
            opposite_diagonal_elements[i][j] = reshaped_matrix[i][j][2 - j];
        }
    }

    // printf("Scores: ");
    // for (int i = 0; i < MATRIX_SIZE; i++) {
    //     printf("%d ", score[i]);
    // }
    // printf("\n");
}

int main() {
    Game game;
    initialize(&game);
    int target;
    printf("Enter the number of X in the current board position: ");
    scanf("%d", &game.x_count);
    printf("Enter the number of O in the current board position: ");
    scanf("%d", &game.y_count);

    for (int i = 0; i < game.x_count; i++) {
        int pos;
        printf("Enter the position for X: ");
        scanf("%d", &pos);
        game.matrix[pos] = 1;
    }

    for (int i = 0; i < game.y_count; i++) {
        int pos;
        printf("Enter the position for O: ");
        scanf("%d", &pos);
        if (game.matrix[pos] != 0) {
            printf("Already occupied by X\n");
            i--;
            continue;
        }
        game.matrix[pos] = 2;
    }

    printf("Matrix Representation of the current position:\n");
    for (int i = 0; i < MATRIX_SIZE; i++) {
        if (i % 3 == 0 && i != 0) {
            printf("\n");
        }
        char temp = ' ';
        if (game.matrix[i] == 1) {
            temp = 'X';
        } else if (game.matrix[i] == 2) {
            temp = 'O';
        }
        printf("%c ", temp);
    }
    printf("\n");

    if (isValid(&game)) {
        printf("The value is :: %d\n", calculate(&game));
        printf("Enter the target variable: ");
        scanf("%d", &target);
        generateMove(&game, target);
        calculateScore(&game, target);
    }

    return 0;
}
