package logic.level;

import controller.Game;
import logic.Visitor;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import java.util.*;
/**
 * Implementation of a Playable Level from the game Breakout
 * @author Joaquin Moraga
 */
public class PlayableLevel extends Observable implements Level , Observer , Visitor {
    private Level next;
    private ArrayList<Brick> Bricks;
    private String name;
    private int score;
    private boolean playable;
    private int numberofbricks;//GlassBricks + WoodenBricks
    private int Realnumberofbricks;//numberofbricks+MetalBricks
    private int seed;
    private double probG;
    private int currentscore;
    private double probM;


    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        this.playable=true;
        this.name=name;
        this.numberofbricks=numberOfBricks;
        this.probG=probOfGlass;
        this.probM= probOfMetal;
        this.seed=seed;
        this.score=0;
        this.currentscore=0;
        next=new PlaceHolder();
        Bricks= new ArrayList<>();
        Random rand= new Random(seed);
        int metalb=0;
        for(int i=0;i<numberOfBricks;i++){
            double proba=rand.nextDouble();
            if(proba<probG){
                Brick g_brick= new GlassBrick();
                score=score+g_brick.getScore();
                ((GlassBrick) g_brick).addedToLevel(this);
                Bricks.add(g_brick);

            }
            else{
                Brick w_brick= new WoodenBrick();
                score=score+w_brick.getScore();
                ((WoodenBrick) w_brick).addedToLevel(this);
                Bricks.add(w_brick);

            }
        }
        for(int j= 0; j<numberOfBricks;j++){
            double proba_2=rand.nextDouble();
            if(proba_2<probM){
                Brick m_brick= new MetalBrick();
                ((MetalBrick) m_brick).addedToLevel(this);
                Bricks.add(m_brick);
                metalb+=1;
            }
        }
        this.Realnumberofbricks=metalb+numberOfBricks;
    }


        @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfBricks() {
        return Realnumberofbricks;
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
        if (hasNextLevel()) {
            Level temp = next.getNextLevel();
            next = level;
            next.setNextLevel(temp);
        } else {
            next = level;
        }


    }

    @Override
    /**
     * Notifies the observer that a {@link logic.brick.WoodenBrick} or a {@link GlassBrick} broke
     * this will decrease the number of bricks with points
     * @param brick a {@link logic.brick.WoodenBrick} or a {@link GlassBrick} that has been broken
     */
    public void VisitBrickWithPoints(Brick brick) {
        setChanged();
        notifyObservers(brick);
        Realnumberofbricks-=1;
        currentscore= currentscore+ brick.getScore();
        if(currentscore==score){
            endLevel();
        }

    }

    /**
     * Notifies the observer that a {@link logic.brick.MetalBrick} broke
     * this will increase the number of balls in the game
     * @param metalBrick a {@link logic.brick.MetalBrick} that has been broken
     */
    @Override
    public void VisitMetalBrick(MetalBrick metalBrick) {
        Realnumberofbricks-=1;
        setChanged();
        notifyObservers(metalBrick);

    }

    /**
     * End the level and notifies the observer of this
     */

    public void endLevel() {
        setChanged();
        notifyObservers(this);
    }

    public void addedToAGame(Game game){
        addObserver(game);
    }



    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Brick) {
            ((Brick)arg).accept(this);
        }
    }
}