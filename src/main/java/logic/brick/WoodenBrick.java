package logic.brick;

import logic.visitor.BrickVisitor;
import logic.visitor.GuiBrickVisitor;

/**
 * Implementation of a WoodenBrick from the game Breakout
 * @author Joaquin Moraga
 */
public class WoodenBrick extends AbstractBrick {
    public WoodenBrick(){
        super(3,200);
    }

    @Override
    public void acceptGuiBrickVisitor(GuiBrickVisitor o) {
        o.VisitWoodenBrick(this);
    }

    @Override
    public boolean isMetalBrick() {
        return false;
    }

    @Override
    public boolean isWoodenBrick() {
        return true;
    }

    @Override
    public boolean isGlassBrick() {
        return false;
    }


    @Override
    public void acceptBrickVisitor(BrickVisitor o) {
        o.VisitBrickWithPoints(this);
    }

}
