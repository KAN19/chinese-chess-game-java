package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class General extends Piece  {

    public General(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithoutSuicide(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            if(!outOfPalace(desCol, desRow)
                    && steps(desCol, desRow) == 1
                    && isStraight(desCol, desRow)) {
                return board.canMoveWithoutBeingChecked(this.col, this.row, desCol, desRow);
            }
        }
        return false;
    }

    @Override
    public boolean canMove(Board board, int desCol, int desRow) {
        if (!isAlliance(desCol, desRow, board)) {
            return !outOfPalace(desCol, desRow)
                    && steps(desCol, desRow) == 1
                    && isStraight(desCol, desRow);
        }
        return false;
    }

    public boolean isGeneralExposed(Board board) {
            General enemyGeneral = (General)board.getEnemyGeneral(this.side);
        return isStraight(enemyGeneral.getCol(), enemyGeneral.getRow())
                && numPiecesBetween(enemyGeneral.getCol(), enemyGeneral.getRow(), board) == 0;
    }

    public boolean isAnyEnemyCanMoveTo(Board board, int desCol, int desRow) {
        Set<Piece> pieces = board.getPieces();
        for (Piece piece: pieces) {
//            Kiem tra co phai enemy khong
            if (piece.getSide() != this.getSide()
                    && piece.canMove(board, desCol, desRow)) {
                return true;
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
