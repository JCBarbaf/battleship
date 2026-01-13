import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class boardController {
    static char[][] CPUBoard;
    static char[][] playerBoard;
    static Map<String, Integer> shipSizes = new HashMap<>(Map.of(
        "carrier", 5,
        "battleship", 4,
        "destroyer", 3,
        "submarine", 1
    ));
    static Random random;
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
    }
    public static void PrintBoard () {
        PrintBoard("player");
    }
    public static void PrintBoard (String boardType) {
        char[][] board;
        if (boardType.equalsIgnoreCase("cpu")) {
            board = CPUBoard;
        } else {
            board = playerBoard;
        }
        String boardString = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boardString += board[i][j] + " ";
            }
            boardString += "\n";
        }
        System.out.println(boardString);
    }

    public static void SetBoard(String difficulty) throws Exception {
        switch (difficulty.toLowerCase()) {
            case "easy":
                // Carrier	    5 x 1
                // Battleship	4 x 1
                // Destroyer	3 x 3
                // Submarine	1 x 5
                AddShip("carrier", 1);
                AddShip("battleship", 1);
                AddShip("destroyer", 3);
                AddShip("submarine", 5);
                PrintBoard("CPU");
                break;
            default:
                throw new Exception("Invalid difficulty");
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
                            System.out.println("Not valid");
                            validPosition = false;
                        }
                    }
                    if (!validPosition) {
                        continue;
                    }
                    for (int i = posInitMain; i < (posInitMain + size); i++) {
                        map[i][posInitSecond] = ship.toUpperCase().charAt(0);
                        if (posInitSecond - 1 >= 0) {
                            map[i][posInitSecond - 1] = 'A';
                        }
                        if (posInitSecond + 1 < map.length) {
                            map[i][posInitSecond + 1] = 'A';
                        }
                    }
                    if (posInitMain - 1 >= 0) {
                        map[posInitMain - 1][posInitSecond] = 'A';
                    }
                    if (posInitMain + size < map[0].length) {
                        map[posInitMain + size][posInitSecond] = 'A';
                    }
                } else {
                    for (int i = posInitMain; i < (posInitMain + size); i++) {
                        if (map[posInitSecond][i] != '-') {
                            System.out.println("Not valid");
                            validPosition = false;
                        }
                    }
                    if (!validPosition) {
                        continue;
                    }
                    for (int i = posInitMain; i < (posInitMain + size); i++) {
                        map[posInitSecond][i] = ship.toUpperCase().charAt(0);
                        if (posInitSecond - 1 >= 0) {
                            map[posInitSecond - 1][i] = 'A';
                        }
                        if (posInitSecond + 1 < map.length) {
                            map[posInitSecond + 1][i] = 'A';
                        }
                    }
                    if (posInitMain - 1 >= 0) {
                        map[posInitSecond][posInitMain - 1] = 'A';
                    }
                    if (posInitMain + size < map[0].length) {
                        map[posInitSecond][posInitMain + size] = 'A';
                    }
                }
            }
        }
        CPUBoard = map;
    }
}
