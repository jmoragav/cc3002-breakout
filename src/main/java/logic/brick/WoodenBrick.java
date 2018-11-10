package main.java.logic.brick;

public class WoodenBrick extends AbstractBrick {
    private int Hitpoints;
    private int Value;
    private boolean Alive;
    public WoodenBrick(){
        Hitpoints=3;
        Value=200;
        Alive=true;
    }
    @Override
    public void hit() {
        hit(Hitpoints,Alive);
    }

    @Override
    public boolean isDestroyed() {
        return Alive;
    }

    @Override
    public int getScore() {
        return Value;
    }

    @Override
    public int remainingHits() {
        return Hitpoints;
    }
}
