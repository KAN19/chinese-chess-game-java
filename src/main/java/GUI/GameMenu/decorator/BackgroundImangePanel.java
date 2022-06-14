package GUI.GameMenu.decorator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BackgroundImangePanel extends JComponent {

    private final Image image;

    public BackgroundImangePanel() {
        try {
            Image pieceImage = ImageIO
                    .read(Objects.requireNonNull(this.getClass()
                            .getResource("/res/background-menu.jpg")));

            this.image = pieceImage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public Image getImage() {
        return image;
    }
}
