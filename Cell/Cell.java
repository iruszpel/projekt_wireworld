package Cell;

import java.io.Serializable;

public class Cell implements Serializable {
    private int x;
    private int y;
    private int state;

    public Cell( int y, int x) {
        this.x = x;
        this.y = y;
        this.state = 0;
    }

    public int getState(){
        return state;
    }

    void setState(int state){
        this.state = state;
    }


    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}
