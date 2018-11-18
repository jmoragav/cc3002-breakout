package logic.level;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import java.util.*;

public class PlayableLevel extends Observable implements Level , Observer {
    private Level next;
    private ArrayList<Brick> Bricks;
    private String name;
    private int score;
    private boolean playable;
    private int numberofbricks;
    private int seed;
    private double probG;

    private double probM;


    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        this.playable=true;
        this.name=name;
        this.numberofbricks=numberOfBricks;
        this.probG=probOfGlass;
        this.probM= probOfMetal;
        this.seed=seed;
        next=new PlaceHolder();
        Bricks= new ArrayList<>();
        Random rand= new Random(seed);
        for(int i=0;i<numberOfBricks;i++){
            double proba=rand.nextDouble();
            if(proba<probG){
                Brick g_brick= new GlassBrick();
                Bricks.add(g_brick);
            }
            else{
                Brick w_brick= new WoodenBrick();
                Bricks.add(w_brick);
            }
        }
        for(int j= 0; j<numberOfBricks;j++){
            double proba_2=rand.nextDouble();
            if(proba_2<probM){
                Brick m_brick= new MetalBrick();
                Bricks.add(m_brick);
            }
        }

    }
    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, int seed) {
        this(name,numberOfBricks,probOfGlass,0,seed);
    }

        @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfBricks() {
        return numberofbricks;
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
    public void BrickWithPointsBroke(Brick brick) {
        score=score+brick.getScore();
        numberofbricks-=1;
        if(numberofbricks==0){
            endLevel();
        }

    }


    @Override
    public void MetalBrickBroke(Brick metalBrick) {
        notifyObservers(metalBrick);

    }

    private void endLevel() {
        notifyObservers(this);
    }



    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Brick) {
            ((Brick)arg).accept(this);
        }
    }
}