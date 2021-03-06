package gamelogic.player;

import gamelogic.board.Side;

import java.io.Serializable;

public class Move implements Serializable {

    Side side;
    int orgCol;
    int orgRow;
    int desCol;
    int desRow;

    public Move(Side side, int orgCol, int orgRow, int desCol, int desRow) {
        this.side = side;
        this.orgCol = orgCol;
        this.orgRow = orgRow;
        this.desCol = desCol;
        this.desRow = desRow;
    }

    @Override
    public String toString() {
        return "Move{" +
                "orgCol=" + orgCol +
                ", orgRow=" + orgRow +
                ", desCol=" + desCol +
                ", desRow=" + desRow +
                "}";
    }

    public String toDisplayString() {
        return String.format("%s: [%d,%d] -> [%d,%d] \n", side.toString(), orgCol, orgRow, desCol, desRow);
    }

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
