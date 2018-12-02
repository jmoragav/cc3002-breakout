package logic.brick;


import logic.Visitor;
/**
 * Implementation of a MetalBrick from the game Breakout
 * @author Joaquin Moraga
 */
public class MetalBrick extends AbstractBrick {
    private int Hitpoints;
    private int Value;
    private boolean Broken;

    public MetalBrick(){
        Hitpoints=10;
        Value=0;
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

    public void ChangeStatus() {
        Broken=true;
    }

    @Override
    public void accept(Visitor o) {
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


}
