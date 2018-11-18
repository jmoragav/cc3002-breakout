package logic.brick;

import logic.level.Level;

import java.util.Observable;

public class GlassBrick extends AbstractBrick implements Brick {
    private int Hitpoints;
    private int Value;
    private boolean Broken;

    public GlassBrick(){
        Hitpoints=1;
        Value=50;
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
        assert (isDestroyed());
        return Hitpoints;
    }

    @Override
    public void Break() {
        Broken= true;
        notifyObservers(this);
    }

    @Override
    public void accept(Level level) {
        level.BrickWithPointsBroke(this);
    }


}
