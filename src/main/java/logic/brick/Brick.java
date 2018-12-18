package logic.brick;

import logic.state.State;
import logic.visitor.BrickVisitor;
import logic.level.Level;


/**
 * Interface that represents a brick object.
 * <p>
 * All bricks should implement this interface.
 *
 * @author Juan-Pablo Silva
 */
public interface Brick {
    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    void hit();

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    boolean isDestroyed();

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    int getScore();

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    int remainingHits();

    /**
     * Adds an observer level to the set of observers for this object
     * @param level the level that will be observing this brick
     */
    void addedToLevel(Level level);


    /**
     * Defines that a brick has been broken
     * this will trigger events
     */

    void Break();

    /**
     * Accepts a visitor type object that will do an action depending on the type of brick
     * @param o visitor who will perform an action
     */
    void acceptBrickVisitor(BrickVisitor o);

    /**
     * Gets whether the brick object is a MetalBrick or not
     * @return true if is a MetalBrick , false otherwise
     */

    boolean isMetalBrick();
    /**
     * Gets whether the brick object is a WoodenBrick or not
     * @return true if is a WoodenBrick , false otherwise
     */

    boolean isWoodenBrick();

    /**
     * Gets whether the brick object is a GlassBrick or not
     * @return true if is a GlassBrick , false otherwise
     */

    boolean isGlassBrick();

    /**
     * Set the current state on the brick
     * @param state
     */

    void setState(State state);

    /**
     * Gets whether the state on the brick object is Normal or not
     * @return true if is Normal, false otherwise
     */

    boolean isNormal();
    /**
     * Gets whether the state on the brick object is Hitted or not
     * @return true if is Hitted, false otherwise
     */
    boolean isHitted();
    /**
     * Gets whether the state on the brick object is AlmostBroke or not
     * @return true if is AlmostBroke, false otherwise
     */
    boolean isAlmostBroke();

    /**
     * Get the name of the brick texture
     * @return the name of the brick texture saved in assets
     */
    String getTexture();
}
