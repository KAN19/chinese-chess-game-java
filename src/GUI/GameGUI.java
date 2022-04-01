package GUI;

import gamelogic.Game;

import javax.swing.*;
import java.awt.*;

public class GameGUI {

    public GameGUI() {
        JFrame f = new JFrame("Chinese Chess");
        f.setSize(1000, 800);
        f.setLocation(50, 50);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        ChessBoardPanel cchessPanel = new ChessBoardPanel(game);
        SideControlPanel sideControlPanel = new SideControlPanel(game);

        f.add(cchessPanel);
        f.add(sideControlPanel);
        f.setVisible(true);
    }

}
