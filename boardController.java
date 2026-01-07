public class boardController {
    static char[][] CPUMap;
    static char[][] playerMap;
    static String hr = "---------------------";
    public static void init() {
        CPUMap = new char[10][10];
        for (int i = 0; i < CPUMap.length; i++) {
            for (int j = 0; j < CPUMap[i].length; j++) {
                CPUMap[i][j] = 'A';
            }
        }
        playerMap = new char[10][10];
        for (int i = 0; i < playerMap.length; i++) {
            for (int j = 0; j < playerMap[i].length; j++) {
                playerMap[i][j] = '-';
            }
        }
    }
    public static void PrintMap () {
        PrintMap("player");
    }
    public static void PrintMap (String mapType) {
        char[][] map;
        if (mapType.equalsIgnoreCase("cpu")) {
            map = CPUMap;
        } else {
            map = playerMap;
        }
        String mapString = hr + "\n";
        for (int i = 0; i < map.length; i++) {
            mapString += "|";
            for (int j = 0; j < map[i].length; j++) {
                mapString += map[i][j] + "|";
            }
            mapString += "\n" + hr + "\n";
        }
        System.out.println(mapString);
    }
}
