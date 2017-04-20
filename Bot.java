import java.util.Random;

class Bot extends Player{
    Bot(Field field, Field enemyField){
        super(field, enemyField);
    }

    void addShips(int[] countOfShips){
        Random rand = new Random();
        int allShips = 0;

        // counting all ships
        for (int numberShips : countOfShips){
            allShips += numberShips;
        }

        int indShip = countOfShips.length - 1;
        while (allShips > 0){
            // choose ship
            for (; countOfShips[indShip] == 0; --indShip);

            // add ship on field
            int col = rand.nextInt(field.getSize());
            int row = rand.nextInt(field.getSize());

            while (!this.field.addShip(rand.nextBoolean(), indShip + 1, row, col)) {
                col = rand.nextInt(field.getSize());
                row = rand.nextInt(field.getSize());
            }
            --countOfShips[indShip];
            --allShips;
        }
    }

    String getFieldString(){
        return field.toString();
    }

    String doShot(){
        Random rand = new Random();

        int row = rand.nextInt(field.getSize());
        int col = rand.nextInt(field.getSize());
        String cell = Field.getCellByRowCol(row, col);

        enemyField.getShot(row, col);
        return cell;
    }
}
