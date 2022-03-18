package gamelogic.player;

import gamelogic.board.Side;

public class Move {

    Side side;
    int orgCol;
    int orgRow;
    int desCol;
    int desRow;

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public int getOrgCol() {
        return orgCol;
    }

    public void setOrgCol(int orgCol) {
        this.orgCol = orgCol;
    }

    public int getOrgRow() {
        return orgRow;
    }

    public void setOrgRow(int orgRow) {
        this.orgRow = orgRow;
    }

    public int getDesCol() {
        return desCol;
    }

    public void setDesCol(int desCol) {
        this.desCol = desCol;
    }

    public int getDesRow() {
        return desRow;
    }

    public void setDesRow(int desRow) {
        this.desRow = desRow;
    }
}
