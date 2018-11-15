package main.java.logic.level;

import main.java.logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;

public class PlayableLevel implements Level {
    private Level next;
    private ArrayList<Brick> Bricks;
    private String name;
    private int score;
    private boolean playable;

    public PlayableLevel(String name, Level next,int score,int numberofbricks,double probofglass,double probofmetal, double probofwood){}
    @Override
    public String getName() {
        return name;
    }

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
        return next;
    }

    @Override
    public boolean isPlayableLevel() {
        return playable;
    }

    @Override
    public boolean hasNextLevel() {
        return next.isPlayableLevel();
    }

    @Override
    public int getPoints() {
        return score;
    }

    @Override
    public void addPlayingLevel(Level level) {
        if(!next.isPlayableLevel()){
            setNextLevel(level);
        }
        else{
            next.addPlayingLevel(level);
        }
    }

    @Override
    public void setNextLevel(Level level) {
        if(hasNextLevel()){
            Level temp= next.getNextLevel();
            next=level;
            next.setNextLevel(temp);
        }
        else{
            next=level;
        }


    }
}
