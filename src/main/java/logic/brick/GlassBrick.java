package logic.brick;

import logic.Visitor;
import logic.level.Level;

public class GlassBrick extends AbstractBrick implements Brick {
    private int Hitpoints;
    private int Value;
    private boolean Broken;

    public GlassBrick(){
        Hitpoints=1;
        Value=50;
        Broken=false;
    }

    @Override
    public void hit() {
        if (Hitpoints != 0) {
            int new_hp = hit(Hitpoints);
            Hitpoints = new_hp;
            if(Hitpoints==0){
                Break();
            }
        }
    }
    @Override
    public boolean isDestroyed() {
        return Broken;
    }

    @Override
    public int getScore() {
        return Value;
    }

    @Override
    public int remainingHits() {
        return Hitpoints;
    }

    @Override
    public void accept(Visitor o) {
        o.VisitBrickWithPoints(this);
    }




    @Override
    public void Break_aux() {
        Broken=true;
    }

}
