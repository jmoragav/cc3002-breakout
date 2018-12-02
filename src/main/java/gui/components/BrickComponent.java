package gui.components;

import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;



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
    public Brick getBrick(){
        return brick;
    }
}
