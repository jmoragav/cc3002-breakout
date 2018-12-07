package logic.visitor;

import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

public interface GuiBrickVisitor {
    void VisitGlassBrick(GlassBrick glassBrick);
    void VisitMetalBrick(MetalBrick metalBrick);
    void VisitWoodenBrick(WoodenBrick woodenBrick);

}
