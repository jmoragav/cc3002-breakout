package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.util.Consumer;
import controller.Game;
import facade.HomeworkTwoFacade;
import gui.components.BallComponent;
import gui.components.BrickComponent;
import gui.components.PlayerComponent;
import gui.types.GameTypes;
import javafx.animation.PathTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.brick.Brick;
import logic.level.Level;

import java.util.*;

import static gui.factory.InteractiveElementsFactory.newBall;
import static gui.factory.InteractiveElementsFactory.newBrick;
import static gui.factory.InteractiveElementsFactory.newPlayer;
import static gui.factory.NonInteractiveElementsFactory.newBackground;
import static gui.factory.NonInteractiveElementsFactory.newBorderWalls;

public class BreakoutApp extends GameApplication implements Observer {
    private HomeworkTwoFacade game;
    private boolean released= false;
    private boolean testing_mode=false;
    private int n_bricks;
    private double prob_g;
    private double prob_m;



    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(700);
        gameSettings.setTitle("Breakout");
        gameSettings.setVersion("0.1");


    }

    @Override
    protected void initGame() {
        Entity bg= newBackground();
        Entity borders= newBorderWalls();
        Entity player = newPlayer();
        Entity ball= newBall(player.getX(),player.getY() - player.getHeight());
        initBGM();
        game= new HomeworkTwoFacade();

        getGameWorld().addEntities(bg,borders,player,ball);
        game.addedToaGUI(this);

    }

    @Override
    protected void preInit() {

        showTutorialMessage();
        showLevelMenu();
        showInitMessage();
    }

    @Override
    protected void initUI() {
        Font font = new Font(30);
        Text textBalls = new Text();
        Text textScore = new Text();
        textBalls.setFont(font);
        textScore.setFont(font);
        textBalls.setFill(Color.GREEN);
        textScore.setFill(Color.GREEN);
        textBalls.setTranslateX(100);
        textBalls.setTranslateY(550);
        textScore.setTranslateX(450);
        textScore.setTranslateY(550);

        textBalls.textProperty().bind(getGameState().intProperty("balls").asString());
        textScore.textProperty().bind(getGameState().intProperty("score").asString());

        getGameScene().addUINode(textBalls);
        getGameScene().addUINode(textScore);

        updateVariables();


    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(GameTypes.BALL, GameTypes.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            ball.removeComponent(PhysicsComponent.class);
                            ball.removeFromWorld();
                            game.dropBall();
                            updateVariables();
                            released=false;
                            if(game.getBallsLeft()!=0){
                                Entity player= getGameWorld().getSingleton(GameTypes.PLAYER).get();
                                Entity new_ball= newBall(player.getX(),player.getY() - player.getHeight());
                                getGameWorld().addEntity(new_ball);
                                updateVariables();

                            }
                            else{
                                showGameOver(false);
                            }


                        }
                    }
                }
        );

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameTypes.BALL, GameTypes.W_BRICK) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity brick, HitBox boxBall, HitBox boxBrick) {
                brick.getComponent(BrickComponent.class).onHit();
                updateVariables();

            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameTypes.BALL, MouseButton.PRIMARY) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity brick, HitBox boxBall, HitBox boxBrick) {
                if(testing_mode)
                brick.getComponent(BrickComponent.class).onHit();
                updateVariables();

            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameTypes.BALL, GameTypes.M_BRICK) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity brick, HitBox boxBall, HitBox boxBrick) {
                brick.getComponent(BrickComponent.class).onHit();
                updateVariables();
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameTypes.BALL, GameTypes.G_BRICK) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity brick, HitBox boxBall, HitBox boxBrick) {
                brick.getComponent(BrickComponent.class).onHit();
                updateVariables();
            }
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("balls",0);
        vars.put("score",0);
    }

    protected void initInput(){
        Input input = getInput();

        input.addAction(new UserAction("New ball") {
            @Override
            protected void onActionBegin() {
                if (getGameWorld().getEntitiesByType(GameTypes.BALL).size() == 1
                        && !game.isGameOver())
                {
                    released=true;
                    getBallControl().release();
                             }

            }
        }, KeyCode.SPACE);
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                if(released){

                getPlayerControl().left();
            }}
        }, KeyCode.A);

        input.addAction(new UserAction("Move Right") {

            @Override
            protected void onAction() {
                if(released){
                getPlayerControl().right();
            }}
        }, KeyCode.D);

        input.addAction(new UserAction("Add Level") {
            @Override
            protected void onActionBegin(){addLevel();}
        },KeyCode.N);

        input.addAction(new UserAction("Enable Level values editor") {
            @Override
            protected void onActionBegin(){showLevelMenu();}
        },KeyCode.T);

    }


    public static void main(String... args){
        launch(args);
    }

    private void addLevel(){
        Level level= game.newLevelWithBricksFull("Level",n_bricks,prob_g,prob_m,System.currentTimeMillis());

        if(!game.hasCurrentLevel()){
            game.setCurrentLevel(level);
            showLevel();
        }
        else{
            game.addPlayingLevel(level);

        }
    }

    private void showLevel(){
        if(game.hasCurrentLevel()) {
            List<Brick> bricks = game.getCurrentLevel().getBricks();
            Collections.shuffle(bricks);
            int counTo10 = 0;
            int cor_x = 50;
            int cor_y = 50;
            for (Brick b : bricks) {
                if (counTo10 == 10) {
                    counTo10 = 0;
                    cor_x = 50;
                    cor_y = cor_y + 20;
                }
                getGameWorld().addEntity(newBrick(cor_x, cor_y, b));
                counTo10++;
                cor_x = cor_x + 50;
            }
        }
    }



    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Game){
            if(((Game) arg).winner()){
                showGameOver(true);
            }
        }
        else if(arg instanceof Level) {
        clearBricks();
        showLevel();}

    }
    private BallComponent getBallControl() {
        return getGameWorld().getSingleton(GameTypes.BALL).get().getComponent(BallComponent.class);
    }
    private PlayerComponent getPlayerControl() {
        return getGameWorld().getSingleton(GameTypes.PLAYER).get().getComponent(PlayerComponent.class);
    }
    



    private void clearBricks() {
        getGameWorld().getEntitiesByType(GameTypes.M_BRICK).forEach(e ->{e.removeFromWorld();});
    }
    private void showGameOver(boolean state){
        if(!state){
        getDisplay().showConfirmationBox("You lost all your balls!!! , Play Again?", yes -> {
            if (yes) {
                getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
                startNewGame();
                game.resetGame();
            } else {
                exit();
            }
        });}
        else{
            getDisplay().showConfirmationBox("You Won!!! , Play Again?", yes -> {
                if (yes) {
                    getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
                    startNewGame();
                    game.resetGame();
                } else {
                    exit();
                }
            });

        }

    }

    private void showLevelMenu(){


        getDisplay().showInputBox("Ingrese la probabilidad de MetalBricks",num ->{
                double realnum= Double.parseDouble(num);
                prob_m=realnum;
            }
        );
        getDisplay().showInputBox("Ingrese la probabilidad de GlassBricks",num ->{
                    double realnum= Double.parseDouble(num);
                    prob_g=realnum;
                }
        );
        getDisplay().showInputBox("Ingrese la cantidad de ladrillos",num ->{
           int realnum= Integer.parseInt(num);
           n_bricks=realnum;
        }
        );

    }

    private void showInitMessage(){
        getDisplay().showMessageBox("Bienvenido a Breakout"+'\n'+"A continuación ingrese los valores para configuarar los niveles");
    }
    private void showTutorialMessage(){
        getDisplay().showMessageBox("TUTORIAL" +'\n'+"Use A key to move towards left"+'\n'+"Use D key to move towards right"+
                '\n'+"Use Space key to release the ball"+'\n'+"Use N key to add a new level"+'\n'+"Use T key to edit the level values");
    }


    private void initBGM() {
        getAudioPlayer().loopBGM("BGM01.wav");
    }

    private void updateVariables() {
        getGameState().intProperty("balls").setValue(game.getBallsLeft());
        getGameState().intProperty("score").setValue(game.getCurrentPoints());
    }


}



