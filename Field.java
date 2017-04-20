import java.util.ArrayList;

class Field {
    static final String cols = "абвгдежзиклмнопрстуфхцчшыэюя";
    private ArrayList<Ship> ships;
    private boolean[][] cells;
    private int size;
    private int liveShips;

    Field(int size){
        this.size = size > cols.length() ? cols.length() : size;
        this.cells = new boolean[size][size];
        this.ships = new ArrayList<Ship>();
        this.liveShips = 0;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.cells[i][j] = false;
            }
        }
    }

    int getSize(){
        return this.size;
    }
    int getLiveShips(){
        return liveShips;
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

        this.liveShips++;
        this.ships.add(newShip);
        return true;
    }

    void addShipUnsafe(boolean orient, int length, int row, int col){
        Ship newShip = new Ship(orient, length, row, col);
        this.ships.add(newShip);
        this.liveShips++;
    }

    boolean getShot(int row, int col){
        if (this.cells[row][col]){
            return false;
        }
        this.cells[row][col] = true;

        for (Ship ship : ships){
            if (ship.locatedOn(row, col)){
                ship.getShot();

                if (ship.getLivesNumber() == 0){ // ship is killed
                    this.liveShips--;
                    showAllShip(row, col, 0);
                }
                return true;
            }
        }

        return false;
    }

    void showAllShip(int row, int col, int orient){
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (row + i >= 0 && row + i < this.size &&
                        col + j >= 0 && col + j < this.size) {
                    cells[row + i][col + j] = true;
                }
            }
        }

        for (Ship ship : ships) {
            if (orient < 1) {
                if (row > 0 && !cells[row-1][col] && ship.locatedOn(row-1, col)){
                    showAllShip(row-1, col, -1);
                }
                if (col-1 > 0 && !cells[row][col-1] && ship.locatedOn(row, col-1)){
                    showAllShip(row, col-1, -1);
                }
            }
            if (orient > -1) {
                if (row + 1 < this.size && !cells[row + 1][col] && ship.locatedOn(row + 1, col)) {
                    showAllShip(row + 1, col, 1);
                }

                if (col + 1 < this.size && !cells[row][col + 1] && ship.locatedOn(row, col + 1)) {
                    showAllShip(row, col + 1, 1);
                }
            }
        }
    }

    public String toString(){
        String str = "  ";
        for (int i = 0; i < this.size; i++){
            str += " " + Field.cols.charAt(i);
        }
        str += "\n";

        for (int row = 0; row < this.size; row++){
            if (row < 9) str += " ";
            str += Integer.toString(row + 1) + " ";

            for (int col = 0; col < this.size; col++){
                // finding ship
                boolean flag = false;

                for (Ship curShip : this.ships){
                    boolean isLocated = curShip.locatedOn(row, col);

                    if (this.cells[row][col] && isLocated){
                        str += "≡ ";
                        flag = true;
                        break;
                    }
                    if (!this.cells[row][col] && isLocated){
                        str += "■ ";
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    if (!this.cells[row][col]) {
                        str += "· ";
                    } else {
                        str += "Ο ";
                    }
                }
            }
            str += Integer.toString(row + 1) + "\n";
        }

        return str + str.substring(0, 4 + this.size * 2);
    }

    static int getIndexCol(char symbCol){
        for (int i = 0; i < cols.length(); i++){
            if (symbCol == cols.charAt(i)){
                return i;
            }
        }
        return 0;
    }
    static int getRowByCell(String cell){
        return Integer.parseInt(cell.substring(1)) - 1;
    }
    static int getColByCell(String cell){
        return Field.getIndexCol(cell.charAt(0));
    }
    static String getCellByRowCol(int row, int col){
        return "" + cols.charAt(col) + Integer.toString(row + 1);
    }
}
