package gui.factory;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.SelectableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import gui.components.BallComponent;
import gui.components.PlayerComponent;
import gui.types.GameTypes;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.brick.Brick;

import logic.visitor.BehaviourSelector;

/**
 * Factory for Interactive Elements in the GUI, here they are created with the components and  boundry boxes if needed
 * @author Joaquin Moraga
 */
public class InteractiveElementsFactory {

    public static Entity newBall(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.03f).friction(1));



        return Entities.builder()
                .at(x ,y)
                .type(GameTypes.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.LIGHTSTEELBLUE))
                .with(physics, new CollidableComponent(true))
                .with(new BallComponent())
                .build();
    }


    public static Entity newPlayer(){
        PhysicsComponent physics= new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().restitution(0f).density(0f).friction(1));

        return Entities.builder().
                type(GameTypes.PLAYER).
                at(FXGL.getAppWidth()/2-50,600).
                viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("bat.png", 100, 102 / 3)).
                with(physics,new CollidableComponent(true)).
                with(new PlayerComponent())
                .build();
    }







    public static Entity newBrick(int x, int y, Brick brick){
        PhysicsComponent physics = new PhysicsComponent();
        BehaviourSelector bh=  new BehaviourSelector(brick);
        physics.setFixtureDef(new FixtureDef().restitution(0f).density(0f).friction(1));

        return Entities.builder().
                at(x,y).
                type(GameTypes.BRICK).
                viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture(brick.getTexture(),50,20)).
                with(physics,new CollidableComponent(true), bh.getComponent()).
                with(new SelectableComponent(true)).
                build();

    }


}
