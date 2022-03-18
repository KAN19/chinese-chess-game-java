package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.List;

public class Cannon extends Piece {

    public Cannon(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithCheckGeneral(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if (board.pieceAt(desCol, desRow) == null) {
                return isStraight(desCol, desRow)
                        && numPiecesBetween(desCol, desRow, board) == 0
                        && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            } else {
                return isStraight(desCol, desRow)
                        && numPiecesBetween(desCol, desRow, board) == 1
                        && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            }
        }
        return false;
    }

    @Override
    public boolean canMove(Board board, int desCol, int desRow) {
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

    @Override
    public List<Move> calculatePossibleMoves(Board board) {
        return null;
    }
}
