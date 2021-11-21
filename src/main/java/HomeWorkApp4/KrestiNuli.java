package HomeWorkApp4;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class KrestiNuli {
    private final static int SIZE = 3;
    private final static char DOT_EMPTY = '.';
    private final static char DOT_X = 'X';
    private final static char DOT_0 = '0';
    private static char[][] MAP;
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Random RANDOM = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Человек победил");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_0)) {
                System.out.println("Победил компьютер");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");
    }

    private static boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            if (MAP[i][0] == symbol && MAP[i][1] == symbol && MAP[i][2] == symbol) {
                return true;
            }
        }
        for (int j = 0; j < SIZE; j++) {
            if (MAP[0][j] == symbol && MAP[1][j] == symbol && MAP[2][j] == symbol) {
                return true;
            }
        }
        // Проверка диагоналей
        if (MAP[0][0] == symbol && MAP[1][1] == symbol && MAP[2][2] == symbol)
            return true;
        if (MAP[2][0] == symbol && MAP[1][1] == symbol && MAP[0][2] == symbol)
            return true;
        return false;
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void aiTurn() {
        int x = RANDOM.nextInt(SIZE);
        int y = RANDOM.nextInt(SIZE);

        do {
            //ИИ занимает свободный центр
            if (MAP[1][1] == DOT_EMPTY) {
                x = 1;
                y = 1;
                MAP[y][x] = DOT_0;
                break;
                //ИИ занимает левый верхний угол
            } else if (MAP[0][0] == DOT_EMPTY && MAP[2][2] == DOT_EMPTY && MAP[0][2] == DOT_EMPTY && MAP[2][0] == DOT_EMPTY) {
                x = 0;
                y = 0;
            }

        } while (!isCellValid(x, y));
        System.out.println("Компьютер сходил в точку " + (x + 1) + " " + (y + 1));
        MAP[y][x] = DOT_0;
    }

    private static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите координаты в формате X (столбец) и Y (строка)");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y));
        MAP[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }

        if (MAP[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }


    private static void initMap() {
        MAP = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }


    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
