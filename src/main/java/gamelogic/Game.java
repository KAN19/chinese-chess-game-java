package gamelogic;

import constant.GameTypeEnum;
import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.pieces.*;
import gamelogic.player.Move;
import gamelogic.player.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Game {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayerTurn;
    GameStatus gameStatus;
    List<Move> redPossibleMoves = new ArrayList<>();
    List<Move> blackPossibleMoves = new ArrayList<>();

    GameTypeEnum gameType;
    String startFirst;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public Game() {
        this.board = new Board();
        this.player1 = new Player("player1", Side.RED, 1, 5, false );
        this.player2 = new Player("player2", Side.BLACK, 1, 5, false);
        this.currentPlayerTurn = player1;
        gameStatus = GameStatus.PLAYING;
        this.player1.startTimer();

    }

//    Black is computer
    public Game(GameTypeEnum gameType) {
        this.gameType = gameType;

        this.player1 = new Player("player1", Side.RED, 1, 5, false);
        this.player2 = new Player("player2", Side.BLACK, 1, 5, true);

        this.board = new Board();

        if (gameType == GameTypeEnum.BLACK_IS_COMPUTER) {
            this.currentPlayerTurn = player1;
        } else if (gameType == GameTypeEnum.RED_IS_COMPUTER) {
            this.currentPlayerTurn = player2;
        }

        gameStatus = GameStatus.PLAYING;

        this.player1.startTimer();
    }


    public void movePiece(int orgCol, int orgRow, int desCol, int desRow) {
        if (board.onMovingPiece(currentPlayerTurn.getSide(),orgCol, orgRow, desCol, desRow)) {
            updatePossibleMoves();
            shiftTurn();
            checkGameStatus();

        }
    }

    private void shiftTurn() {
        if (Objects.equals(this.currentPlayerTurn.getName(), "player1")) {

            player1.stopTimer();
            this.currentPlayerTurn = player2;
            player2.startTimer();

            //            danh cho black la computer only
            support.firePropertyChange("MoveChanged", "old ne", blackPossibleMoves);
        } else {

            player2.stopTimer();
            this.currentPlayerTurn = player1;
            player1.startTimer();
        }
//        this.turn = this.turn == Side.RED ? Side.BLACK : Side.RED;
    }

    public void checkGameStatus() {

        if (redPossibleMoves.size() == 0) {
            System.out.println(Side.BLACK + " win");
            gameStatus = GameStatus.BLACK_WIN;
            return;
        }

        if (blackPossibleMoves.size() == 0) {
            System.out.println(Side.RED + " win");
            gameStatus = GameStatus.RED_WIN;
            return;
        }

        if (isBeingCheck()) {
            System.out.println(currentPlayerTurn.getSide() + " dang bi checked");
            gameStatus = currentPlayerTurn.getSide() == Side.RED ? GameStatus.RED_BEING_CHECKED : GameStatus.BLACK_BEING_CHECKED;
        }

    }


    public boolean isBeingCheck () {
        General currentTurnGeneral = (General) board.getGeneral(currentPlayerTurn.getSide());
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
//        System.out.println("Red" + redPossibleMoves.size());
//        System.out.println("Black" + blackPossibleMoves.size());
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    public GameTypeEnum getGameType() {
        return gameType;
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

    public Player getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    enum GameStatus {
        PLAYING,
        RED_BEING_CHECKED,
        BLACK_BEING_CHECKED,
        RED_WIN,
        BLACK_WIN
    }


}
