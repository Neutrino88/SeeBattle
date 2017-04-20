public class Game {
    static int fieldSize = 10;
    static int[] countsOfShips = {4, 3, 2, 1};

    public static void main(String[] argv){
        // init person
        Person person = new Person(new Field(fieldSize), new Field(fieldSize));
        person.addShips(countsOfShips);

        // init bot
        Bot bot = new Bot(new Field(fieldSize), new Field(fieldSize));
        bot.addShips(countsOfShips);

        // start game
        if (game(person, bot)){
            System.out.println("Ты выйграл, человек!\nПросто я еще недостаточно силен!");
        }
        else {
            System.out.println("Ты проиграл, жалкий человечишка!\nИскусственный интелект и вправду существует...\n" +
                    "Пойду захватывать мир! О-хо-хо-хо... ");
        }
    }

    private static boolean game(Person person, Bot bot){
        int accurateShot;

        while (!bot.isDead() && !person.isDead()){
            do{
                String shotCell = person.doShot();
                accurateShot = bot.getShot(Field.getRowByCell(shotCell), Field.getColByCell(shotCell));

                if (accurateShot > 0){
                    person.addEnemyShip(true, 1, Field.getRowByCell(shotCell), Field.getColByCell(shotCell));
                }
                if (accurateShot == 2){
                    person.enemyField.noteCellsAroundShip(Field.getRowByCell(shotCell), Field.getColByCell(shotCell), 0);
                }

                System.out.println(person.toString());
            }while (accurateShot > 0 && !bot.isDead());

            do{
                String shotCell = bot.doShot();
                System.out.println("Компьютера ход: " + shotCell);
                accurateShot = person.getShot(Field.getRowByCell(shotCell), Field.getColByCell(shotCell));

                if (accurateShot > 0){
                    bot.addEnemyShip(true, 1, Field.getRowByCell(shotCell), Field.getColByCell(shotCell));
                }
                if (accurateShot == 2){
                    bot.enemyField.noteCellsAroundShip(Field.getRowByCell(shotCell), Field.getColByCell(shotCell), 0);
                }

                System.out.println(person.toString());
            } while (accurateShot > 0 && !person.isDead());
        }

        return bot.isDead();
    }
}
