package logic.brick;

import logic.level.Level;

import java.util.Observable;
import java.util.Observer;

/**
 * Abstract class that contains some methods which facilitate the implementation of
 * the different types of bricks
 *
 * @author Joaquin Moraga
 */

public abstract class AbstractBrick extends Observable implements Brick {

    @Override
    public void addedToLevel(Level level){
        addObserver((Observer) level);
    }

    /**
     * Calculates the damage caused to a {@link Brick}
     * @param hp initial Hitpoints of the {@link Brick}
     * @return new Hitpoints of the {@link Brick}
     */
    protected int hit(int hp){


        int new_hp= hp-1;

        return new_hp;

    }

    @Override
    public void Break() {
        ChangeStatus();
        setChanged();
        notifyObservers(this);
    }
}
