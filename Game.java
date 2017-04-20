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
        boolean peopleShot = true;

        while (!bot.isDead() && !people.isDead()){
            if (peopleShot){
                String shotCell = people.doShot();
                peopleShot = bot.getShot(Field.getRowByCell(shotCell), Field.getColByCell(shotCell));

                if (peopleShot){
                    people.addEnemyShip(true, 1, Field.getRowByCell(shotCell), Field.getColByCell(shotCell));
                }
            }
            else{
                String shotCell = bot.doShot();
                System.out.println("Мой ход: " + shotCell);
                peopleShot = !people.getShot(Field.getRowByCell(shotCell), Field.getColByCell(shotCell));

                if (!peopleShot){
                    bot.addEnemyShip(true, 1, Field.getRowByCell(shotCell), Field.getColByCell(shotCell));
                }
            }

            System.out.println(people.toString());
        }

        return bot.isDead();
    }
}
