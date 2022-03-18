package gamelogic;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.pieces.General;
import gamelogic.pieces.Piece;
import gamelogic.player.Move;
import gamelogic.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    Board board;
    Player player1;
    Player player2;
    Side turn = Side.RED;
    GameStatus gameStatus;
    List<Move> redPossibleMoves ;
    List<Move> blackPossibleMoves;

    public Game() {
        this.board = new Board();
        this.player1 = new Player(this.board, Side.RED);
        this.player2 = new Player(this.board, Side.BLACK);
        gameStatus = GameStatus.PLAYING;
        redPossibleMoves = new ArrayList<>();
        blackPossibleMoves = new ArrayList<>();
    }

    public void movePiece(int orgCol, int orgRow, int desCol, int desRow) {

        if (board.onMovingPiece(turn ,orgCol, orgRow, desCol, desRow)) {
            shiftTurn();
        }

//        if (doTheMoveAction(movingP, desCol, desRow)) {
//
//            checkGameStatus();
//            //Co van de xiu
//            if (isSuicide()) {
//                revertLastMove(movingP, targetP, orgCol, orgRow);
//            } else {
//                changeTheTurn();
//            }
//            updatePossibleMoves();
//        }
    }

    private boolean isSuicide() {
        return turn == Side.RED && gameStatus == GameStatus.RED_BEING_CHECKED
                || turn == Side.BLACK && gameStatus == GameStatus.BLACK_BEING_CHECKED;
    }

    private boolean doTheMoveAction(Piece movingP, int desCol, int desRow) {
        Piece targetP = board.pieceAt(desCol, desRow);

        if (movingP.canMoveWithCheckGeneral(board, desCol, desRow)) {
            if (targetP != null) {
                board.removePieceAt(targetP);
            }
            movingP.setCol(desCol);
            movingP.setRow(desRow);

            return true;

        }
        return false;
    }

    private void revertLastMove(Piece movedPiece, Piece targetP, int orgCol, int orgRow) {
        movedPiece.setCol(orgCol);
        movedPiece.setRow(orgRow);
        if (targetP != null) {
            board.getPieces().add(targetP);
        }
    }

    private void shiftTurn() {
        this.turn = this.turn == Side.RED ? Side.BLACK : Side.RED;
    }

    public void checkGameStatus() {
        if (isBeingCheck()) {
            System.out.println(turn + " dang bi checked");
            gameStatus = turn == Side.RED ? GameStatus.RED_BEING_CHECKED : GameStatus.BLACK_BEING_CHECKED;
        } else {
            gameStatus = GameStatus.PLAYING;
        }
    }


    public boolean isBeingCheck () {
        General currentTurnGeneral = (General) board.getGeneral(turn);
        return currentTurnGeneral
                .isAnyEnemyCanMoveTo(this.board,
                        currentTurnGeneral.getCol(),
                        currentTurnGeneral.getRow());
    }


    private void updatePossibleMoves() {
        redPossibleMoves.clear();
        blackPossibleMoves.clear();

        for (Piece piece : board.getPieces()) {
            List<Move> piecePossibleMoves = piece.calculatePossibleMoves(board);
            if (piecePossibleMoves != null) {
                if (piece.getSide() == Side.RED) {
                    redPossibleMoves.addAll(piecePossibleMoves);
                } else {
                    blackPossibleMoves.addAll(piecePossibleMoves);
                }
            }
        }
        System.out.println("Red" + redPossibleMoves);
        System.out.println("Black" + blackPossibleMoves);
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Side getTurn() {
        return turn;
    }

    enum GameStatus {
        PLAYING,
        RED_BEING_CHECKED,
        BLACK_BEING_CHECKED,
        RED_WIN,
        BLACK_WIN
    }

}
