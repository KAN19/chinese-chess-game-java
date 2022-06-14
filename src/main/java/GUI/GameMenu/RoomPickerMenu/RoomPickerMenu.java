package GUI.GameMenu.RoomPickerMenu;

import GUI.GameGUI;
import constant.GameTypeEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomPickerMenu extends JFrame{
    private JPanel mainPanel;
    private JLabel mainTitle;

    private JTextField playerNameInput;
    private JButton phong1Button;
    private JPanel enterNamePanel;
    private JButton phong2Button;

    private String playerName;

    public RoomPickerMenu(String name)  {

        this.playerName = name;

        this.setSize(500, 500);
        this.setTitle("Nguyen Kiet Chiness Chess Game");


        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        phong1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickRoom(111);
            }
        });

        phong2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickRoom(222);
            }
        });
    }

    private void disposeAction() {
        this.dispose();
    }

    private void pickRoom(int port) {
        new GameGUI(GameTypeEnum.P2P_ONLINE, playerName, port);
        disposeAction();
    }


}
