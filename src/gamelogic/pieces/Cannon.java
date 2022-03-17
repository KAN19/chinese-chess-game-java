package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;

public class Cannon extends Piece {

    public Cannon(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveTo(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if (board.pieceAt(desCol, desRow) == null) {
                return isStraight(desCol, desRow)
                        && numPiecesBetween(desCol, desRow, board) == 0;
            } else {
                return isStraight(desCol, desRow)
                        && numPiecesBetween(desCol, desRow, board) == 1;
            }
        }
        return false;
    }
}
