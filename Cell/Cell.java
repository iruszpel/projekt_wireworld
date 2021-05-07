package Cell;

public class Cell {
    int x;
    int y;
    int state;

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

    //funkcje nie były przewidziane w diagramie klas
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}
