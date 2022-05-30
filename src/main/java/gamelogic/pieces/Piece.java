package gamelogic.pieces;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.player.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    protected Side side;
    protected String imgName;
    protected int col;
    protected int row;
    protected List<Move> possibleMoves;

    public Piece(int col, int row, Side side, String imgName) {
        this.side = side;
        this.imgName = imgName;
        this.col = col;
        this.row = row;
    }


    public abstract boolean canMoveWithoutSuicide(Board board, int desCol, int desRow);

    public abstract boolean canMove(Board board, int desCol, int desRow);

    public abstract void calculatePossibleMoves(Board board);

    public List<Move> calculateMovesFromBasicMoves(Board board, int[][] basicMoves) {
        List<Move> moves = new ArrayList<>();

        int currentCol = this.getCol();
        int currentRow = this.getRow();

        for (int[] possibleMovesParameter : basicMoves) {
            int newPositionCol = currentCol + possibleMovesParameter[0];
            int newPositionRow = currentRow + possibleMovesParameter[1];

            if (this.canMoveWithoutSuicide(board, newPositionCol, newPositionRow)) {
                moves.add(new Move(Side.RED, this.col, this.row, newPositionCol, newPositionRow));
            }
        }
        return moves;
    }

    protected boolean outOfPalace(int col, int row) {
        if (this.side == Side.RED) {
            return col < 3 || col > 5 || row < 0 || row > 2;
        } else {
            return col < 3 || col > 5 || row < 7 || row > 9;
        }
    }

    protected boolean selfSide(int row) {
        return this.side == Side.RED ? row <= 4 : row >= 5;
    }

    protected boolean isDiagonal(int desCol, int desRow) {
        return Math.abs(this.col - desCol) == Math.abs(this.row - desRow);
    }

    protected boolean isStraight(int toCol, int toRow) {
        return this.getCol() == toCol || this.getRow() == toRow;
    }

    protected boolean isStraightForward(int desCol, int desRow) {
        if (this.getSide() == Side.RED) {
            return this.getCol() == desCol && this.getRow() < desRow;
        } else {
            return this.getCol() == desCol && this.getRow() > desRow;
        }
    }

    protected boolean unableToMoveBackward(int desRow) {
        if (this.getSide() == Side.RED) {
            return this.getRow() <= desRow;
        } else {
            return this.getRow() >= desRow;
        }
    }


    protected int numPiecesBetween(int toCol, int toRow, Board board) {
        if (!isStraight(toCol, toRow)
                || steps(toCol, toRow) < 2) {
            return 0;
        }
        int numPieces = 0, startCoord, endCoord;
        if (this.getCol() == toCol) { // vertical
            startCoord = Math.min(this.getRow(), toRow);
            endCoord = Math.max(this.getRow(), toRow);
            for (int row = startCoord + 1; row < endCoord; row++) {
                numPieces += (board.pieceAt(this.getCol(), row) == null) ? 0 : 1;
            }
        } else {
            startCoord = Math.min(this.getCol(), toCol);
            endCoord = Math.max(this.getCol(), toCol);
            for (int col = startCoord + 1; col < endCoord; col++) {
                numPieces += (board.pieceAt(col, this.getRow()) == null) ? 0 : 1;
            }
        }
        return numPieces;
    }

    protected int steps(int desCol, int desRow) {
        if (this.col == desCol) {
            return Math.abs(this.row - desRow);
        } else if (this.row == desRow) {
            return Math.abs(this.col - desCol);
        } else if (isDiagonal(desCol, desRow)) {
            return Math.abs(this.row - desRow);
        }
        return 0; // neither straight nor diagonal
    }

    protected boolean isAlliance(int desCol, int desRow, Board board) {
        Piece target = board.pieceAt(desCol, desRow);
        return target != null && target.side == this.side;
    }

    protected boolean isOutOfBoard(int desCol, int desRow) {
        return desCol < 0 || desCol > 8 || desRow < 0 || desRow > 9;
    }


    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(List<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }
}
