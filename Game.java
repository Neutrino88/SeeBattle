public class Game {
    static int fieldSize = 10;
    static int[] countsOfShips = {1, 1, 0, 1};

    public static void main(String[] argv){
        // init people
        People people = new People(new Field(fieldSize), new Field(fieldSize));
        people.addShips(countsOfShips);
        System.out.println(people.getFieldString());

        // init bot
        Bot bot = new Bot(new Field(fieldSize), new Field(fieldSize));
        bot.addShips(countsOfShips);
        System.out.println(bot.getFieldString());

        // start game
        if (game(people, bot)){
            System.out.println("Вы выйграли!\nНо я все равно хороший стратег!");
        }
        else {
            System.out.println("Вы проиграли!\nИскусственный интелект и вправду существует... Хо-хо-хо... ");
        }
    }

    private static boolean game(People people, Bot bot){
        boolean peopleShot = true;

        while (!bot.isDead() && !people.isDead()){
            if (peopleShot){
                String shotCell = people.doShot();
                peopleShot = bot.getShot(Integer.parseInt(shotCell.substring(1)) - 1, Field.getIndexCol(shotCell.charAt(0)));
            }
            else{
                String shotCell = bot.doShot();
                peopleShot = !people.getShot(Integer.parseInt(shotCell.substring(1)) - 1, Field.getIndexCol(shotCell.charAt(0)));
            }

            System.out.println(people.getFieldString());
            System.out.println(bot.getFieldString());
        }

        return bot.isDead();
    }

}
