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

        for (int row = 0; row < this.size; row++){
            if (row < 9) str += " ";
            str += Integer.toString(row + 1) + " ";

            for (int col = 0; col < this.size; col++){
                // finding ship
                boolean flag = false;

                for (Ship curShip : ships){
                    boolean located = curShip.locatedOn(row, col);

                    if (cells[row][col] && located){
                        str += "≡ ";
                        flag = true;
                        break;
                    }
                    if (!cells[row][col] && located){
                        str += "■ ";
                        flag = true;
                        break;
                    }
                    if (cells[row][col] && !located){
                        str += "Ο ";
                        flag = true;
                        break;
                    }
                }

                if (!flag || ships.size() == 0){
                    str += "· ";
                }
            }
            str += Integer.toString(row + 1) + "\n";
        }

        return str + "   а б в г д е ж з и й\n";
    }
}
