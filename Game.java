import java.util.Scanner;

public class Game {
    public static int fieldSize = 8;
    private static int[] countsOfShips = {0, 2, 2, 0, 1};

    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);

        Field playerField = new Field(fieldSize);

        //System.out.println(playerField.toString());
        //inputPlayerShips(playerField, scanner);

        Field botField = new Field(fieldSize);
        botField.addShipsRandomly(countsOfShips);
        System.out.println(botField.toString());

    }

    private static void inputPlayerShips(Field field, Scanner scanner){
        for (int lenShip = 1; lenShip <= countsOfShips.length; lenShip++){
            for (int numberShip = 0; numberShip < countsOfShips[lenShip-1]; numberShip++){
                String cell = "";

                System.out.print(Integer.toString(lenShip) + "-палубный корабль:\n   Первая клетка (формат г4): ");
                while (true) {
                    cell = inputValue(scanner, "", "^[" + field.rows.substring(0, field.getSize()) + "](\\d?\\d)$");

                    if (new Integer(cell.substring(1)) - 1 < field.getSize()){
                        break;
                    }

                    System.out.print("Некорректное значение! Попробуйте еще раз: ");
                }

                String orient = "в";
                if (lenShip > 1) {
                    orient = inputValue(scanner, "   Горизонтально - г, вертикально - в: ", "^[вг]$");
                }

                if (!field.addShip(orient.equals("в"), lenShip, new Integer(cell.substring(1)) - 1, cell.charAt(0))) {
                    System.out.println("Установите корабль в другое место!");
                    numberShip--;
                    continue;
                }

                System.out.println('\n' + field.toString());
            }
        }
    }

    private static String inputValue(Scanner scanner, String title, String regexp){
        System.out.print(title);

        while(true) {
            String value = scanner.next();

            if (!value.matches(regexp)){
                System.out.print("Некорректное значение! Попробуйте еще раз: ");
                continue;
            }
            return value;
        }
    }
}
