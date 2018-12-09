package gui.components;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;
import logic.visitor.BehaviourSelector;


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
        BehaviourSelector bh = new BehaviourSelector(getBrick());
        int rm= getBrick().remainingHits();
        brick.hit();
        if (rm==1 || isGlassBrick()){

            FXGL.getAudioPlayer().playSound(bh.getSound());
            FXGL.getAudioPlayer().playSound("Oof.wav");

        }
        else if(brick.isAlmostBroke()){
            entity.setView(FXGL.getAssetLoader().loadTexture(brick.getTexture(),50,20));
            FXGL.getAudioPlayer().playSound(bh.getSound());
        }
        else if (brick.isHitted()){
            entity.setView(FXGL.getAssetLoader().loadTexture(brick.getTexture(),50,20));
            FXGL.getAudioPlayer().playSound(bh.getSound());
        }
        else {
        FXGL.getAudioPlayer().playSound(bh.getSound());}


        if(isDestroyed()){
            entity.removeFromWorld();
        }



    }

}
