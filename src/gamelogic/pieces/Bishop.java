package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithoutSuicide(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board) && !isOutOfBoard(desCol, desRow)) {
            return selfSide(desRow)
                    && board.pieceAt((this.getCol() + desCol)/2, (this.getRow() + desRow)/2) == null
                    && isDiagonal(desCol, desRow)
                    && steps(desCol, desRow) == 2
                    && board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
        }

        return false;
    }

    @Override
    public boolean canMove(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return selfSide(desRow)
                    && board.pieceAt((this.getCol() + desCol)/2, (this.getRow() + desRow)/2) == null
                    && isDiagonal(desCol, desRow)
                    && steps(desCol, desRow) == 2;
        }
        return false;
    }

    @Override
    public void calculatePossibleMoves(Board board) {
        int[][] possibleMovesParameters = {{2, 2}, {2,-2}, {-2,2}, {-2, -2}};
        List<Move> moves = calculateMovesFromBasicMoves(board, possibleMovesParameters);
        this.setPossibleMoves(moves);
    }
}
