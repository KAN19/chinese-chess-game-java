package GUI;

import GUI.GameMenu.MainMenu;
import constant.GameConstant;
import gamelogic.Game;
import gamelogic.board.Side;
import gamelogic.pieces.Piece;
import gamelogic.player.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class ChessBoardPanel extends JPanel implements MouseListener, PropertyChangeListener {
    static final int orgX = 40, orgY = 40, side = 60;

    final Map<String, Image> pieceImages = new HashMap<>();
    private final Game game;
    Piece selectingPiece;

    private JFrame jFrame;

    public ChessBoardPanel(Game game, JFrame frame) {
        this.game = game;
        this.jFrame = frame;
        game.addPropertyChangeListener(this);
        addMouseListener(this);
//        this.setBackground(Color.BLUE);
        this.setBounds(new Rectangle(20, 20, side * 9 + 20, side * 10 + 20));

        try {
            Set<String> imageNames = new HashSet<>(Arrays.asList(
                    GameConstant.BLACK_CHARIOT,
                    GameConstant.BLACK_KNIGHT,
                    GameConstant.BLACK_BISHOP,
                    GameConstant.BLACK_GUARD,
                    GameConstant.BLACK_GENERAL,
                    GameConstant.BLACK_SOLDIER,
                    GameConstant.BLACK_CANNON,
                    GameConstant.RED_CHARIOT,
                    GameConstant.RED_KNIGHT,
                    GameConstant.RED_BISHOP,
                    GameConstant.RED_GUARD,
                    GameConstant.RED_GENERAL,
                    GameConstant.RED_CANNON,
                    GameConstant.RED_SOLDIER
            ));
            for (String imageName :
                    imageNames) {
//                Image pieceImage = ImageIO.read(Objects.requireNonNull((getClass()
//                                .getResource("/GUI/res/" + imageName + ".png"))));
                Image pieceImage = ImageIO
                        .read(Objects.requireNonNull(this.getClass()
                                .getResource("/res/" + imageName + ".png")));
                pieceImages.put(imageName, pieceImage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < GameConstant.BOARD_COLS; i++) {
            g.drawLine(orgX + i * side, orgY,
                    orgX + i * side, orgY + 4 * side);
            g.drawLine(orgX + i * side, orgY + 5 * side,
                    orgX + i * side, orgY + 9 * side);
        }
        for (int i = 0; i < GameConstant.BOARD_ROWS; i++) {
            g.drawLine(orgX, orgY + i * side,
                    orgX + 8 * side, orgY + i * side);
        }
        for (int i = 0; i < 2; i++) {
            g.drawLine(orgX + 3 * side, orgY + i * 7 * side,
                    orgX + 5 * side, orgY + (2 + i * 7) * side);
            g.drawLine(orgX + 5 * side, orgY + i * 7 * side,
                    orgX + 3 * side, orgY + (2 + i * 7) * side);
            g.drawLine(orgX + 8 * i * side, orgY + 4 * side,
                    orgX + 8 * i * side, orgY + 5 * side);
        }
    }

    private void drawHalfStarAt(Graphics g, int col, int row,
                                boolean left) {
        int gap = side / 9;
        int bar = side / 4;
        int hSign = left ? -1 : 1;
        int tipX = orgX + col * side + hSign * gap;
        for (int i = 0; i < 2; i++) {
            int vSign = -1 + i * 2;
            int tipY = orgY + row * side + vSign * gap;
            g.drawLine(tipX, tipY, tipX + hSign * bar, tipY);
            g.drawLine(tipX, tipY, tipX, tipY + vSign * bar);
        }
    }

    private void drawStarAt(Graphics g, int col, int row) {
        drawHalfStarAt(g, col, row, true);
        drawHalfStarAt(g, col, row, false);
    }

    private void drawPieces(Graphics g) {
        for (Piece p : game.getBoard().getPieces()) {
            Image img = pieceImages.get(p.getImgName());


            g.drawImage(img, orgX + side * p.getCol() - side / 2,
                    orgY + side * p.getRow() - side / 2, this);


        }
    }

    private void drawSelectedPiece(Graphics g) {
        if (selectingPiece != null && !game.getCurrentPlayerTurn().isComputer()) {
            g.drawRect(orgX + selectingPiece.getCol() * side - side / 2,
                    orgY + selectingPiece.getRow() * side - side / 2,
                    67,
                    67);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);

        for (int i = 0; i < 2; i++) {
            drawStarAt(g, 1 + i * 6, 2);
            drawStarAt(g, 1 + i * 6, 7);
            drawHalfStarAt(g, 0, 3, false);
            drawHalfStarAt(g, 0, 6, false);
            drawHalfStarAt(g, 8, 3, true);
            drawHalfStarAt(g, 8, 6, true);
        }

        for (int i = 0; i < 3; i++) {
            drawStarAt(g, 2 + i * 2, 3);
            drawStarAt(g, 2 + i * 2, 6);
        }

        int margin = side / 15;
        g.drawRect(orgX - margin,
                orgY - margin,
                (GameConstant.BOARD_COLS - 1) * side + 2 * margin,
                (GameConstant.BOARD_ROWS - 1) * side + 2 * margin);

        drawPieces(g);
        drawSelectedPiece(g);
    }

    private Point xyToColRow(Point xy) {
        return new Point((xy.x - orgX + side / 2) / side, (xy.y - orgY + side / 2) / side);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (!game.getCurrentPlayerTurn().isComputer()) {
            Point clickedCoordinate = xyToColRow(e.getPoint());
            if (selectingPiece == null) {
                selectingPiece = game.getBoard().pieceAt(clickedCoordinate.x, clickedCoordinate.y);
            } else {

                int orgCol = selectingPiece.getCol();
                int orgRow = selectingPiece.getRow();

                if (game.movePiece(
                        selectingPiece.getCol(),
                        selectingPiece.getRow(),
                        clickedCoordinate.x,
                        clickedCoordinate.y)) {

                    sendMoveToLan(
                            new Move(game.getCurrentPlayerTurn().getSide(),
                                    orgCol,
                                    orgRow,
                                    clickedCoordinate.x,
                                    clickedCoordinate.y));

                };

                selectingPiece = null;
            }
        }

        repaint();

        if (game.getGameStatus() == Game.GameStatus.BLACK_WIN) {
            JOptionPane.showMessageDialog(jFrame, "Nguoi choi " + Side.BLACK + " chien thang!");
            disposeAndCreateNewGame(jFrame);
        } else if (game.getGameStatus() == Game.GameStatus.RED_WIN){
            JOptionPane.showMessageDialog(jFrame, "Nguoi choi " + Side.RED + " chien thang!");
            disposeAndCreateNewGame(jFrame);
        }
    }

    private void sendMoveToLan(Move move) {
        if (game.getServer() != null) {
            game.getServer().sendPieceMove(move);
        }

        if (game.getClient() != null) {
            game.getClient().sendPieceMove(move);
        }
    }

    private int generateRandomNumber(int rangeValue) {
        Random generator = new Random();
        return generator.nextInt(rangeValue);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "possibleMovesForComputer")) {
            if (game.getCurrentPlayerTurn().isComputer()) {
//            System.out.println(game.getCurrentPlayerTurn().isComputer());
                List<Move> computerMoves = (List<Move>) evt.getNewValue();
                int numberOfMoves = computerMoves.size();
                if (numberOfMoves > 0) {
                    int randomNumber = generateRandomNumber(numberOfMoves);

                    Move randomMove = computerMoves.get(randomNumber);
                    game.movePiece(randomMove.getOrgCol(), randomMove.getOrgRow(), randomMove.getDesCol(), randomMove.getDesRow());
                }
            }
        }
    }

    private void disposeAndCreateNewGame(JFrame frame) {
        jFrame.dispose();

        JFrame jFrame = new MainMenu();
        jFrame.setVisible(true);
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
