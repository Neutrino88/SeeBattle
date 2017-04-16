import java.util.Scanner;

public class Game {
    static int[] countOfShips = {4, 3, 2, 1};

    public static void main(String[] argv){
        Field field = new Field(10);
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < countOfShips.length; i++){
            for (int j = 0; j < countOfShips[i]; j++){
                System.out.println(Integer.toString(i+1) + "-палубный корабль:");
                System.out.print("   Первая клетка (формат а4): ");
                String cell = scanner.next();
                System.out.print("   Горизонтально - г, вертикально - в: ");
                String orient = scanner.next();

                field.addShip(orient == "в", i+1, cell.charAt(0) - 'а', new Integer(cell.substring(1)) - 1);

                System.out.println('\n' + field.toString());
            }
        }


    }
}
