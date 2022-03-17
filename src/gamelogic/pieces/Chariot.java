package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;

public class Chariot extends Piece{

    public Chariot(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveTo(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return isStraight(desCol, desRow) && numPiecesBetween(desCol, desRow, board) == 0;
        }
        return false;
    }
}
