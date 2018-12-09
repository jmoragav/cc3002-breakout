package logic.visitor;

import com.almasb.fxgl.entity.component.Component;
import gui.components.BrickComponent;
import gui.types.GameTypes;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import static gui.types.GameTypes.G_BRICK;
import static gui.types.GameTypes.M_BRICK;
import static gui.types.GameTypes.W_BRICK;

public class BehaviourSelector implements GuiBrickVisitor {

    private Component component;
    private GameTypes type;
    String sound;


    public BehaviourSelector(Brick brick){
        brick.acceptGuiBrickVisitor(this);
        component = new BrickComponent(brick);
    }
    @Override
    public void VisitGlassBrick(GlassBrick glassBrick) {
        sound = "GlassHit.wav";

           type= G_BRICK;
    }

    @Override
    public void VisitMetalBrick(MetalBrick metalBrick) {
        sound = "MetalHit.wav";

        type= M_BRICK;
    }

    @Override
    public void VisitWoodenBrick(WoodenBrick woodenBrick) {
        sound = "WoodenHit.wav";

        type=W_BRICK;
    }


    public GameTypes getType(){return type;}
    public Component getComponent(){return component;}
    public String getSound(){
        return sound;
    }


}
