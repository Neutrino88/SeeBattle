import java.util.Scanner;

public class Game {
    private static int[] countsOfShips = {4, 3, 2, 1};

    public static void main(String[] argv){
        Field field = new Field(10);
        Scanner scanner = new Scanner(System.in);

        inputPlayerShips(field, scanner);

    }

    private static void inputPlayerShips(Field field, Scanner scanner){
        for (int lenShip = 1; lenShip <= countsOfShips.length; lenShip++){
            for (int numberShip = 0; numberShip < countsOfShips[lenShip-1]; numberShip++){
                String cell = inputValue(scanner,
                        Integer.toString(lenShip) + "-палубный корабль:\n   Первая клетка (формат г4): ",
                        "^[абвгдежзий](10|[1-9])$");

                String orient = "в";
                if (lenShip > 1) {
                    orient = inputValue(scanner, "   Горизонтально - г, вертикально - в: ", "^[вг]$");
                }

                if (!field.addShip(orient.equals("в"), lenShip, new Integer(cell.substring(1)) - 1, cell.charAt(0) - 'а')) {
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
