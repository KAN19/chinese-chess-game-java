package onlineFeature;

import gamelogic.player.Move;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public interface SocketHandler {

    public void sendPieceMove(Move move);
}
