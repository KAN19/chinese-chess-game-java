package gamelogic;

import constant.GameTypeEnum;
import gamelogic.board.Board;
import gamelogic.board.Side;
import gamelogic.pieces.*;
import gamelogic.player.Move;
import gamelogic.player.Player;
import onlineFeature.Client;
import onlineFeature.Server;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Game implements PropertyChangeListener {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayerTurn;
    GameStatus gameStatus;
    List<Move> redPossibleMoves = new ArrayList<>();
    List<Move> blackPossibleMoves = new ArrayList<>();

    GameTypeEnum gameType;

    private Client client;
    private Server server;

    private String listMoves = "";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Game(GameTypeEnum gameType) {
        this.gameType = gameType;
        this.gameStatus = GameStatus.PLAYING;
        this.board = new Board();

        this.player1 = new Player("player1", Side.RED, 1, 5, false );
        this.player2 = new Player("player2", Side.BLACK, 1, 5, false);

        this.currentPlayerTurn = player1;

        switch (gameType) {
            case BLACK_IS_COMPUTER:
                this.player2.setComputer(true);
                break;
            case P2P_OFFLINE:
                break;
            case P2P_ONLINE:
                joinRoom(111);
                break;
        }

//        this.player1.startTimer();
    }


    /**
     * move piece action executing by game controller. All the outside interaction should go through this.
     * @param orgCol original column
     * @param orgRow original row
     * @param desCol destination column
     * @param desRow destination row
     * @return boolean if the move is executed successfully
     */
    public boolean movePiece(int orgCol, int orgRow, int desCol, int desRow) {
        if (board.onMovingPiece(currentPlayerTurn.getSide(),orgCol, orgRow, desCol, desRow)) {
            saveTheMove(new Move(currentPlayerTurn.getSide(),orgCol, orgRow, desCol, desRow));
            updatePossibleMoves();
            shiftTurn();
            checkGameStatus();
            return true;
        }
        return false;

    }


    /**
     * Save all the move of the game. Those moves will be requested to save after the game is end.
     * @param move
     */
    private void saveTheMove(Move move) {
        listMoves += move.toDisplayString();

        support.firePropertyChange("listMoveInTextFieldChanged", "", move.toDisplayString());
    }

    /**
     * shift the turn of the game. Based on the currentPlayerTurn attribute
     */
    private void shiftTurn() {
        if (Objects.equals(this.currentPlayerTurn.getName(), "player1")) {

            player1.stopTimer();
            this.currentPlayerTurn = player2;
            player2.startTimer();

            //            danh cho black la computer only
            support.firePropertyChange("possibleMovesForComputer", "old ne", blackPossibleMoves);
        } else {

            player2.stopTimer();
            this.currentPlayerTurn = player1;
            player1.startTimer();
        }
//        this.turn = this.turn == Side.RED ? Side.BLACK : Side.RED;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
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
            support.firePropertyChange("isBeingChecked", "old ne", gameStatus);
            return;
        }

        gameStatus = GameStatus.PLAYING;

    }


    /**
     * use the board and the coordination of 2 general to check if one side is being checked.
     * @return true if the current player turn is being checked
     */
    public boolean isBeingCheck () {
        General currentTurnGeneral = (General) board.getGeneral(currentPlayerTurn.getSide());
        return currentTurnGeneral
                .isAnyEnemyCanMoveTo(this.board,
                        currentTurnGeneral.getCol(),
                        currentTurnGeneral.getRow());
    }


    /**
     * Update all possible moves of the 2 side of player.
     * This function loop through all the pieces and calculate their possible moves.
     * Then it accumulates them into one specific variable
     */
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

    private void createServer(int port) {

        server = new Server(this, port);
        if (server.getServer() != null) {
            Thread thread = new Thread(server);
            thread.start();
        } else {
            server = null;
        }

    }

    private void createClient(int port) {
        client = new Client(this, port);
        Thread thread = new Thread(client);
        thread.start();

    }

    private void joinRoom(int port) {
        createServer(port);
        if (server == null) {
            createClient(port);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getNewValue());
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

    public Client getClient() {
        return client;
    }

    public Server getServer() {
        return server;
    }



    public enum GameStatus {
        PLAYING,
        RED_BEING_CHECKED,
        BLACK_BEING_CHECKED,
        RED_WIN,
        BLACK_WIN
    }


}
