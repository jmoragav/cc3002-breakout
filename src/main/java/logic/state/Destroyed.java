package logic.state;

import logic.brick.Brick;
/**
 * Implementation of the state Destroyed of a Brick from the game Breakout
 * @author Joaquin Moraga
 */

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
