package controller;

import logic.Visitor;
import logic.brick.Brick;
import logic.brick.MetalBrick;
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
public class Game implements Observer , Visitor {
    private int Points;
    private int balls;
    private Level current;

    private boolean finished;
    private boolean zero_balls;
    public Game(int balls) {
        Points= 0;
        finished=false;
        current = new PlaceHolder();
        this.balls=balls;
        if(balls==0){
            zero_balls=true;
        }



    }

    /**
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return finished;
    }

    /**
     * Gets the {@link List} of {@link Brick}s in the current level.
     *
     * @return the bricks in the level
     */
    public List<Brick> getBricks() {
       return current.getBricks();
        
    }
    /**
     * Check if the next {@link Level} of the current {@link Level} is playable or not.
     *
     * @return true if the next level is playable, false otherwise
     */
    public boolean hasNextLevel() {
        return current.hasNextLevel();
    }

    /**
     * Pass to the next level of the current {@link Level}. Ignores all conditions and skip to the next level.
     * if the current level hasn't a next level the game is finished
     */
    public void goNextLevel() {
        assert (!isGameOver());
        if(current.hasNextLevel()){
       Level nextLevel= current.getNextLevel();
       setCurrentLevel(nextLevel);}
       else{
           finished=true;
           setCurrentLevel(new PlaceHolder());
       }
    }


    /**
     * Gets whether the current {@link Level} is playable or not.
     *
     * @return true if the current level is playable, false otherwise
     */

    public boolean hasCurrentLevel() {
        return current.isPlayableLevel();
    }

    /**
     * Gets the current {@link Level} name
     * @return the level name
     */
    public String getLevelName() {
        return current.getName();
    }

    /**
     * Gets the current {@link Level}
     * @return the current level
     */
    public Level getCurrentLevel() {
        return current;
    }

    /**
     * Sets a {@link Level} as the current level
     * @param level the level who will be the new current level
     */
    public void setCurrentLevel(Level level) {
        current=level;
        current.addedToAGame(this);
    }

    /**
     * Adds a level to the list of levels in the current level. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */

    public void addPlayingLevel(Level level) {
        assert(!zero_balls);
        current.addPlayingLevel(level);
    }
    /**
     * Gets the number of points required to pass to the next level. Gets the points obtainable in the current {@link Level}.
     *
     * @return the number of points in the current level
     */

    public int getLevelPoints() {
        return current.getPoints();
    }
    /**
     * Gets the accumulated points through all levels and current {@link Level}.
     *
     * @return the cumulative points
     */

    public int getPoints() {
        return Points;
    }
    /**
     * Gets the number of balls available
     * @return the number of balls
     */

    public int getBalls() {
        return balls;
    }

    /**
     * Decrease the number of balls in the game
     * @return the new number of balls
     */
    public int dropBall() {
        balls=balls-1;
        if(balls<=0) {
            zero_balls=true ;
            balls=0;
        }
        return getBalls();
    }
    /**
     * Gets the number of {@link Brick} in the current level.
     *
     * @return the number of Bricks in the current level
     */

    public int numberOfBricks() {
        return current.getNumberOfBricks();
    }

    /**
     * Checks whether the game is over or not. A game is over when the number of available balls are 0 or the player won the game.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        if(zero_balls){
            return true; }
        else if(winner()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    /**
     * This method will trigger when an Observable object notifies the game
     * If the object is a brick the game will visit it
     * In the case that the object is a level , the game will pass to the next level
     */
    public void update(Observable o, Object arg) {
        if(arg instanceof Brick){
           ((Brick) arg).accept(this);
        }
        if(arg instanceof Level){
            goNextLevel();
        }

    }
    /**
     * Creates a new level with the given parameters.
     *
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param probOfMetal    the probability of a {@link logic.brick.MetalBrick}
     * @param seed           the seed for the random number generator
     * @return a new level determined by the parameters
     * @see Level
     */

    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        return new PlayableLevel(name,numberOfBricks,probOfGlass,probOfMetal,seed);
    }
    /**
     * Creates a new level with the given parameters with no metal bricks.
     *
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param seed           the seed for the random number generator
     * @return a new level determined by the parameters
     * @see Level
     */

    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        return newLevelWithBricksFull(name,numberOfBricks,probOfGlass,0,seed);

    }

    /**
     * The game visits a {@link MetalBrick} when it breaks
     * that means that the game will increase the number of available balls
     * @param brick the brick that broke
     */

    @Override
    public void VisitMetalBrick(MetalBrick brick) {
        balls=balls+1;
    }
    /**
     * The game visits a {@link logic.brick.WoodenBrick} or a {@link logic.brick.GlassBrick} when it breaks
     * that means that the game will increase the number of current points depending
     * in the value of the brick
     * @param brick the brick that broke
     */

    @Override
    public void VisitBrickWithPoints(Brick brick) {
        int score=brick.getScore();
        Points=Points+score;
    }
}
