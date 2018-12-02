package gui.factory;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import gui.types.GameTypes;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NonInteractiveElementsFactory {

    public static Entity newBackground(){
        return Entities.builder().viewFromNode(new Rectangle(600,600, Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND).build();
    }

    public static Entity newBorderWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(GameTypes.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }


}
