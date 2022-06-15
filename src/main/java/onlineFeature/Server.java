package onlineFeature;

import gamelogic.Game;
import gamelogic.player.Move;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable, SocketHandler {

    private ServerSocket server;
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean done;
private int port;
    private Game game;


    public Server(Game game, int port) {
        this.game = game;
        this.port = port;
        done = false;
        try {
            server = new ServerSocket(9999);
        } catch (IOException e) {
            server = null;
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (!done) {
                client = server.accept();

                out = new ObjectOutputStream(client.getOutputStream());
                in = new ObjectInputStream((client.getInputStream()));

                Object inComingObject;
                while((inComingObject = in.readObject()) != null) {
                    Move move = (Move) inComingObject;
//                    System.out.println("Nhan tu client ne: " + move);
                    movePieceFromLAN(move);
                }
            }
        } catch (IOException e) {
            shutdown();
            server = null;
            client = null;
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendPieceMove(Move move) {
        try {
            out.writeObject(move);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void movePieceFromLAN(Move move) {
        if (game.movePiece(move.getOrgCol(), move.getOrgRow(), move.getDesCol(), move.getDesRow())) {
            System.out.println("Move piece thanh cong");
        } else {
            System.out.println("Move that bai");
        }
    }

    public void shutdown() {
        try {
            done = true;
            if (!server.isClosed()) {
                server.close();
            }
            in.close();
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ServerSocket getServer() {
        return server;
    }



    public boolean isDone() {
        return done;
    }


}
