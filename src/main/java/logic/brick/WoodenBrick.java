package logic.brick;

import logic.level.Level;

import java.util.Observable;

public class WoodenBrick extends AbstractBrick {
    private int Hitpoints;
    private int Value;
    private boolean Broken;
    public WoodenBrick(){
        Hitpoints=3;
        Value=200;
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

    public void Break() {
        Broken= true;
        notifyObservers(this);
    }

    @Override
    public void accept(Level level) {
        level.BrickWithPointsBroke(this);
    }

}
