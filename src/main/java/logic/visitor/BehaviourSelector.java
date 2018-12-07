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
    private Shape shape;
    private Component component;
    private GameTypes type;
    String sound;
    String texture;

    public BehaviourSelector(Brick brick){
        brick.acceptGuiBrickVisitor(this);
        component = new BrickComponent(brick);
    }
    @Override
    public void VisitGlassBrick(GlassBrick glassBrick) {
        shape = new Rectangle(50,20, Color.ALICEBLUE);
           type= G_BRICK;
    }

    @Override
    public void VisitMetalBrick(MetalBrick metalBrick) {
        shape = new Rectangle(50,20, Color.DARKGRAY);
        type= M_BRICK;
    }

    @Override
    public void VisitWoodenBrick(WoodenBrick woodenBrick) {
        shape = new Rectangle(50,20, Color.SADDLEBROWN);
        type=W_BRICK;
    }

    public Shape getShape(){
        return shape;
    }
    public GameTypes getType(){return type;}
    public Component getComponent(){return component;}
}
