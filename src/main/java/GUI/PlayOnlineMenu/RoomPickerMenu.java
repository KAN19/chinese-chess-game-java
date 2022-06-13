package GUI.PlayOnlineMenu;

import GUI.GameGUI;
import constant.GameTypeEnum;
import onlineFeature.Client;
import onlineFeature.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomPickerMenu extends JFrame {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JButton createARoomButton;
    private JButton playAsClientButton;

    public RoomPickerMenu() {
        this.setSize(500, 500);
        this.setTitle("Select a Room");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createARoomButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed (click event)
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameGUI(GameTypeEnum.P2P_ONLINE_SERVER);
                disposeAction();
            }
        });
        playAsClientButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                new GameGUI(GameTypeEnum.P2P_ONLINE_CLIENT);
                disposeAction();
            }
        });
    }

    private Client createClient() {
        Client client = new Client();

        Thread t2 = new Thread(client);
        t2.start();

        return client;
    }

    private Server createServer() {
        Server server = new Server();
        Thread t1 = new Thread(server);
        t1.start();

        return server;
    }

    private void disposeAction() {
        this.dispose();

    }

}
