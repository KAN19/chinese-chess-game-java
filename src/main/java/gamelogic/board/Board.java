package gamelogic.board;

import constant.GameConstant;
import gamelogic.pieces.*;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private final Set<Piece> pieces = new HashSet<>();

    private final Piece redGeneral;
    private final Piece blackGeneral;

    /**
     * Initialize a basic terminal chess game.
     */
    public Board() {
        redGeneral = new General(4, 0, Side.RED, "red-general");
        blackGeneral = new General(4, 9, Side.BLACK, "black-general");
        pieces.add(redGeneral);
        pieces.add(blackGeneral);

        for (int i = 0; i < 2; i++) {
            pieces.add(new Chariot(i * 8, 0, Side.RED, "red-chariot"));
            pieces.add(new Chariot(i * 8, 9, Side.BLACK, "black-chariot"));

            pieces.add(new Knight(1 + i * 6, 0, Side.RED, "red-knight"));
            pieces.add(new Knight(1 + i * 6, 9, Side.BLACK, "black-knight"));

            pieces.add(new Bishop(2 + i * 4, 0, Side.RED, "red-bishop"));
            pieces.add(new Bishop(2 + i * 4, 9, Side.BLACK, "black-bishop"));

            pieces.add(new Guard(3 + i * 2, 0, Side.RED, "red-guard"));
            pieces.add(new Guard(3 + i * 2, 9, Side.BLACK, "black-guard"));

            pieces.add(new Cannon(1 + i * 6, 2, Side.RED, "red-cannon"));
            pieces.add(new Cannon(1 + i * 6, 7, Side.BLACK, "black-cannon"));
        }

        for (int i = 0; i < 5; i++) {
            pieces.add(new Soldier(i * 2, 3, Side.RED, "red-soldier"));
            pieces.add(new Soldier(i * 2, 6, Side.BLACK, "black-soldier"));
        }
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public Piece pieceAt(int col, int row) {
        for (Piece piece : pieces) {
            if (piece.getCol() == col && piece.getRow() == row) {
                return piece;
            }
        }
        return null;
    }


    /**
     * Move the piece from a coordinate to another. Use board object to interact with the piece.
     * @param currentTurn current moving turn
     * @param orgCol original column
     * @param orgRow original row
     * @param desCol destination col
     * @param desRow destination row
     * @return true if the move is executed
     */
    public boolean onMovingPiece(Side currentTurn, int orgCol, int orgRow, int desCol, int desRow) {

        Piece movingP = this.pieceAt(orgCol, orgRow);
        Piece targetP = this.pieceAt(desCol, desRow);

        if (movingP == null || currentTurn != movingP.getSide()) return false;

        if (movingP.canMoveWithoutSuicide(this, desCol, desRow)) {
            if (targetP != null) {
                pieces.remove(targetP);
            }
            movingP.setCol(desCol);
            movingP.setRow(desRow);

            updatePossibleMovesOfPieces();
            return true;
        }

        return false;
    }


    /**
     * Move the piece. After the move, check if the general is being checked or not.
     * If yes then revert the move
     * @param orgCol original col
     * @param orgRow original row
     * @param desCol destination col
     * @param desRow destination row
     * @return true if the move is good
     */
    public boolean canMoveWithoutBeingChecked(int orgCol, int orgRow, int desCol, int desRow) {
//        Move the piece. If after moving, the general is not checked than return true
        boolean result = false;

        Piece movingP = this.pieceAt(orgCol, orgRow);
        Piece targetP = this.pieceAt(desCol, desRow);

        if (targetP != null) {
            pieces.remove(targetP);
        }
        movingP.setCol(desCol);
        movingP.setRow(desRow);

        General currentTurnGeneral = (General) this.getGeneral(movingP.getSide());
        if (!currentTurnGeneral
                .isAnyEnemyCanMoveTo(this,
                        currentTurnGeneral.getCol(),
                        currentTurnGeneral.getRow())
                && !currentTurnGeneral.isGeneralExposed(this)) {
            result = true;
        }

        revertLastMove(movingP, targetP, orgCol, orgRow);

        return result;
    }

    /**
     * Revert last move.
     * @param movedPiece the piece which is moved
     * @param targetP the target piece. null if the move is just a move
     * @param orgCol  original column
     * @param orgRow  original row
     */
    private void revertLastMove(Piece movedPiece, Piece targetP, int orgCol, int orgRow) {
        movedPiece.setCol(orgCol);
        movedPiece.setRow(orgRow);
        if (targetP != null) {
            pieces.add(targetP);
        }
    }


    public void updatePossibleMovesOfPieces() {
        Piece[] arrayPieces = new Piece[pieces.size()];
        pieces.toArray(arrayPieces);

        for (Piece arrayPiece : arrayPieces) {
            arrayPiece.calculatePossibleMoves(this);
        }
    }

    public Piece getGeneral(Side side) {
        if (side == Side.RED) {
            return redGeneral;
        } else {
            return blackGeneral;
        }
    }

    public Piece getEnemyGeneral(Side currentSide) {
        if (currentSide == Side.RED) {
            return blackGeneral;
        } else {
            return redGeneral;
        }
    }

    @Override
    public String toString() {
        StringBuilder brdStr = new StringBuilder(" ");

        for (int i = 0; i < GameConstant.BOARD_COLS; i++) {
            brdStr.append("  ").append(i);
        }
        brdStr.append("\n");

        for (int row = 0; row < GameConstant.BOARD_ROWS; row++) {
            brdStr.append(row);
            for (int col = 0; col < GameConstant.BOARD_COLS; col++) {
                Piece piece = pieceAt(col, row);
                if (piece == null) {
                    brdStr.append(" . ");
                } else {
                    if (piece instanceof General) {
                        brdStr.append(piece.getSide() == Side.RED ? " Ge" : " ge");
                    } else if (piece instanceof Knight) {
                        brdStr.append(piece.getSide() == Side.RED ? " Kn" : " kn");
                    } else if (piece instanceof Bishop) {
                        brdStr.append(piece.getSide() == Side.RED ? " Bi" : " bi");
                    } else if (piece instanceof Guard) {
                        brdStr.append(piece.getSide() == Side.RED ? " Gu" : " gu");
                    } else if (piece instanceof Cannon) {
                        brdStr.append(piece.getSide() == Side.RED ? " Ca" : " ca");
                    } else if (piece instanceof Soldier) {
                        brdStr.append(piece.getSide() == Side.RED ? " So" : " so");
                    } else if (piece instanceof Chariot) {
                        brdStr.append(piece.getSide() == Side.RED ? " Ch" : " ch");
                    }
                }
            }
            brdStr.append("\n");
        }
        return brdStr.toString();
    }
}
