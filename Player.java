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
}
