package main.java.logic.brick;



public class MetalBrick extends AbstractBrick {
    private int Hitpoints;
    private int Value;
    private boolean Alive;

    public MetalBrick(){
        Hitpoints=10;
        Value=0;
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
