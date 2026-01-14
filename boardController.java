import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class boardController {
    static char[][] CPUBoard;
    static char[][] playerBoard;
    static Map<String, Integer> shipSizes = new HashMap<>();
    static Random random;

    //Console colors
    public static final String BLUE_TEXT = "\u001B[34m";
    public static final String GREEN_TEXT = "\u001B[32m";
    public static final String RED_TEXT = "\u001B[31m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    public static final String RESET_COLORS = "\u001B[0m";


    public static void init() {
        random = new Random();
        CPUBoard = new char[10][10];
        for (int i = 0; i < CPUBoard.length; i++) {
            for (int j = 0; j < CPUBoard[i].length; j++) {
                CPUBoard[i][j] = '-';
            }
        }
        playerBoard = new char[10][10];
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard[i].length; j++) {
                playerBoard[i][j] = '-';
            }
        }
        shipSizes.put("carrier", 5);
        shipSizes.put("battleship", 4);
        shipSizes.put("destroyer", 3);
        shipSizes.put("submarine", 1);
    }
    public static void PrintBoard () {
        PrintBoard("player");
    }
    public static void PrintBoard(String boardType) {
        char[][] board;
        if (boardType.equalsIgnoreCase("cpu")) {
            board = CPUBoard;
        } else {
            board = playerBoard;
        }
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                char letter = board[i][j];

                if (letter != 'W' && letter != '-') {
                    System.out.print(BLUE_TEXT);
                }
                System.out.print(letter + RESET_COLORS + " ");
            }
            System.out.println();
        }
    }

    public static void SetBoard(String difficulty) throws Exception {
        boardController.ResetBoard("cpu");
        switch (difficulty.toLowerCase()) {
            case "easy":
                AddShip("carrier", 1);
                AddShip("battleship", 1);
                AddShip("destroyer", 3);
                AddShip("submarine", 5);
                PrintBoard("CPU");
                break;
            case "medium":
                AddShip("carrier", 1);
                AddShip("battleship", 1);
                AddShip("destroyer", 1);
                AddShip("submarine", 2);
                PrintBoard("CPU");
                break;
            case "hard":
                AddShip("destroyer", 1);
                AddShip("submarine", 1);
                PrintBoard("CPU");
                break;
            default:
                throw new Exception("Invalid difficulty");
        }
    }

    public static void ResetBoard(String boardType) throws Exception{
        char[][] newBoard = new char[10][10];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[i].length; j++) {
                newBoard[i][j] = '-';
            }
        }
        switch (boardType.toLowerCase()) {
            case "cpu":
                CPUBoard = newBoard;
                break;
            case "player":
                playerBoard = newBoard;
                break;
            default:
                throw new Exception("Invalid board type");
        }
    }

    public static void AddShip(String ship, int quantity) throws Exception{
        char[][] map = CPUBoard;
        int size = shipSizes.get(ship);
        for (int index = 0; index < quantity; index++) {
            boolean validPosition = false;
            while (!validPosition) {
                validPosition = true;
                int posInitMain = random.nextInt(map[0].length - (size -1));
                int posInitSecond = random.nextInt(map[0].length);
                boolean vertical = random.nextBoolean();
                // System.out.println(ship);
                // System.out.println(vertical ? "vertical" : "horizontal");
                // System.out.println(vertical ? posInitSecond + "," + posInitMain : posInitMain + "," + posInitSecond);
                if (vertical) {
                    for (int i = posInitMain; i < (posInitMain + size); i++) {
                        if (map[i][posInitSecond] != '-') {
                            // System.out.println("Not valid");
                            validPosition = false;
                        }
                    }
                    if (!validPosition) {
                        continue;
                    }
                    for (int i = posInitMain; i < (posInitMain + size); i++) {
                        map[i][posInitSecond] = ship.toUpperCase().charAt(0);
                        if (posInitSecond - 1 >= 0) {
                            map[i][posInitSecond - 1] = 'W';
                        }
                        if (posInitSecond + 1 < map.length) {
                            map[i][posInitSecond + 1] = 'W';
                        }
                    }
                    if (posInitMain - 1 >= 0) {
                        map[posInitMain - 1][posInitSecond] = 'W';
                    }
                    if (posInitMain + size < map[0].length) {
                        map[posInitMain + size][posInitSecond] = 'W';
                    }
                } else {
                    for (int i = posInitMain; i < (posInitMain + size); i++) {
                        if (map[posInitSecond][i] != '-') {
                            // System.out.println("Not valid");
                            validPosition = false;
                        }
                    }
                    if (!validPosition) {
                        continue;
                    }
                    for (int i = posInitMain; i < (posInitMain + size); i++) {
                        map[posInitSecond][i] = ship.toUpperCase().charAt(0);
                        if (posInitSecond - 1 >= 0) {
                            map[posInitSecond - 1][i] = 'W';
                        }
                        if (posInitSecond + 1 < map.length) {
                            map[posInitSecond + 1][i] = 'W';
                        }
                    }
                    if (posInitMain - 1 >= 0) {
                        map[posInitSecond][posInitMain - 1] = 'W';
                    }
                    if (posInitMain + size < map[0].length) {
                        map[posInitSecond][posInitMain + size] = 'W';
                    }
                }
            }
        }
        CPUBoard = map;
    }
}
