package logic.state;

import logic.brick.Brick;

public abstract class AbstractState implements State {
    private Brick brick;
    public AbstractState(Brick brick){
        setBrick(brick);
    }

    @Override
    public void setBrick(Brick brick) {
        this.brick = brick;
    }


    @Override
    public abstract String getTexture();
    public boolean isNormal(){return  false;}
    public boolean isHitted(){return false;}
    public boolean isAlmostBroke() { return false;}
    public Brick getBrick(){return brick; }
    @Override
    public boolean isDestroyed() {
        return false;
    }
}
