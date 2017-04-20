public class Game {
    static int fieldSize = 10;
    static int[] countsOfShips = {0, 1, 0, 1};

    public static void main(String[] argv){
        // init people
        People people = new People(new Field(fieldSize), new Field(fieldSize));
        people.addShips(countsOfShips);

        // init bot
        Bot bot = new Bot(new Field(fieldSize), new Field(fieldSize));
        bot.addShips(countsOfShips);
        System.out.println(bot.getFieldString());

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
        boolean accurateShot;

        while (!bot.isDead() && !people.isDead()){
            do{
                String shotCell = people.doShot();
                accurateShot = bot.getShot(Field.getRowByCell(shotCell), Field.getColByCell(shotCell));

                if (accurateShot){
                    people.addEnemyShip(true, 1, Field.getRowByCell(shotCell), Field.getColByCell(shotCell));
                }
                System.out.println(people.toString());
            }while (accurateShot);

            do{
                String shotCell = bot.doShot();
                System.out.println("Компьютера ход: " + shotCell);
                accurateShot = people.getShot(Field.getRowByCell(shotCell), Field.getColByCell(shotCell));

                if (accurateShot){
                    bot.addEnemyShip(true, 1, Field.getRowByCell(shotCell), Field.getColByCell(shotCell));
                }
                System.out.println(people.toString());
            } while (accurateShot);
        }

        return bot.isDead();
    }
}
