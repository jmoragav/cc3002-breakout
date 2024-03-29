package logic.brick;

import logic.visitor.BrickVisitor;

/**
 * Implementation of a GlassBrick from the game Breakout
 * @author Joaquin Moraga
 */
public class GlassBrick extends AbstractBrick  {

    public GlassBrick(){
        super(1,50);
    }


    @Override
    public boolean isMetalBrick() {
        return false;
    }

    @Override
    public boolean isWoodenBrick() {
        return false;
    }

    @Override
    public boolean isGlassBrick() {
        return true;
    }


    @Override
    public void acceptBrickVisitor(BrickVisitor o) {
        o.VisitBrickWithPoints(this);
    }
}
