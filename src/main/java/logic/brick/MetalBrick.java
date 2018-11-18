package logic.brick;


import logic.level.Level;

public class MetalBrick extends AbstractBrick {
    private int Hitpoints;
    private int Value;
    private boolean Broken;

    public MetalBrick(){
        Hitpoints=10;
        Value=0;
        Broken=false;
    }
    @Override
    public void hit() {
    hit(Hitpoints);

    }

    @Override
    public boolean isDestroyed() {
        return Broken;
    }

    @Override
    public int getScore() {
        return Value;
    }

    @Override
    public int remainingHits() {
        return Hitpoints;
    }

    @Override
    public void Break() {
        Broken= true;
        notifyObservers(this);
    }

    @Override
    public void accept(Level level) {
        level.MetalBrickBroke(this);
    }
}
