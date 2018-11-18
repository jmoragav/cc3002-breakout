package logic.level;

import logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;

public class PlaceHolder implements Level {
    private Level next;
    private ArrayList<Brick>Bricks;
    @Override
    public String getName() {
        return "" ;}

    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    @Override
    public List<Brick> getBricks() {
        return Bricks;
    }

    @Override
    public Level getNextLevel() {
        return new PlaceHolder();
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel() {
        return false;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        if (!next.isPlayableLevel()) {
            setNextLevel(level);
        } else {
            next.addPlayingLevel(level);
        }
        return this;

    }

    @Override
    public void setNextLevel(Level level) {
        next=level;

    }

    @Override
    public void BrickWithPointsBroke(Brick brick) {

    }

    @Override
    public void MetalBrickBroke(Brick metalBrick) {

    }
}
