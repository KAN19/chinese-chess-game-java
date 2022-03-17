package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;

public class Guard extends Piece{

    public Guard(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveTo(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return !outOfPalace(desCol,desRow)
                    && isDiagonal(desCol, desRow)
                    && steps(desCol, desRow) == 1;
        }

        return false;
    }
}
