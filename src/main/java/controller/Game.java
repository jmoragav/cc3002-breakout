package controller;

import logic.brick.Brick;
import logic.level.Level;
import logic.level.PlaceHolder;
import logic.level.PlayableLevel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {
    private int Points;
    private int balls;
    private Level current;// CHEKEAR DESPUES

    private boolean finished;

    public Game(int balls) {
        Points= 0;
        finished=false;
        current = new PlaceHolder();
        this.balls=balls;



    }

    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return finished;
    }

    public List<Brick> getBricks() {
       return current.getBricks();
        
    }

    public boolean hasNextLevel() {
        return current.hasNextLevel();
    }

    public void goNextLevel() {
        if(current.hasNextLevel()){
       Level nextLevel= current.getNextLevel();
       setCurrentLevel(nextLevel);}
       else{
           finished=true;
        }
    }

    public boolean hasCurrentLevel() {
        return current.isPlayableLevel();
    }

    public String getLevelName() {
        return current.getName();
    }

    public Level getCurrentLevel() {
        return current;
    }

    public void setCurrentLevel(Level level) {
        current=level;
    }

    public void addPlayingLevel(Level level) {
        current.addPlayingLevel(level);
    }

    public int getLevelPoints() {
        return current.getPoints();
    }

    public int getPoints() {
        return Points;
    }

    public int getBalls() {
        return balls;
    }

    public int dropBall() {
        balls=balls-1;
        return getBalls();
    }

    public int numberOfBricks() {
        return current.getNumberOfBricks();
    }

    public boolean isGameOver() {
        if(getBalls()==0){
            return true; }
        else if(finished){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Brick){
            balls+=1;
        }
        if(arg instanceof Level){
            goNextLevel();
        }
    }

    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        return new PlayableLevel(name,numberOfBricks,probOfGlass,probOfMetal,seed);
    }

    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        return new PlayableLevel(name,numberOfBricks,probOfGlass,seed);

    }
}
