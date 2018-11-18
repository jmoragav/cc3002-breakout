package logic.brick;

import logic.level.Level;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractBrick extends Observable implements Brick {
    public void addedToLevel(Level level){
        addObserver((Observer) level);
    }
    protected void hit(int hp){
        int new_hp= hp-1;
        if(new_hp==0) Break();

    }
}
