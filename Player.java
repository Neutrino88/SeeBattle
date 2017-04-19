abstract class Player {
    protected Field field;
    protected Field enemyField;

    Player(Field field, Field enemyField){
        this.field = field;
        this.enemyField = enemyField;
    }

    abstract void addShips(int[] countOfShips);
    abstract String getFieldString();
    abstract String doShot();

    boolean getShot(int row, int col){
        return field.getShot(row, col);
    }

    boolean isDead(){
        return field.getLiveShips() == 0;
    }
}