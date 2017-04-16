public class Field {
    private Cell[][] cells;
    private int size;

    public Field(int size){
        this.size = size;
        this.cells = new Cell[size][size];
    }

    public boolean doShot(int row, int col){
        return true;
    }
}
