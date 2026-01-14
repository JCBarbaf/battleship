public class battleship {
    public static void main(String[] args) {
        boardController.init();
        System.out.println("PLAYER BOARD");
        boardController.PrintBoard();
        try {
            System.out.println("CPU BOARD EASY");
            boardController.SetBoard("easy");
            System.out.println("CPU BOARD MEDIUM");
            boardController.SetBoard("medium");
            System.out.println("CPU BOARD HARD");
            boardController.SetBoard("hard");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
