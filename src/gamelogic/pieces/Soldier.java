package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;

public class Soldier extends Piece {

    public Soldier(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveTo(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if (selfSide(this.getRow())) {
                return steps(desCol, desRow) == 1
                        && isStraightForward(desCol);
            } else {
                return steps(desCol, desRow) == 1;
            }
        }
        return false;
    }
}
