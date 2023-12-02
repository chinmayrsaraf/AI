#include <iostream>
#include <vector>

void printSoln(const std::vector<int>& copyspace) {
    for (int k = 0; k < copyspace.size(); k++) {
        std::cout << copyspace[k] << " ";
    }
    std::cout << std::endl;
}

int calculateScore(const std::vector<int>& board, int player) {
    int score = 0;

    for (int row = 0; row < 3; row++) {
        int count = 0;
        for (int col = 0; col < 3; col++) {
            if (board[row * 3 + col] == player) {
                count++;
            }
        }
        if (count == 2) {
            score++;
        }
    }

    for (int col = 0; col < 3; col++) {
        int count = 0;
        for (int row = 0; row < 3; row++) {
            if (board[row * 3 + col] == player) {
                count++;
            }
        }
        if (count == 2) {
            score++;
        }
    }

    // Check diagonals
    int countDiagonal1 = 0;
    int countDiagonal2 = 0;
    for (int i = 0; i < 3; i++) {
        if (board[i * 3 + i] == player) {
            countDiagonal1++;
        }
        if (board[i * 3 + 2 - i] == player) {
            countDiagonal2++;
        }
    }
    if (countDiagonal1 == 2) {
        score++;
    }
    if (countDiagonal2 == 2) {
        score++;
    }

    return score;
}

void possibleSteps(std::vector<int>& space) {
    std::cout << "Possible moves for O:" << std::endl;
    int bestMove = -1;
    int bestScore = INT_MIN;

    for (int i = 0; i < 9; i++) {
        if (space[i] == 0) {
            std::vector<int> copyspace = space;
            copyspace[i] = 2;
            int score = calculateScore(copyspace, 2);
            printSoln(copyspace);
            std::cout << "Score: " << score << std::endl;

            if (score > bestScore) {
                bestScore = score;
                bestMove = i;
            }
        }
    }

    std::cout << "The best move for O is at position " << bestMove << std::endl;
}

int main() {
    std::vector<int> space(9);
    std::cout << "Enter the moves: (0 = empty, 1 = X, 2 = O)" << std::endl;
    for (int i = 0; i < 9; i++) {
        std::cin >> space[i];
    }

    int countX = 0;
    int countO = 0;
    for (int i = 0; i < 9; i++) {
        if (space[i] == 1) {
            countX++;
        } else if (space[i] == 2) {
            countO++;
        }
    }

    if (std::abs(countX - countO) > 1) {
        std::cout << "Invalid situation" << std::endl;
    } else {
        std::cout << "Valid Position" << std::endl;

        std::cout << "The original position is:" << std::endl;
        for (int k = 0; k < space.size(); k++) {
            std::cout << space[k] << " ";
        }
        std::cout << std::endl;

        possibleSteps(space);
    }

    return 0;
}