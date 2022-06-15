package GUI.GameMenu;

import GUI.GameGUI;

import GUI.GameMenu.EnterNameMenu.EnterNameMenu;
import constant.GameTypeEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    private JPanel mainPanel;
    private JLabel mainTitle;
    private JButton buttonPlayComputer;
    private JButton buttonPlayOnline;
    private JPanel enterNamePanel;
    private JPanel playOptionPanel;
    private JButton playP2POfflineButton;
    private JButton seeRecord;

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
                displayEnterNameMenu();
                disposeAction();
            }
        });

        playP2POfflineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameGUI(GameTypeEnum.P2P_OFFLINE);
                disposeAction();
            }
        });
        seeRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMessageDialog("Chức năng còn đang phát triển, vui lòng quay lại sau!");
            }
        });
    }

    private void displayMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void disposeAction() {
        this.dispose();
    }


    private void displayEnterNameMenu() {

        JFrame frame = new EnterNameMenu();
        frame.setVisible(true);
    }

}
