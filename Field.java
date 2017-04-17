import java.util.ArrayList;

class Field {
    private ArrayList<Ship> ships;
    private boolean[][] cells;
    private int size;

    Field(int size){
        this.size = size;
        this.cells = new boolean[size][size];
        this.ships = new ArrayList<Ship>();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.cells[i][j] = false;
            }
        }
    }

    boolean addShip(boolean orient, int length, int row, int col) {
        // if values are uncorrected
        if (orient && (row + length) > this.size) return false;
        if (!orient && (col + length) > this.size) return false;

        Ship newShip = new Ship(orient, length, row, col);
        // add ship in cells
        for (Ship curShip : ships) {
            if (newShip.overlay(curShip))
                return false;
        }

        ships.add(newShip);
        return true;
    }

    public boolean doShot(int row, int col){
        return true;
    }

    @Override
    public String toString(){
        String str = "   а б в г д е ж з и й\n";

        for (int i = 0; i < this.size; i++){
            if (i < 9) str += " ";
            str += Integer.toString(i+1) + " ";

            for (int j = 0; j < this.size; j++){
                // finding ship
                for (Ship curShip : ships){
                    if (cells[i][j] && curShip.locatedOn(i, j)){
                        str += "≡ ";
                        break;
                    }
                    if (!cells[i][j] && curShip.locatedOn(i, j)){
                        str += "■ ";
                        break;
                    }
                    if (cells[i][j] && !curShip.locatedOn(i, j)){
                        str += "Ο ";
                        break;
                    }
                    if (!cells[i][j] && !curShip.locatedOn(i, j)){
                        str += "· ";
                        break;
                    }
                }

                if (ships.size() == 0){
                    str += "· ";
                }
            }
            str += Integer.toString(i+1) + "\n";
        }

        return str + "   а б в г д е ж з и й\n";
    }
}
