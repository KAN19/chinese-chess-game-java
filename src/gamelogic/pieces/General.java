package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;

public class General extends Piece {

    public General(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveTo(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return !outOfPalace(desCol, desRow)
                    && steps(desCol, desRow) == 1
                    && isStraight(desCol, desRow);
        }
        return false;
    }
}
