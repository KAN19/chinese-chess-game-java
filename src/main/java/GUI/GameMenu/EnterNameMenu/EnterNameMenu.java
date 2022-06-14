package GUI.GameMenu.EnterNameMenu;

import GUI.GameGUI;
import GUI.GameMenu.RoomPickerMenu.RoomPickerMenu;
import constant.GameTypeEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EnterNameMenu extends JFrame{
    private JPanel mainPanel;
    private JLabel mainTitle;

    private JTextField playerNameInput;
    private JButton playButton;
    private JPanel enterNamePanel;


    public EnterNameMenu()  {

        this.setSize(500, 500);
        this.setTitle("Nguyen Kiet Chiness Chess Game");


        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(playerNameInput.getText(), "")) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter your name to play game!");
                } else {
                    forwardTheMenu();
                }
            }
        });

    }

    private void disposeAction() {
        this.dispose();
    }

    private void forwardTheMenu() {
        JFrame frame = new RoomPickerMenu(playerNameInput.toString());
        frame.setVisible(true);
        disposeAction();
    }

}
