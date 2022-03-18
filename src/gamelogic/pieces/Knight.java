package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.List;

public class Knight extends Piece {

    public Knight(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithCheckGeneral(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if (Math.abs(this.col - desCol) == 1 && Math.abs(this.row - desRow) == 2) {
                return (board.pieceAt(this.col, (this.row + desRow) / 2) == null)
                        && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            } else if (Math.abs(this.col - desCol) == 2 && Math.abs(this.row - desRow) == 1) {
                return board.pieceAt((this.col + desCol) / 2, this.row) == null
                        && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            }
        }

        return false;
    }

    @Override
    public boolean canMove(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if (Math.abs(this.col - desCol) == 1 && Math.abs(this.row - desRow) == 2) {
                return (board.pieceAt(this.col, (this.row + desRow) / 2) == null);
            } else if (Math.abs(this.col - desCol) == 2 && Math.abs(this.row - desRow) == 1) {
                return board.pieceAt((this.col + desCol) / 2, this.row) == null;
            }
        }

        return false;
    }

    @Override
    public List<Move> calculatePossibleMoves(Board board) {
        return null;
    }


}
