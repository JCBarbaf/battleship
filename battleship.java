public class battleship {
    public static void main(String[] args) {
        boardController.init();
        System.out.println("CPU MAP");
        boardController.PrintMap("cpu");
        System.out.println("PLAYER MAP");
        boardController.PrintMap();
    }
}
