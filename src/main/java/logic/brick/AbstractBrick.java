package logic.brick;

import logic.level.Level;
import logic.visitor.BrickVisitor;

import java.util.Observable;
import java.util.Observer;

/**
 * Abstract class that contains some methods which facilitate the implementation of
 * the different types of bricks
 *
 * @author Joaquin Moraga
 */

public abstract class AbstractBrick extends Observable implements Brick {

    protected int Hitpoints;
    protected int Value;
    protected boolean Broken;

    public AbstractBrick(int hp ,int value) {
        Hitpoints=hp;
        Value=value;
        Broken=false;
    }

    @Override
    public void addedToLevel(Level level){
        addObserver((Observer) level);
    }


    @Override
    public void Break() {
        ChangeStatus();
        setChanged();
        notifyObservers(this);
    }

    @Override
    public void hit() {
        if (Hitpoints != 0) {
            Hitpoints =Hitpoints-1;
            if(Hitpoints==0){
                Break();
            }
        }
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
    public void ChangeStatus() {
        Broken=true;
    }
}
