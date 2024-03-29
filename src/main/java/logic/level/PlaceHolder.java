package logic.level;

import controller.Game;
import logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementation of a Unplayable Level from the game Breakout
 *
 * @author Joaquin Moraga
 */
public class PlaceHolder implements Level {
    private ArrayList<Brick>Bricks;
    private String name;
    private Level next;

    public PlaceHolder(){
        name="";
        next=null;
        Bricks= new ArrayList<>();
    }

    @Override
    public String getName() {
        return name ;}

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
        return this;
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
        return level; }

    @Override
    public void setNextLevel(Level level) {    }


    public void addedToAGame(Game game){
    }
}
