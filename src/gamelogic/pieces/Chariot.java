package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.List;

public class Chariot extends Piece{

    public Chariot(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithCheckGeneral(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return isStraight(desCol, desRow)
                    && numPiecesBetween(desCol, desRow, board) == 0
                    && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
        }
        return false;
    }

    @Override
    public boolean canMove(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return isStraight(desCol, desRow)
                    && numPiecesBetween(desCol, desRow, board) == 0;
        }
        return false;
    }

    @Override
    public List<Move> calculatePossibleMoves(Board board) {
        return null;
    }
}
