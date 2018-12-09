package logic.state;

import logic.brick.Brick;

public interface State {
    void setBrick(Brick brick);
    String getTexture();
    boolean isNormal();
    boolean isHitted();
    boolean isAlmostBroke();
    boolean isDestroyed();

}
