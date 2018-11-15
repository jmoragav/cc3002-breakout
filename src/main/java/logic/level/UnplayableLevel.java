package main.java.logic.level;

import main.java.logic.brick.Brick;

import java.util.List;

public class UnplayableLevel implements Level {
     public UnplayableLevel (){}
    @Override
    public String getName() {
        return "Placeholder";
    }

    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    @Override
    public List<Brick> getBricks() {
        return null;
    }

    @Override
    public Level getNextLevel() {
        return new UnplayableLevel();
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
    public void addPlayingLevel(Level level) {

    }

    @Override
    public void setNextLevel(Level level) {

    }
}
