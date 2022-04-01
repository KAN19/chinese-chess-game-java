package gamelogic;

import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.pieces.*;
import gamelogic.player.Move;
import gamelogic.player.Player;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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
        this.player1 = new Player(Side.RED, 1, 5);
        this.player2 = new Player(Side.BLACK, 1, 5);
        gameStatus = GameStatus.PLAYING;
        redPossibleMoves = new ArrayList<>();
        blackPossibleMoves = new ArrayList<>();

        this.player1.startTimer();

    }


    public void movePiece(int orgCol, int orgRow, int desCol, int desRow) {

        if (board.onMovingPiece(turn ,orgCol, orgRow, desCol, desRow)) {
            updatePossibleMoves();
            shiftTurn();
            checkGameStatus();
        }
    }

    private void shiftTurn() {
        if (this.turn == Side.RED) {
            player1.stopTimer();
            this.turn = Side.BLACK;
            player2.startTimer();
        } else {
            player2.stopTimer();
            this.turn = Side.RED;
            player1.startTimer();
        }
//        this.turn = this.turn == Side.RED ? Side.BLACK : Side.RED;
    }

    public void checkGameStatus() {

        if (redPossibleMoves.size() == 0) {
            System.out.println(Side.RED + " win");
            gameStatus = GameStatus.BLACK_WIN;
            return;
        }

        if (blackPossibleMoves.size() == 0) {
            System.out.println(Side.BLACK + " win");
            gameStatus = GameStatus.RED_WIN;
            return;
        }

        if (isBeingCheck()) {
            System.out.println(turn + " dang bi checked");
            gameStatus = turn == Side.RED ? GameStatus.RED_BEING_CHECKED : GameStatus.BLACK_BEING_CHECKED;
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

            Set<Piece> pieces = board.getPieces();

        for (Piece piece : pieces) {
//            if (piece instanceof Soldier) {
                List<Move> piecePossibleMoves = piece.getPossibleMoves();
                if (piecePossibleMoves != null) {
                    if (piece.getSide() == Side.RED) {
                        redPossibleMoves.addAll(piecePossibleMoves);
                    } else {
                        blackPossibleMoves.addAll(piecePossibleMoves);
                    }
                }
//            }
        }
        System.out.println("Red" + redPossibleMoves.size());
        System.out.println("Black" + blackPossibleMoves.size());
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
