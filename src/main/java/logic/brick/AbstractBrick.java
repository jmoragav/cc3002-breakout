package logic.brick;

import logic.level.Level;
import logic.state.*;
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

    protected State state;

    public AbstractBrick(int hp ,int value) {
        Hitpoints=hp;
        Value=value;

        setState(new Normal(this));
    }

    @Override
    public void addedToLevel(Level level){
        addObserver((Observer) level);
    }


    @Override
    public void Break() {
        setState(new Destroyed(this));
        setChanged();
        notifyObservers(this);
    }

    @Override
    public void hit() {
        if (Hitpoints != 0) {
            Hitpoints =Hitpoints-1;
            if((Hitpoints==2 && isWoodenBrick()) || (Hitpoints==4 && isMetalBrick())){
                setState(new AlmostBroke(this));
            }
            if(Hitpoints==8 && isMetalBrick()){
                setState(new Hitted(this));
            }
            if(Hitpoints==0){
                Break();
            }
        }
    }

    @Override
    public boolean isDestroyed() {
        return state.isDestroyed();
    }

    @Override
    public int getScore() {
        return Value;
    }

    @Override
    public int remainingHits() {
        return Hitpoints;
    }

    public void setState(State state){
        this.state=state;
        this.state.setBrick(this);
    }


    @Override
    public boolean isAlmostBroke(){
       return state.isAlmostBroke();
    }

    @Override
    public boolean isHitted() {
        return state.isHitted();
    }

    @Override
    public boolean isNormal() {
        return state.isNormal();
    }

    @Override
    public String getTexture() {
        return state.getTexture();
    }
}
