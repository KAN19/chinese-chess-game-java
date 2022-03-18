package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.List;

public class Soldier extends Piece {

    public Soldier(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithCheckGeneral(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if (selfSide(this.getRow())) {
                return steps(desCol, desRow) == 1
                        && isStraightForward(desCol)
                        && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            } else {
                return steps(desCol, desRow) == 1
                        && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            }
        }
        return false;
    }

    @Override
    public boolean canMove(Board board, int desCol, int desRow) {
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

    @Override
    public List<Move> calculatePossibleMoves(Board board) {
        return null;
    }
}
