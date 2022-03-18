package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class General extends Piece {

    public General(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveWithCheckGeneral(Board board, int desCol, int desRow) {
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
    public List<Move> calculatePossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        if (this.side == Side.RED) {
            for (int row = 0; row <= 2; row++) {
                for (int col = 3; col <= 5; col++) {
                    if (this.canMoveWithCheckGeneral(board, col, row)) {
                        moves.add(new Move(Side.RED, this.col, this.row, col, row));
                    }
                }
            }
        } else {
            for (int row = 7; row <= 9; row++) {
                for (int col = 3; col <= 5; col++) {
                    if  (this.canMoveWithCheckGeneral(board, col, row)) {
                        moves.add(new Move(Side.RED, this.col, this.row, col, row));
                    }
                }
            }
        }
        return moves;
    }
}
