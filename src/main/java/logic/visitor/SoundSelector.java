package logic.visitor;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

public class SoundSelector implements GuiBrickVisitor {
    String sound;

    public SoundSelector(Brick brick){
        brick.acceptGuiBrickVisitor(this);
    }
    @Override
    public void VisitGlassBrick(GlassBrick glassBrick) {
        sound = "GlassHit.wav";

    }

    @Override
    public void VisitMetalBrick(MetalBrick metalBrick) {
        sound = "MetalHit.wav";

    }

    @Override
    public void VisitWoodenBrick(WoodenBrick woodenBrick) {
        sound = "WoodenHit.wav";
    }
    public String getSound(){
        return sound;
    }
}
