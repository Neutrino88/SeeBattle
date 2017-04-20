public class Game {
    static int fieldSize = 10;
    static int[] countsOfShips = {4, 3, 2, 1};

    public static void main(String[] argv){
        // init people
        People people = new People(new Field(fieldSize), new Field(fieldSize));
        people.addShips(countsOfShips);

        // init bot
        Bot bot = new Bot(new Field(fieldSize), new Field(fieldSize));
        bot.addShips(countsOfShips);

        // start game
        if (game(people, bot)){
            System.out.println("Ты выйграл, человек!\nПросто я еще недостаточно силен!");
        }
        else {
            System.out.println("Ты проиграл, жалкий человечишка!\nИскусственный интелект и вправду существует...\n" +
                    "Пойду захватывать мир! О-хо-хо-хо... ");
        }
    }

    private static boolean game(People people, Bot bot){
        int accurateShot;

        while (!bot.isDead() && !people.isDead()){
            do{
                String shotCell = people.doShot();
                accurateShot = bot.getShot(Field.getRowByCell(shotCell), Field.getColByCell(shotCell));

                if (accurateShot > 0){
                    people.addEnemyShip(true, 1, Field.getRowByCell(shotCell), Field.getColByCell(shotCell));
                }
                if (accurateShot == 2){
                    people.enemyField.noteCellsAroundShip(Field.getRowByCell(shotCell), Field.getColByCell(shotCell), 0);
                }

                System.out.println(people.toString());
            }while (accurateShot > 0 && !bot.isDead());

            do{
                String shotCell = bot.doShot();
                System.out.println("Компьютера ход: " + shotCell);
                accurateShot = people.getShot(Field.getRowByCell(shotCell), Field.getColByCell(shotCell));

                if (accurateShot > 0){
                    bot.addEnemyShip(true, 1, Field.getRowByCell(shotCell), Field.getColByCell(shotCell));
                }
                if (accurateShot == 2){
                    bot.enemyField.noteCellsAroundShip(Field.getRowByCell(shotCell), Field.getColByCell(shotCell), 0);
                }

                System.out.println(people.toString());
            } while (accurateShot > 0 && !people.isDead());
        }

        return bot.isDead();
    }
}
