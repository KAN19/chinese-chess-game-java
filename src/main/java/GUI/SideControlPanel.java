package GUI;

import gamelogic.Game;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class SideControlPanel extends JPanel  implements MouseListener, PropertyChangeListener {
    private final Game game;

    JTextArea txt = new JTextArea(20, 20);

    public SideControlPanel(Game game) {
        addMouseListener(this);
        this.game = game;
        this.setBounds(new Rectangle(67 * 9 + 40, 20, 350, 800));
        this.setLayout(null);


        txt.setBounds(20, 120, 300, 100);
        txt.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txt.setLayout(new FlowLayout());


        JScrollPane jScrollPane = new JScrollPane(txt,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        txt.setText("Đỏ đi trước!");
        this.add(jScrollPane);
        this.add(txt);

        game.getPlayer1().addPropertyChangeListener(this);
        game.getPlayer2().addPropertyChangeListener(this);

        game.addPropertyChangeListener(this);
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
        if (Objects.equals(evt.getPropertyName(), "currentDurationChanged")) {
            repaint();
        }
        if (Objects.equals(evt.getPropertyName(), "listMoveInTextFieldChanged")) {
            txt.setText((String)evt.getNewValue());
        }

        if (Objects.equals(evt.getPropertyName(), "isBeingChecked")) {
            if (game.getGameStatus() == Game.GameStatus.BLACK_BEING_CHECKED) {
                txt.append("Black đang bị chiếu tướng!");
            } else if (game.getGameStatus() == Game.GameStatus.RED_BEING_CHECKED) {
                txt.append("RED đang bị chiếu tướng!");
            }
        }
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
