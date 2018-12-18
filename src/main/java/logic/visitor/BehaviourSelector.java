package logic.visitor;

import com.almasb.fxgl.entity.component.Component;
import gui.components.BrickComponent;

import logic.brick.Brick;

import logic.brick.MetalBrick;

/**
 * BehaviourSector is an object that will determinate the sound of a brick
 * and the component of this using visitor pattern
 * @author Joaquin Moraga
 */
public class BehaviourSelector implements BrickVisitor {

    private Component component;

    String sound;


    public BehaviourSelector(Brick brick){
        brick.acceptBrickVisitor(this);
        component = new BrickComponent(brick);
    }

    /**
     * The game visits a {@link MetalBrick}
     * and assign the sound field to the correspondent sound name
     * @param metalBrick the brick that indicates the sound name
     */
    @Override
    public void VisitMetalBrick(MetalBrick metalBrick) {
        sound = "MetalHit.wav";

    }

    /**
     * The game visits a {@link logic.brick.WoodenBrick} or {@link logic.brick.GlassBrick}
     * and assign the sound field to the correspondent sound name
     * @param brick the brick that indicates the sound name
     */

    @Override
    public void VisitBrickWithPoints(Brick brick) {
        if (brick.isWoodenBrick()){
            sound = "WoodenHit.wav";
        }
        else{
            sound = "GlassHit.wav";
        }
    }


    /**
     * Get the component of a brick for the GUI
     * @return Brick Component
     */
    public Component getComponent(){return component;}

    /**
     * Get the sound name of the brick
     * @return name of the sound file
     */
    public String getSound(){
        return sound;
    }


}
