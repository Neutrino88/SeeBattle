import java.util.ArrayList;
import java.util.Random;

class Field {
    private ArrayList<Ship> ships;
    private boolean[][] cells;
    private int size;
    public static String rows = "абвгдежзиклмнопрстуфхцчшыэюя";

    Field(int size){
        this.size = size > rows.length() ? rows.length() : size;
        this.cells = new boolean[size][size];
        this.ships = new ArrayList<Ship>();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.cells[i][j] = false;
            }
        }
    }

    int getSize(){
        return this.size;
    }

    boolean addShip(boolean orient, int length, int row, char symbCol) {
        // char column to int column
        int col = 0;
        for (int i = 0; i < rows.length(); i++){
            if (symbCol == rows.charAt(i)){
                col = i;
                break;
            }
        }

        // if values are uncorrected
        if (orient && (row + length) > this.size) return false;
        if (!orient && (col + length) > this.size) return false;

        Ship newShip = new Ship(orient, length, row, col);
        // add ship in cells
        for (Ship curShip : ships) {
            if (newShip.overlay(curShip))
                return false;
        }

        this.ships.add(newShip);
        return true;
    }

    boolean doShot(int row, int col){
        return true;
    }

    @Override
    public String toString(){
        String str = "  ";
        for (int i = 0; i < this.size; i++){
            str += " " + rows.charAt(i);
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

    void addShipsRandomly(int[] countOfShips){
        Random rand = new Random();
        int allShips = 0;

        // delete all ships from field
        ships.clear();

        // counting all ships
        for (int numberShips : countOfShips){
            allShips += numberShips;
        }

        while (allShips > 0){
            // choose ship
            int indShip = rand.nextInt(countOfShips.length);

            while (countOfShips[indShip] == 0){
                indShip = (indShip + 1) % countOfShips.length;
            }
            // add ship on field
            char col = rows.charAt(rand.nextInt(this.size));
            int row = rand.nextInt(this.size);

            while (!this.addShip(rand.nextBoolean(), indShip + 1, row, col)) {
                col = rows.charAt(rand.nextInt(this.size));
                row = rand.nextInt(this.size);
            }
            --countOfShips[indShip];
            --allShips;
        }
    }
}
