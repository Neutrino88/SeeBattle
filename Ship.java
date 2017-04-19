class Ship {
    private boolean orient; // false=hor, true=ver
    private int livesNumber;
    private int length;
    private int col;
    private int row;

    Ship(boolean orient, int length, int row, int col){
        this.livesNumber = length;
        this.length = length;
        this.orient = orient;
        this.row = row;
        this.col = col;
    }

    int getLivesNumber(){
        return this.livesNumber;
    }
    boolean getOrient(){
        return this.orient;
    }
    int getLength(){
        return this.length;
    }
    int getColumn(){
        return this.col;
    }
    int getRow(){
        return this.row;
    }

    boolean overlay(Ship ship){
        int x1, y1;
        int x2, y2;

        for (int i = 0; i < ship.getLength(); i++){
            // update coord 1th ship
            if (ship.getOrient()) {
                x1 = ship.getColumn();
                y1 = ship.getRow() + i;
            }
            else {
                x1 = ship.getColumn() + i;
                y1 = ship.getRow();
            }

            for (int j = 0; j < this.length; j++){
                // update coord 2th ship
                if (this.orient) { // ver
                    x2 = this.col;
                    y2 = this.row + j;
                }
                else {
                    x2 = this.col + j;
                    y2 = this.row;
                }

                if (x1 - 1 <= x2 && x2 <= x1 + 1)
                    if (y1 - 1 <= y2 && y2 <= y1 + 1)
                        return true;
            }
        }

        return false;
    }

    boolean locatedOn(int row, int col) {
        int x, y;

        for (int i = 0; i < this.length; i++) {
            if (this.orient) { // ver
                y = this.row + i;
                x = this.col;
            } else {
                y = this.row; // hor
                x = this.col + i;
            }

            if (x == col && y == row) {
                return true;
            }
        }

        return false;
    }

    void getShot(){
        this.livesNumber--;
    }
}
