import GUI.GameGUI;
import GameMenu.MainMenu;
import gamelogic.Game;
import gamelogic.board.Board;

import javax.swing.*;

public class EntryPoint {
    public static void main(String[] args) {
//       Board boardGame = new Board();
//        System.out.println(boardGame);
//        boardGame.movePiece(0,4,1,4);
//        System.out.println(boardGame);

//        Game game = new Game();
//        System.out.println(game.getBoard());
//        game.getPlayer1().movePiece(4,0,4,1);
//        System.out.println(game.getBoard());
//        new GameGUI();

        JFrame jFrame = new MainMenu();
        jFrame.setVisible(true);

    }

}
