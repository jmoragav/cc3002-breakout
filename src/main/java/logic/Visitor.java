package logic;

import logic.brick.Brick;
import logic.brick.MetalBrick;

/**
 * Interface that contains the methods to visit the objects in which it was accepted
 *
 * @author Joaquin Moraga
 */
public interface Visitor {

    void VisitMetalBrick(MetalBrick brick);
    void VisitBrickWithPoints(Brick brick);
}
