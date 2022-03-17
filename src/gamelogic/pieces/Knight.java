package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;

public class Knight extends Piece {

    public Knight(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveTo(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if (Math.abs(this.getCol() - desCol) == 1 && Math.abs(this.getRow() - desRow) == 2) {
                return board.pieceAt(this.getCol(), (this.getRow() + desRow) / 2) == null;
            } else if (Math.abs(this.getCol() - desCol) == 2 && Math.abs(this.getRow() - desRow) == 1) {
                return board.pieceAt((this.getCol() + desCol) / 2, this.getRow()) == null;
            }
        }

        return false;
    }


}
