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
            System.out.println("Вы выйграли!\nВы хороший стратег!");
        }
        else {
            System.out.println("Вы проиграли!\nИскусственный интелект существует ...");
        }
    }

    private static boolean game(People people, Bot bot){
        return true;
    }

}
