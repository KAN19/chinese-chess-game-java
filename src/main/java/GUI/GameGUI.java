package GUI;

import constant.GameTypeEnum;
import gamelogic.Game;

import javax.swing.*;

public class GameGUI {
    private Game game;
    private JFrame jFrame;

    public GameGUI(GameTypeEnum gameTypeEnum) {
        this.jFrame = initBasicComponentAttribute();

        game = new Game(gameTypeEnum);

        initCoreLogic();
    }

    public GameGUI(GameTypeEnum gameTypeEnum, String name, int port) {
        this.jFrame = initBasicComponentAttribute();

        game = new Game(gameTypeEnum, name, port);

        initCoreLogic();
    }

    private JFrame initBasicComponentAttribute() {
        JFrame f = new JFrame("Chinese Chess");
        f.setSize(1000, 700);
        f.setLocation(0, 0);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
        return f;
    }

    private void initCoreLogic() {
        ChessBoardPanel cchessPanel = new ChessBoardPanel(game, jFrame);
        SideControlPanel sideControlPanel = new SideControlPanel(game);

        jFrame.add(cchessPanel);
        jFrame.add(sideControlPanel);
    }

}
