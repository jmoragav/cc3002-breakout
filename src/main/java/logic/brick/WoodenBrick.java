package logic.brick;

import logic.Visitor;
import logic.level.Level;

/**
 * Implementation of a WoodenBrick from the game Breakout
 * @author Joaquin Moraga
 */
public class WoodenBrick extends AbstractBrick {
    private int Hitpoints;
    private int Value;
    private boolean Broken;
    public WoodenBrick(){
        Hitpoints=3;
        Value=200;
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
    public void ChangeStatus() {
        Broken=true;
    }


}
