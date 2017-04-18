class Ship {
    private boolean orient; // false=hor, true=ver
    private int length;
    private int col;
    private int row;

    Ship(boolean orient, int length, int row, int col){
        this.length = length;
        this.orient = orient;
        this.row = row;
        this.col = col;
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
            for (int j = 0; j < this.length; j++){
                // update coord 1th ship
                if (ship.getOrient()) {
                    x1 = ship.getColumn() + i;
                    y1 = ship.getRow();
                }
                else {
                    x1 = ship.getColumn();
                    y1 = ship.getRow() + i;
                }
                // update coord 2th ship
                if (this.orient) {
                    x2 = this.col + j;
                    y2 = this.row;
                }
                else {
                    x2 = this.row;
                    y2 = this.col + j;
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
}
