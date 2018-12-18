package logic.state;

import logic.brick.Brick;
/**
 * Implementation of the state AlmostBroke of a Brick from the game Breakout
 * @author Joaquin Moraga
 */
public class AlmostBroke extends AbstractState {
    private Brick brick;

    public AlmostBroke(Brick brick){
        super(brick);
        this.brick=getBrick();
    }

    @Override
    public String getTexture() {
       String texture = "";
        if(brick.isMetalBrick()){
            texture= "brick_metal_cracked.png";
        }
        else if(brick.isWoodenBrick()){
            texture= "brick_wood2.png";
        }

        return texture;
    }

    @Override
    public boolean isAlmostBroke() {
        return true;
    }
}
