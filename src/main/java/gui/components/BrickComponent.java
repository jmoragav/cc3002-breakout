package gui.components;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;
import logic.visitor.SoundSelector;


public class BrickComponent extends Component {
    private Brick brick;

    public BrickComponent(Brick brick){
        this.brick=brick;
    }
    public void hit(){
        brick.hit();
    }
    public boolean isWoodenBrick(){
        return brick.isWoodenBrick();
    }
    public boolean isMetalBrick(){
        return brick.isMetalBrick();
    }
    public boolean isGlassBrick(){
        return brick.isGlassBrick();
    }
    public boolean isDestroyed(){
        return brick.isDestroyed();
    }
    public int remainingHits(){return  brick.remainingHits();}
    public Brick getBrick(){
        return brick;
    }

    public void onHit() {
        SoundSelector ss= new SoundSelector(getBrick());
        int rm= getBrick().remainingHits();
        brick.hit();
        if (rm==1){
            FXGL.getAudioPlayer().playSound(ss.getSound());
            FXGL.getAudioPlayer().playSound("Oof.wav");
        }
        else{
        FXGL.getAudioPlayer().playSound(ss.getSound());}
        if(isDestroyed()){
            entity.removeFromWorld();
        }



    }
}
