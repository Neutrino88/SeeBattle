public class Field {
    private Cell[][] cells;
    private int size;

    public Field(int size){
        this.size = size;
        this.cells = new Cell[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.cells[i][j] = new Cell();
            }
        }
    }

    public boolean addShip(boolean orient, int length, int row, int col) {
        // if values are uncorrected
        if (orient && (row + length) > this.size) return false;
        if (orient && (col + length) > this.size) return false;

        //for (int i = 0; i < )

        // add ship in cells
        Ship ship = new Ship(orient, length);

        for (int i = 0; i < length; i++) {
            if (orient) {
                cells[row][col + i].setShip(ship);
            } else {
                cells[row + i][col].setShip(ship);
            }
        }

        return true;
    }

    public boolean doShot(int row, int col){
        return true;
    }

    @Override
    public String toString(){
        String str = "   а б в г д е ж з и й\n";

        for (int i = 0; i < this.size; i++){
            if (i < 9) str += ' ';
            str += Integer.toString(i+1) + ' ';

            for (int j = 0; j < this.size; j++)
                if (cells[i][j].getShip() == null) {
                    if (cells[i][j].isDead()) str += "* ";
                    else str += ". ";
                }
                else
                    if (cells[i][j].getShip().isDead()) {
                        str += "- ";
                    }
                    else {
                        str += "+ ";
                    }

            str += Integer.toString(i+1) + '\n';
        }

        return str + "   а б в г д е ж з и й\n";
    }
}
