package GUI;

import gamelogic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SideControlPanel extends JPanel  implements MouseListener, PropertyChangeListener {
    private final Game game;
    private String previousBlackTimeLeft = "";
    private String previousRedTimeLeft = "";

    JTextArea txt = new JTextArea();

    public SideControlPanel(Game game) {
        addMouseListener(this);
        this.game = game;
        this.setBounds(new Rectangle(67 * 9 + 40, 20, 350, 800));
        this.setLayout(null);

        txt.setBounds(20, 120, 300, 300);
        txt.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txt.setEditable(false);

        txt.setText("Hello");
        txt.append("\nNguyen kiet");
        this.add(txt);

        game.getPlayer1().addPropertyChangeListener(this);
        game.getPlayer2().addPropertyChangeListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitle(g);
        drawPlayerWithTime(g);
    }


    private void drawTitle(Graphics g) {
        g.setColor(Color.blue);
        g.setFont(new Font("Roboto", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Player", (350 -
                metrics1.stringWidth("Player")) / 2, metrics1.getAscent());

    }

    private void drawPlayerWithTime(Graphics g) {
        String player1Timer = game.getPlayer1().getCurrentDuration();
        String player2Timer = game.getPlayer2().getCurrentDuration();

        g.setColor(Color.red);
        g.setFont(new Font("Roboto", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Red player: " + player1Timer, (350 -
                metrics1.stringWidth("Red player: " + player1Timer)) / 2, metrics1.getAscent() + 32);

        g.setColor(Color.black);
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Black player: " + player2Timer, (350 -
                metrics2.stringWidth("Black player: " + player2Timer)) / 2, metrics2.getAscent() + 64);

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
//        System.out.println("Catch change " + evt.getNewValue());
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
