package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.settings.GameSettings;
import controller.Game;
import facade.HomeworkTwoFacade;
import gui.components.BallComponent;
import gui.components.BrickComponent;
import gui.components.PlayerComponent;
import gui.types.GameTypes;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.brick.Brick;
import logic.level.Level;

import java.util.*;

import static gui.factory.InteractiveElementsFactory.*;
import static gui.factory.NonInteractiveElementsFactory.newBackground;
import static gui.factory.NonInteractiveElementsFactory.newBorderWalls;

/**
 * The GUI for the game Breakdown, it contains methods that show Pop up boxes , read the inputs and handles the physics
 * @author Joaquin Moraga
 */
public class BreakoutApp extends GameApplication implements Observer {
    private HomeworkTwoFacade game;
    private boolean released= false;
    private boolean emptylevel=true;
    private int n_bricks;
    private double prob_g;
    private double prob_m;
    private boolean lock;
    private boolean cheatmode;
    private boolean cheat_message;
    private Text textCHEAT;


    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(700);
        gameSettings.setTitle("Breakout");
        gameSettings.setVersion("0.1");
        lock=false;
        cheatmode=false;
        cheat_message=false;
        Text textCHEAT= new Text();



    }

    @Override
    protected void initGame() {
        Entity bg= newBackground();
        Entity borders= newBorderWalls();
        Entity player = newPlayer();
        Entity ball= newBall(player.getX()+(player.getWidth()/2),player.getY() - player.getHeight()/2);
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
        Font font = new Font(20);
        Text textBalls = new Text();
        Text textScore = new Text();
        Text textLabelBalls = new Text(0,getHeight()-40,"Balls:");
        Text textLabelScore = new Text(0,getHeight(),"Score:");
        text(textBalls,textScore,textLabelBalls,textLabelScore,font);


        textBalls.textProperty().bind(getGameState().intProperty("balls").asString());
        textScore.textProperty().bind(getGameState().intProperty("score").asString());

        getGameScene().addUINode(textBalls);
        getGameScene().addUINode(textScore);
        getGameScene().addUINode(textLabelBalls);
        getGameScene().addUINode(textLabelScore);



    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(GameTypes.BALL, GameTypes.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {

                            ball.removeFromWorld();
                            game.dropBall();
                            updateVariables();
                            released=false;
                            if(game.getBallsLeft()!=0){
                                Entity player= getGameWorld().getSingleton(GameTypes.PLAYER).get();
                                Entity new_ball= newBall(player.getX()+(player.getWidth()/2),player.getY() - player.getHeight()/2);
                                getGameWorld().addEntity(new_ball);
                                lock=false;
                                updateVariables();

                            }
                            else{
                                showGameOver(false);
                            }


                        }
                    }
                }
        );

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameTypes.BALL, GameTypes.BRICK) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity brick, HitBox boxBall, HitBox boxBrick) {

                brick.getComponent(BrickComponent.class).onHit();

                if(brick.getComponent(BrickComponent.class).isDestroyed()){
                    explosion(brick.getPosition());
                }

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
                        && !lock && !emptylevel)
                {
                    released=true;
                    lock=true;
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
            protected void onActionBegin(){
                emptylevel=false;
                addLevel();
                updateVariables();

            }
        },KeyCode.N);

        input.addAction(new UserAction("Enable Level values editor") {
            @Override
            protected void onActionBegin(){showLevelMenu();}
        },KeyCode.M);
        input.addAction(new UserAction("Show tutorial") {
            @Override
            protected void onActionBegin(){showTutorialMessage();}
        },KeyCode.T);

        input.addAction(new UserAction("Toggle CHEAT MODE") {
            @Override
            protected void onActionBegin(){

                if(cheatmode){
                    getGameScene().removeUINode(textCHEAT);
                }
                else{
                    showCHEATMODE();
                }
                if(!cheat_message){
                    showCheatModeMessage();
                    cheat_message=true;
                }

                cheatmode=!cheatmode;

            }
        },KeyCode.Y);

        input.addAction(new UserAction("CHEAT MODE: CLICK ON BRICK") {
            @Override
            protected void onActionBegin(){
                if(cheatmode){
                Entity brick=getGameWorld().getSelectedEntity().get();
                brick.getComponent(BrickComponent.class).onHit();

                if(brick.getComponent(BrickComponent.class).isDestroyed()){
                    explosion(brick.getPosition());
                }

                updateVariables();}


            }
        },MouseButton.PRIMARY);
        input.addAction(new UserAction("CHEAT MODE: SPAWN BALL") {
            @Override
            protected void onActionBegin(){
                if(cheatmode){
                 getGameWorld().getSingleton(GameTypes.BALL).get().removeFromWorld();
                 Entity player = getGameWorld().getSingleton(GameTypes.PLAYER).get();
                 Entity new_ball= newBall(player.getX()+(player.getWidth()/2),player.getY() - player.getHeight()/2);
                 getGameWorld().addEntity(new_ball);
                 lock=false;
                 released=false;
                }


            }
        },MouseButton.SECONDARY);


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
            clearElements();
            showLevel();
        }

    }
    private BallComponent getBallControl() {
        return getGameWorld().getSingleton(GameTypes.BALL).get().getComponent(BallComponent.class);
    }
    private PlayerComponent getPlayerControl() {
        return getGameWorld().getSingleton(GameTypes.PLAYER).get().getComponent(PlayerComponent.class);
    }






    private void clearElements() {

        getGameWorld().getEntitiesByType(GameTypes.BRICK).forEach(e ->{e.removeFromWorld();});
        getGameWorld().getEntitiesByType(GameTypes.BALL).forEach(e ->{e.removeFromWorld();});
        Entity player = getGameWorld().getSingleton(GameTypes.PLAYER).get();
        Entity new_ball= newBall(player.getX()+(player.getWidth()/2),player.getY() - player.getHeight()/2);
        getGameWorld().addEntity(new_ball);
        lock=false;
        released=false;


    }
    private void showGameOver(boolean state){
        if(!state){
        getDisplay().showConfirmationBox("You lost all your balls!!! , Play Again?", yes -> {
            if (yes) {
                resetGame();
            } else {
                exit();
            }
        });}
        else{
            getDisplay().showConfirmationBox("You Won!!! , Play Again?", yes -> {
                if (yes) {
                    resetGame();
                } else {
                    exit();
                }
            });

        }

    }

    private void showLevelMenu(){


        getDisplay().showInputBox("Ingrese la probabilidad de MetalBricks"+'\n'+"(Recommended 0.1 or 0.2)",num ->{
                double realnum= Double.parseDouble(num);
                prob_m=realnum;
            }
        );
        getDisplay().showInputBox("Ingrese la probabilidad de GlassBricks"+'\n'+"(Recommended 0.6 or 0.7)",num ->{
                    double realnum= Double.parseDouble(num);
                    prob_g=realnum;
                }
        );
        getDisplay().showInputBox("Ingrese la cantidad de ladrillos",num ->{
           int realnum= Integer.parseInt(num);
           if(realnum>100){//Es injugable con un numero mayor a 100
               realnum=100;
           }
           n_bricks=realnum;

        }
        );


    }

    private void showInitMessage(){
        getDisplay().showMessageBox("Bienvenido a Breakout"+'\n'+"A continuación ingrese los valores para configuarar los niveles");
    }
    private void showTutorialMessage(){
        getDisplay().showMessageBox("TUTORIAL" +'\n'+"Use A key to move towards left"+'\n'+"Use D key to move towards right"+
                '\n'+"Use Space key to release the ball"+'\n'+"Use N key to add a new level"+'\n'+"Use M key to edit the level values"+
                '\n'+"Use Y key to activate CHEAT MODE"+" "+"(ONLY FOR TESTING)"+
                '\n'+"Use T key to show the tutorial once again");
    }
    private void showCheatModeMessage(){
        getDisplay().showMessageBox("CHEAT MODE activated, click on the bricks to hit them."+'\n'+"But be careful! if you hit the background you will cause a fatal error");
    }


    private void initBGM() {
        getAudioPlayer().loopBGM("BGM01.wav");
    }

    private void updateVariables() {
        getGameState().intProperty("balls").setValue(game.getBallsLeft());
        getGameState().intProperty("score").setValue(game.getCurrentPoints());

    }
    private void resetGame(){
        getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
        startNewGame();
        game.resetGame();
        lock=false;
        emptylevel=true;
        cheatmode=false;
        cheat_message=false;

    }
    private  void explosion(Point2D pos){

        Entity explosion = new Entity();
        explosion.setPosition(pos);
        ParticleEmitter emitter = ParticleEmitters.newExplosionEmitter(300);

        ParticleComponent component = new ParticleComponent(emitter);
        component.setOnFinished(explosion::removeFromWorld);
        explosion.addComponent(component);
        getGameWorld().addEntity(explosion);
    }

    private void text(Text t1, Text t2, Text Title1, Text Title2, Font font){
        t1.setFont(font);
        t2.setFont(font);
        Title1.setFont(font);
        Title2.setFont(font);
        Title1.setFill(Color.GREEN);
        Title2.setFill(Color.GREEN);
        t1.setFill(Color.GREEN);
        t2.setFill(Color.GREEN);
        t1.setTranslateX(50);
        t2.setTranslateX(60);
        t1.setTranslateY(Title1.getY());
        t2.setTranslateY(Title2.getY());


    }
    private void showCHEATMODE(){


            Font font = new Font(20);
            textCHEAT =new Text("CHEAT MODE ON");
            textCHEAT.setTranslateX(getWidth()-2*textCHEAT.getLayoutBounds().getWidth());
            textCHEAT.setTranslateY(getHeight());
            textCHEAT.setFont(font);
            textCHEAT.setFill(Color.RED);
            getGameScene().addUINode(textCHEAT);


    }
}



