package gamelogic.player;

import gamelogic.board.Side;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.List;

public class Player {

    private Side side;
    private List<Move> moveList;
    private Timer timer;
    private int second, minute;


    private String ddSecond, ddMinute;
    private final DecimalFormat dformat = new DecimalFormat("00");

    public Player(Side side, int minute, int second) {
        this.side = side;
        this.minute = minute;
        this.second = second;

        initTimer();
    }

    public void initTimer() {
        this.timer = new Timer(1000, e -> {

            ddSecond = dformat.format(second);
            ddMinute = dformat.format(minute);
            System.out.println(side + " Time left: " + ddMinute + ":" + ddSecond);
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

    public void setSide(Side side) {
        this.side = side;
    }

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
}
