package logic.state;

import logic.brick.Brick;

public class Destroyed extends AbstractState {
    public Destroyed(Brick brick) {
        super(brick);
    }

    @Override
    public String getTexture() {
       return "";
    }

    @Override
    public boolean isDestroyed() {
        return true;
    }
}
