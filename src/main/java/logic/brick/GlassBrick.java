package main.java.logic.brick;

public class GlassBrick extends AbstractBrick {
    private int Hitpoints;
    private int Value;
    private boolean Alive;

    public GlassBrick(){
        Hitpoints=1;
        Value=50;
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
        assert (isDestroyed());
        return Hitpoints;
    }
}
