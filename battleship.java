public class battleship {
    public static void main(String[] args) {
        boardController.init();
        System.out.println("CPU BOARD");
        boardController.PrintBoard("cpu");
        System.out.println("PLAYER BOARD");
        boardController.PrintBoard();
        System.out.println("START GAME");
        try {
            boardController.SetBoard("easy");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
