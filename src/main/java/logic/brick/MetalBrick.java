package logic.brick;


import logic.Visitor;
import logic.level.Level;

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

    public void Break_aux() {
        Broken=true;
    }

    @Override
    public void accept(Visitor o) {
        o.VisitMetalBrick(this);
    }


}
