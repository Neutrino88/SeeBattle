import java.util.Scanner;

class People extends Player {
    Scanner scanner;

    People(Field field, Field enemyField){
        super(field, enemyField);
        this.scanner = new Scanner(System.in);
    }

    void addShips(int[] countOfShips){
//        field.addShip(false, 2, 1, 1);
  //      field.addShip(false, 4, 4, 4);

        for (int lenShip = 1; lenShip <= countOfShips.length; lenShip++){
            for (int numberShip = 0; numberShip < countOfShips[lenShip-1]; numberShip++){
                String cell;

                System.out.print(Integer.toString(lenShip) + "-палубный корабль:\n   Первая клетка (формат г4): ");
                while (true) {
                    cell = inputValue("", "^[" + Field.cols.substring(0, field.getSize()) + "](\\d?\\d)$");

                    if (new Integer(cell.substring(1)) - 1 < field.getSize()){
                        break;
                    }

                    System.out.print("Некорректное значение! Попробуйте еще раз: ");
                }

                String orient = "в";
                if (lenShip > 1) {
                    orient = inputValue("   Горизонтально - г, вертикально - в: ", "^[вг]$");
                }

                if (!field.addShip(orient.equals("в"), lenShip, Field.getRowByCell(cell), Field.getColByCell(cell))) {
                    System.out.println("   Установите корабль в другое место!");
                    numberShip--;
                    continue;
                }

                System.out.println('\n' + field.toString());
            }
        }
   }

    String inputValue(String title, String regexp){
        System.out.print(title);

        while(true) {
            String value = scanner.next();

            if (!value.matches(regexp)){
                System.out.print("   Некорректное значение! Попробуйте еще раз: ");
                continue;
            }
            return value;
        }
    }

    String doShot(){
        String cell;

        System.out.print("Ваш ход: ");
        while (true) {
            cell = inputValue("", "^[" + Field.cols.substring(0, field.getSize()) + "](\\d?\\d)$");

            if (new Integer(cell.substring(1)) - 1 < field.getSize()){
                break;
            }

            System.out.print("Некорректное значение! Попробуйте еще раз: ");
        }

        this.enemyField.getShot(Field.getRowByCell(cell), Field.getColByCell(cell));
        return cell;
    }
}
