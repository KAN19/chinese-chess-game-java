package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;

import java.util.Set;

public class General extends Piece {

    public General(int col, int row, Side side, String imgName) {
        super(col, row, side, imgName);
    }

    @Override
    public boolean canMoveTo(Board board, int desCol, int desRow) {
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
                    && piece.canMoveTo(board, desCol, desRow)) {
                return true;
            }
        }
        return false;
    }
}
