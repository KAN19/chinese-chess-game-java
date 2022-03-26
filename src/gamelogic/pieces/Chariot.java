package gamelogic.pieces;

import constant.GameConstant;
import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.ArrayList;
import java.util.List;

public class Chariot extends Piece {

    public Chariot(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithoutSuicide(Board board, int desCol, int desRow) {
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
    public void calculatePossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        int currentCol = this.getCol();
        int currentRow = this.getRow();

        for (int row = 0; row < GameConstant.BOARD_ROWS; row++) {
            if (this.canMoveWithoutSuicide(board, currentCol, row)) {
                moves.add(new Move(Side.RED, this.col, this.row, currentCol, row));
            }
        }
        for (int col = 0; col < GameConstant.BOARD_COLS; col++) {
            if (this.canMoveWithoutSuicide(board, col, currentRow)) {
                moves.add(new Move(Side.RED, this.col, this.row, col, currentRow));
            }
        }

        this.setPossibleMoves(moves);

    }
}
