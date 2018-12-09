package gui.factory;

import com.almasb.fxgl.app.DSLKt;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import gui.types.GameTypes;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.brick.Brick;
import logic.visitor.BehaviourSelector;

public class NonInteractiveElementsFactory {

    public static Entity newBackground(){
        return Entities.builder().viewFromNode(new Rectangle(700,700, Color.BLACK)).with(new IrremovableComponent())
                .renderLayer(RenderLayer.BACKGROUND).build();
    }

    public static Entity newBorderWalls() {
        Entity walls = Entities.makeScreenBounds(40);
        walls.setType(GameTypes.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }



}
