package gamelogic;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.pieces.Piece;
import gamelogic.player.Player;

public class Game {

    Board board;
    Player player1;
    Player player2;
    Side turn = Side.RED;

    public Game() {
        this.board = new Board();
        this.player1 = new Player(this.board, Side.RED);
        this.player2 = new Player(this.board, Side.BLACK);
    }

    public void movePiece(int orgCol, int orgRow, int desCol, int desRow) {
        Piece movingP = board.pieceAt(orgCol, orgRow);
        if (movingP == null || movingP.getSide() != turn) return;

        Piece targetP = board.pieceAt(desCol, desRow);
        if (movingP.canMoveTo(board, desCol, desRow)) {
            if (targetP != null) {
                board.removePieceAt(targetP);
            }
            movingP.setCol(desCol);
            movingP.setRow(desRow);

            turn = turn == Side.RED ? Side.BLACK : Side.RED;
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Side getTurn() {
        return turn;
    }

}
