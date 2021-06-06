package Cell;

public class Empty extends Cell {

        public Empty(int y, int x){
            super(y, x);
            super.setState(0);
        }
}
