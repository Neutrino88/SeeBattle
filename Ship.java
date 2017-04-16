public class Ship {
    private boolean dead;
    private boolean orientation; // false=hor, true=ver
    private int length;
    private boolean[] parts;

    public Ship(boolean orientation, int length){
        this.dead = false;
        this.length = length;
        this.orientation = orientation;

        this.parts = new boolean[length];
        for (int i = 0; i < length; i++){
            this.parts[i] = false;
        }
    }

    public boolean doShot(int part){
        // if part is uncorrect
        if (part >= length || part < 0){
            return false;
        }
        // update this.parts
        this.parts[part] = true;

        // update this.dead
        boolean dead = this.parts[0];
        for (int i = 1; i < this.length; i++){
            dead = this.parts[i] & dead;
        }
        this.dead = dead;

        return true;
    }

    public boolean isDead(){
        return this.dead;
    }
}
