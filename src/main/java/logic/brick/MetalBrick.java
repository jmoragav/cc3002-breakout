package logic.brick;


import logic.visitor.BrickVisitor;
import logic.visitor.GuiBrickVisitor;

/**
 * Implementation of a MetalBrick from the game Breakout
 * @author Joaquin Moraga
 */
public class MetalBrick extends AbstractBrick {
    public MetalBrick(){
        super(10,0);
    }

    @Override
    public void acceptGuiBrickVisitor(GuiBrickVisitor o) {
        o.VisitMetalBrick(this);
    }

    @Override
    public boolean isMetalBrick() {
        return true;
    }

    @Override
    public boolean isWoodenBrick() {
        return false;
    }

    @Override
    public boolean isGlassBrick() {
        return false;
    }


    @Override
    public void acceptBrickVisitor(BrickVisitor o) {
        o.VisitMetalBrick(this);
    }

}
