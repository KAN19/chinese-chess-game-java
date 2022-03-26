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
    public boolean canMoveWithoutSuicide(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if (selfSide(this.getRow())) {
                return steps(desCol, desRow) == 1
                        && isStraightForward(desCol, desRow)
                        && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            } else {
                return steps(desCol, desRow) == 1
                        && unableToMoveBackward(desRow)
                        && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            }
        }
        return false;
    }

    @Override
    public boolean canMove(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board) && !isOutOfBoard(desCol, desRow)) {
            if (selfSide(this.getRow())) {
                return steps(desCol, desRow) == 1
                        && isStraightForward(desCol, desRow);
            } else {
                return steps(desCol, desRow) == 1 && unableToMoveBackward(desRow);
            }
        }
        return false;
    }

    @Override
    public void calculatePossibleMoves(Board board) {
        int[][] possibleMovesParameters = {{0, 1}, {-1, 0}, {1, 0}, {0, -1}};
        List<Move> moves = calculateMovesFromBasicMoves(board, possibleMovesParameters);
        this.setPossibleMoves(moves);
    }
}
