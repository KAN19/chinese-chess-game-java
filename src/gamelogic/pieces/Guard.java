package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.List;

public class Guard extends Piece{

    public Guard(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithCheckGeneral(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return !outOfPalace(desCol,desRow)
                    && isDiagonal(desCol, desRow)
                    && steps(desCol, desRow) == 1
                    && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
        }

        return false;
    }

    @Override
    public boolean canMove(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return !outOfPalace(desCol,desRow)
                    && isDiagonal(desCol, desRow)
                    && steps(desCol, desRow) == 1;
        }

        return false;
    }

    @Override
    public List<Move> calculatePossibleMoves(Board board) {
        return null;
    }
}
