package logic.brick;

import logic.level.Level;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractBrick extends Observable implements Brick {
    public void addedToLevel(Level level){
        addObserver((Observer) level);
    }
    protected int hit(int hp){


        int new_hp= hp-1;

        return new_hp;

    }

    public abstract void Break_aux();

    @Override
    public void Break() {
        Break_aux();
        setChanged();
        notifyObservers(this);
    }
}
