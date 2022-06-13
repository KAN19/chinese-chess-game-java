package GUI.GameMenu;

import GUI.GameGUI;
import GUI.PlayOnlineMenu.RoomPickerMenu;
import constant.GameTypeEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainMenu extends JFrame{
    private JPanel mainPanel;
    private JLabel mainTitle;
    private JButton buttonPlayComputer;
    private JButton buttonPlayOnline;
    private JTextField playerNameInput;
    private JButton playButton;
    private JPanel enterNamePanel;
    private JPanel playOptionPanel;
    private JLabel playerNameLabel;
    private JButton playP2POfflineButton;

    public MainMenu()  {

        this.setSize(500, 500);
        this.setTitle("Nguyen Kiet Chiness Chess Game");
        this.playOptionPanel.setVisible(true);
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
                displayRoomPickerMenu();
                disposeAction();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(playerNameInput.getText(), "")) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter your name to play game!");
                } else {
                    updateGameMenu();
                }
            }
        });
        playP2POfflineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void disposeAction() {
        this.dispose();

    }


    private void updateGameMenu() {
        this.playOptionPanel.setVisible(true);
        this.enterNamePanel.setVisible(false);
        this.playerNameLabel.setText("Hello champion, " + this.playerNameInput.getText());
    }

    private void displayRoomPickerMenu() {
        JFrame roomPicker = new RoomPickerMenu();
        roomPicker.setVisible(true);
    }

}
