import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SnakeGame {
    static Scanner sc = new Scanner(System.in);
    static int score = 0;

    public static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static Queue<Node> queue = new LinkedList<>();
    static char[][] board;

    public static void main(String[] args) {
        board = new char[5][5];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 'o';
            }
        }
        System.err.println((int) Math.floor(Math.random() * 3));
        board[3][3] = 'X';
        queue.add(new Node(0, 0));
        snakesmove(0, 0);
    }

    public static void snakesmove(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board.length) {
            if (board[row][col] == '.') {
                System.out.println("Game Over!!!");
                System.out.println();
                System.out.println("Your Score : " + score);
                System.out.println();
                System.exit(0);
            }
            if (board[row][col] != 'X') {
                Node curr = queue.poll();
                int r = curr.row;
                int c = curr.col;
                board[r][c] = 'o';
            } else {
                score++;
                while (true) {
                    int fr = (int) Math.floor(Math.random() * 5);
                    int fc = (int) Math.floor(Math.random() * 5);
                    if (board[fr][fc] != '.') {
                        board[fr][fc] = 'X';
                        break;
                    }
                }
            }
            queue.add(new Node(row, col));
            board[row][col] = '.';
            while (!queue.isEmpty()) {
                printboard();
                System.out.println("Enter the intput U-up D-down R-right L-left : ");
                char c = sc.next().charAt(0);
                switch (c) {
                    case 'U':
                        snakesmove(row - 1, col);
                        break;
                    case 'D':
                        snakesmove(row + 1, col);
                        break;
                    case 'R':
                        snakesmove(row, col + 1);
                        break;
                    case 'L':
                        snakesmove(row, col - 1);
                        break;
                    default:
                        System.out.println("Plese Enter Correct direction...");
                        break;
                }
            }
        } else {

            System.out.println("Invalid move..");
            System.out.println("Game Over!!!");
            System.out.println();
            System.out.println("Your Score : " + score);
            System.out.println();
            System.exit(0);
        }
    }

    public static void printboard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}