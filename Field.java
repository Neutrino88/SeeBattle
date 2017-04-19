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

    boolean addShip(boolean orient, int length, int row, char symbCol) {
        // char column to int column
        int col = getIndexCol(symbCol);

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

    boolean getShot(int row, int col){
        if (cells[row][col]){
            return false;
        }
        cells[row][col] = true;

        for (int i = 0; i < ships.size(); i++){
            Ship ship = ships.get(i);

            if (ship.locatedOn(row, col)){
                if (ship.getLivesNumber() > 1){ // ship is wounded
                    ship.getShot();
                    return true;
                }
                else{ // ship is killed
                    liveShips--;
                }
            }
        }

        return false;
    }

    public String toString(){
        String str = "  ";
        for (int i = 0; i < this.size; i++){
            str += " " + cols.charAt(i);
        }
        str += "\n";

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
}
