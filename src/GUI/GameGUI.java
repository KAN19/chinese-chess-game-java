package GUI;

import gamelogic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class GameGUI {

    public GameGUI() {
        JFrame f = new JFrame("Chinese Chess");
        f.setSize(1000, 800);
        f.setLocation(50, 50);

        Game game = new Game();
        ChessBoardPanel cchessPanel = new ChessBoardPanel(game);

        f.add(cchessPanel);
        f.setVisible(true);
    }

}
