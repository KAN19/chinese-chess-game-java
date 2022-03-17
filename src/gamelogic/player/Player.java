package gamelogic.player;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.pieces.Piece;

import java.util.List;

public class Player {

    private final Board board;

    private Side side;
    private List<Move> moveList;

    public Player(Board board, Side side) {
        this.board = board;
        this.side = side;
    }

//    public void movePiece(int orgCol, int orgRow, int desCol, int desRow) {
//        Piece movingP = board.pieceAt(orgCol, orgRow);
//        if (movingP == null || movingP.getSide() != this.side) return;
//
//        Piece targetP = board.pieceAt(desCol, desRow);
//        if (movingP.canMoveTo(board, desCol, desRow)) {
//            if (targetP != null) {
//                board.removePieceAt(targetP);
//            }
//            movingP.setCol(desCol);
//            movingP.setRow(desRow);
//        }
//    }

    public Board getBoard() {
        return board;
    }
}
