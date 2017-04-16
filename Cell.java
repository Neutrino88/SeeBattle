public class Cell {
    private boolean dead;
    private Ship ship;

    public Cell(Ship ship){
        this.ship = ship;
        this.dead = false;
    }

    public Cell(){
        this.ship = null;
        this.dead = false;
    }

    public Ship getShip(){
        return this.ship;
    }
    public void setShip(Ship ship){
        this.ship = ship;
    }
    public void unsetShip(){
        this.ship = null;
    }

    public boolean isDead(){
        return this.dead;
    }

    public boolean doShot(){

        return true;
    }
}
