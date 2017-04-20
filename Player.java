abstract class Player {
    protected Field field;
    protected Field enemyField;

    Player(Field field, Field enemyField){
        this.field = field;
        this.enemyField = enemyField;
    }

    abstract void addShips(int[] countOfShips);
    abstract String doShot();

    boolean getShot(int row, int col){
        return field.getShot(row, col);
    }
    void addEnemyShip(boolean orient, int length, int row, int col){
        enemyField.addShip(orient, length, row, col);
    }

    boolean isDead(){
        return field.getLiveShips() == 0;
    }

    public String toString(){
        int prevInd = 0, ind;
        String result = "";
        String separator = "        ";
        String fieldStr = field.toString();
        String enemyFieldStr = enemyField.toString();

        for (int i = 0; i < field.getSize() + 2; i++){
            ind = fieldStr.indexOf("\n", prevInd);
            result += fieldStr.substring(prevInd, ind) + separator;

            if (i < 10 || i == field.getSize() + 1) result += " ";
            if (i == 0 || i == field.getSize() + 1) result += "  ";

            result += enemyFieldStr.substring(prevInd, ind+1);
            prevInd = ind + 1;
        }

        return result;
    }
}
