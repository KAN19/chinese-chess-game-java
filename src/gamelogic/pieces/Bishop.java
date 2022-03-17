package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;

public class Bishop extends Piece{

    public Bishop(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveTo(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return selfSide(desRow)
                    && board.pieceAt((this.getCol() + desCol)/2, (this.getRow() + desRow)/2) == null
                    && isDiagonal(desCol, desRow)
                    && steps(desCol, desRow) == 2;
        }

        return false;
    }
}
