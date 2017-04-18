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
                System.out.println(Integer.toString(lenShip) + "-палубный корабль:");
                System.out.print("   Первая клетка (формат г4): ");
                String cell = inputCell(scanner);

                String orient = "в";
                if (lenShip > 1) {
                    System.out.print("   Горизонтально - г, вертикально - в: ");
                    orient = scanner.next();

                    if (!orient.matches("^[вг]$")){
                        System.out.println("Некорректное значение!");
                        numberShip--;
                        continue;
                    }
                }

                if (!field.addShip(orient.equals("в"), lenShip, new Integer(cell.substring(1)) - 1, cell.charAt(0) - 'а')) {
                    System.out.println("Недопустимое значение!");
                    numberShip--;
                }

                System.out.println('\n' + field.toString());
            }
        }
    }

    private static String inputCell(Scanner scanner){
        while(true) {
            String cell = scanner.next();

            if (!cell.matches("^[абвгдежзий](10|[1-9])$")){
                System.out.println("Некорректное значение!");
                continue;
            }
            return cell;
        }
    }
}
