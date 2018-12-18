package logic.state;

import logic.brick.Brick;
/**
 * Interface that represents a State in a brick object

 * @author Joaquin Moraga
 */

public interface State {
    /**
     * Set the brick that will be affected by the statee
     * @param brick that will have a state
     */
    void setBrick(Brick brick);

    /**
     * Get the texture of the brick depending on the state
     * @return the name of the brick texture
     */
    String getTexture();
    /**
     * Gets whether the state is Normal or not
     * @return true if is Normal, false otherwise
     */
    boolean isNormal();
    /**
     * Gets whether the state is Hitted or not
     * @return true if is Hitted, false otherwise
     */
    boolean isHitted();
    /**
     * Gets whether the state is AlmostBroke or not
     * @return true if is AlmostBroke, false otherwise
     */
    boolean isAlmostBroke();
    /**
     * Gets whether the state is Destroyed or not
     * @return true if is Destroyed, false otherwise
     */
    boolean isDestroyed();

}
