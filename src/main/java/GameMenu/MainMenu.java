package GameMenu;

import GUI.GameGUI;
import constant.GameTypeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    private JPanel mainPanel;
    private JLabel mainTitle;
    private JButton buttonPlayComputer;
    private JButton buttonPlayOnline;

    public MainMenu()  {

        this.setSize(500, 500);
        this.setTitle("Nguyen Kiet Chiness Chess Game");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonPlayComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameGUI(GameTypeEnum.BLACK_IS_COMPUTER);
                disposeAction();
            }
        });

        buttonPlayOnline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameGUI(GameTypeEnum.P2P_OFFLINE);
                disposeAction();
            }
        });
    }

    private void disposeAction() {
        this.dispose();

    }

}
