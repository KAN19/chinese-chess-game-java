package gamelogic.player;

import gamelogic.board.Side;
import onlineFeature.Client;
import onlineFeature.SocketHandler;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DecimalFormat;
import java.util.List;

public class Player {

    private Side side;
    private List<Move> moveList;
    private Timer timer;
    private int second, minute;
    private String lastDuration ;
    private String currentDuration ;

    Client client;

    private String name;

    private boolean isComputer;

    private SocketHandler socketActionManager;

    private String ddSecond, ddMinute;
    private final DecimalFormat dformat = new DecimalFormat("00");

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public Player(String name, Side side, int minute, int second, boolean isComputer) {
        this.name = name;
        this.side = side;
        this.minute = minute;
        this.second = second;
        this.isComputer = isComputer;

        ddSecond = dformat.format(second);
        ddMinute = dformat.format(minute);

        currentDuration = ddMinute + ":" + ddSecond;
        initTimer();
    }

    public void initTimer() {
        this.timer = new Timer(1000, e -> {

            ddSecond = dformat.format(second);
            ddMinute = dformat.format(minute);
//            System.out.println(side + " Time left: " + ddMinute + ":" + ddSecond);

            lastDuration = currentDuration;
            currentDuration = ddMinute + ":" + ddSecond;

            setResult(currentDuration);
            second--;


            if (second == 0 && minute > 0) {
                second = 59;
                minute--;
            } else if (second <= 0 && minute <= 0) {
                System.out.println(side + " het gio");
                this.timer.stop();
            }
        });
    }

    public void setResult(String newValue) {
        support.firePropertyChange("currentDurationChanged", lastDuration, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        support.removePropertyChangeListener(l);
    }

    public String getName() {
        return name;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public String getCurrentDuration() {
        return currentDuration;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }

    public String getDdSecond() {
        return ddSecond;
    }

    public String getDdMinute() {
        return ddMinute;
    }

    public void startTimer() {
        this.timer.start();
    }

    public void stopTimer() {
        this.timer.stop();
    }

    public Side getSide() {
        return side;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    //    public void setSide(Side side) {
//        this.side = side;
//    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSocketActionManager(SocketHandler socketActionManager) {
        this.socketActionManager = socketActionManager;
    }

    public SocketHandler getSocketActionManager() {
        return socketActionManager;
    }
}
