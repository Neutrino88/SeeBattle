abstract class Player {
    protected Field field;
    protected Field enemyField;

    Player(Field field, Field enemyField){
        this.field = field;
        this.enemyField = enemyField;
    }

    abstract void addShips(int[] countOfShips);
    abstract String doShot();

    int getShot(int row, int col){
        return this.field.getShot(row, col);
    }
    void addEnemyShip(boolean orient, int length, int row, int col){
        this.enemyField.addShipUnsafe(orient, length, row, col);
    }

    boolean isDead(){
        return field.getLiveShips() == 0;
    }
    public String toString(){
        int prevInd = 0, ind;
        String result = "";
        String separator = "        ";
        String fieldStr = this.field.toString();
        String enemyFieldStr = this.enemyField.toString();

        for (int i = 0; i < this.field.getSize() + 2; i++){
            ind = fieldStr.indexOf("\n", prevInd);
            result += fieldStr.substring(prevInd, ind) + separator;

            if (i < 10 || i == this.field.getSize() + 1) result += " ";
            if (i == 0 || i == this.field.getSize() + 1) result += "  ";

            result += enemyFieldStr.substring(prevInd, ind+1);
            prevInd = ind + 1;
        }

        return result;
    }
}
