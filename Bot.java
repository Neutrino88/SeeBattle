import java.util.Random;

public class Bot {
    private Field field;

    Bot(Field field){
        this.field = field;
    }

    void addShips(int[] countOfShips){
        Random rand = new Random();
        int allShips = 0;

        // delete all ships from field

        // counting all ships
        for (int numberShips : countOfShips){
            allShips += numberShips;
        }

        int indShip = countOfShips.length - 1;
        while (allShips > 0){
            // choose ship
            for (; countOfShips[indShip] == 0; --indShip);

            // add ship on field
            char col = Field.rows.charAt(rand.nextInt(field.getSize()));
            int row = rand.nextInt(field.getSize());

            while (!this.field.addShip(rand.nextBoolean(), indShip + 1, row, col)) {
                col = Field.rows.charAt(rand.nextInt(field.getSize()));
                row = rand.nextInt(field.getSize());
            }
            --countOfShips[indShip];
            --allShips;
        }
    }

    String getFieldString(){
        return field.toString();
    }
}
