package onlineFeature;

import gamelogic.Game;
import gamelogic.player.Move;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable, SocketHandler{
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private boolean done;
    private int port;

    private Game game;

    public Client(Game game, int port) {
        this.game = game;
        this.port = port;
    }

    @Override
    public void run() {
        try{
            client = new Socket("127.0.0.1", 9999);

            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream((client.getInputStream()));

//            InputHandler inHandler = new InputHandler();
//            Thread t = new Thread(inHandler);
//            t.start();

            Object inComingObject;
            while((inComingObject = objectInputStream.readObject()) != null) {
                Move move = (Move) inComingObject;
//                System.out.println("Nhan tu server ne: " + move);
                movePieceFromLAN(move);
            }

        } catch (IOException e) {
            shutdown();
            client = null;
        } catch (ClassNotFoundException e) {
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

    @Override
    public void sendPieceMove(Move move) {
        try {
            objectOutputStream.writeObject(move);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        done = true;
        try {
            objectInputStream.close();
            objectOutputStream.close();
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try{
               BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
               while (!done) {
                    String message = inReader.readLine();
                    if (message.equals("/quit")) {
                        inReader.close();
                        shutdown();
                    } else {
//                        out.println(message);
                    }
               }
            } catch (IOException e) {
                shutdown();
            }
        }
    }

    public Socket getClient() {
        return client;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public boolean isDone() {
        return done;
    }
}
