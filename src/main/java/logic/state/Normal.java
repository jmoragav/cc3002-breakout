package logic.state;

import logic.brick.Brick;
/**
     * Implementation of the state Normal of a Brick from the game Breakout
 * @author Joaquin Moraga
 */

public class Normal extends AbstractState {
    private Brick brick;

    public Normal(Brick brick) {
        super(brick);
        this.brick=getBrick();
    }


    @Override
    public String getTexture() {
        if (brick.isMetalBrick()) {
            return "brick_metal.png";
        } else if (brick.isGlassBrick()) {
            return "brick_blue.png";
        } else {
            return "brick_wood.png";
        }
    }

    @Override
    public boolean isNormal(){return  true;}

}



