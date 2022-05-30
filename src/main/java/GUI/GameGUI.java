package GUI;

import constant.GameTypeEnum;
import gamelogic.Game;

import javax.swing.*;
import java.awt.*;

public class GameGUI {

    public GameGUI(GameTypeEnum gameTypeEnum) {
        JFrame f = new JFrame("Chinese Chess");
        f.setSize(1000, 800);
        f.setLocation(0, 0);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

//        Cai nay cho 2 nguoi choi
//        Game game = new Game();

        Game game = null;
        game = new Game();
//        if (gameTypeEnum == GameTypeEnum.P2P_OFFLINE || gameTypeEnum == GameTypeEnum.P2P_ONLINE) {
//            game = new Game();
//        }
//
//        //        Cai nay cho choi voi may
//        if (gameTypeEnum == GameTypeEnum.BLACK_IS_COMPUTER || gameTypeEnum == GameTypeEnum.RED_IS_COMPUTER) {
//            game = new Game(gameTypeEnum);
//        }

        assert game != null;
        ChessBoardPanel cchessPanel = new ChessBoardPanel(game);
        SideControlPanel sideControlPanel = new SideControlPanel(game);

        f.add(cchessPanel);
        f.add(sideControlPanel);

        f.setVisible(true);
    }

}
