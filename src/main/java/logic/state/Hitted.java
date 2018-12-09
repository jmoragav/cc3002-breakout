package logic.state;

import logic.brick.Brick;

public class Hitted extends AbstractState{


    public Hitted(Brick brick) {
        super(brick);
    }

    @Override
    public String getTexture() {
        return "brick_metal_hitted.png";
    }

    @Override
    public boolean isHitted(){
        return true;
    }
}
