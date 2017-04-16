public class Cell {
    private boolean status;
    private Ship ship;

    public Cell(Ship ship){
        this.ship = ship;
        this.status = false;
    }

    public boolean doShot(){

        return true;
    }
}
