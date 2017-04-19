import java.util.Scanner;

class People extends Player {
    People(Field field, Field enemyField){
        super(field, enemyField);
    }

    void addShips(int[] countOfShips){
        Scanner scanner = new Scanner(System.in);

        for (int lenShip = 1; lenShip <= countOfShips.length; lenShip++){
            for (int numberShip = 0; numberShip < countOfShips[lenShip-1]; numberShip++){
                String cell;

                System.out.print(Integer.toString(lenShip) + "-палубный корабль:\n   Первая клетка (формат г4): ");
                while (true) {
                    cell = inputValue(scanner, "", "^[" + Field.rows.substring(0, field.getSize()) + "](\\d?\\d)$");

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

        scanner.close();
    }

    static String inputValue(Scanner scanner, String title, String regexp){
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

    String getFieldString(){
        return field.toString();
    }

    String doShot(){
        return "a1";
    }
}
