package logic;

import logic.brick.Brick;

public interface Visitor {
    void VisitMetalBrick(Brick brick);
    void VisitBrickWithPoints(Brick brick);
}
