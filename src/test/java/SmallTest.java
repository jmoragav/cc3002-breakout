import controller.Game;
import facade.HomeworkTwoFacade;
import gui.components.BrickComponent;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.Level;
import logic.level.PlaceHolder;
import logic.visitor.BehaviourSelector;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;


public class SmallTest {
    private HomeworkTwoFacade hw2;
    private final int glassScore = 50;
    private final int woodenScore = 200;
    private final int seed = 0;
    private final int initialBalls = 3;

    @Before
    public void setUp() {
        hw2 = new HomeworkTwoFacade();
    }

    @Test
    public void UnplayableLevelTest(){
        Level placeholder= new PlaceHolder();
        placeholder.setNextLevel(new PlaceHolder());
        assertFalse(placeholder.hasNextLevel());
        assertFalse(placeholder.isPlayableLevel());

    }

    @Test
    public void DeadGameTest(){
        Game Deadgame= new Game(0);
        assertFalse(Deadgame.winner());
        assertTrue(Deadgame.isGameOver());
        assertEquals(Deadgame.getBalls(),0);
        assertEquals(Deadgame.dropBall(),0);


    }
    @Test
    public void GameIsOver(){
        hw2.addPlayingLevel(hw2.newLevelWithBricksFull("",20,0.5,0.5,seed));
        hw2.goNextLevel();
        assertTrue(hw2.isGameOver());
    }

    @Test
    public void BrickSmallTest(){
        Brick gbrick,mbrick,wbrick;
        gbrick= new GlassBrick();
        wbrick=new WoodenBrick();
        mbrick= new MetalBrick();
        assertTrue(gbrick.isGlassBrick());
        assertTrue(mbrick.isMetalBrick());
        assertTrue(wbrick.isWoodenBrick());
        assertFalse(wbrick.isMetalBrick());
        assertFalse(wbrick.isGlassBrick());
        assertFalse(gbrick.isMetalBrick());
        assertFalse(gbrick.isWoodenBrick());
        assertFalse(mbrick.isWoodenBrick());
        assertFalse(mbrick.isGlassBrick());

        assertEquals(gbrick.getTexture(),"brick_blue.png");
        assertEquals(wbrick.getTexture(),"brick_wood.png");
        assertEquals(mbrick.getTexture(),"brick_metal.png");


    }

    @Test
    public void StateTest(){
        Brick gbrick,mbrick,wbrick;
        gbrick= new GlassBrick();
        wbrick=new WoodenBrick();
        mbrick= new MetalBrick();
       /* List<Brick>lista= new ArrayList<>();
        lista.add(gbrick);lista.add(wbrick);lista.add(mbrick);*/
        assertTrue(gbrick.isNormal());
        assertTrue(wbrick.isNormal());
        assertTrue(mbrick.isNormal());
        assertFalse(gbrick.isHitted());
        assertFalse(wbrick.isHitted());
        assertFalse(mbrick.isHitted());
        assertFalse(gbrick.isAlmostBroke());
        assertFalse(wbrick.isAlmostBroke());
        assertFalse(mbrick.isAlmostBroke());
        assertFalse(gbrick.isDestroyed());
        assertFalse(mbrick.isDestroyed());
        assertFalse(wbrick.isDestroyed());
        gbrick.hit();
        wbrick.hit();
        mbrick.hit();
        assertTrue(gbrick.isDestroyed());
        assertEquals(gbrick.getTexture(),"");
        assertFalse(gbrick.isNormal());
        assertTrue(wbrick.isAlmostBroke());
        assertFalse(wbrick.isNormal());
        assertFalse(wbrick.isHitted());
        assertEquals(wbrick.getTexture(),"brick_wood2.png");
        wbrick.hit();
        mbrick.hit();
        wbrick.hit();
        mbrick.hit();
        assertTrue(wbrick.isDestroyed());
        assertFalse(wbrick.isNormal());
        assertFalse(wbrick.isHitted());
        assertFalse(wbrick.isAlmostBroke());
        assertEquals(wbrick.getTexture(),"");
        assertTrue(mbrick.isHitted());
        assertFalse(mbrick.isNormal());
        assertFalse(mbrick.isAlmostBroke());
        assertFalse(mbrick.isDestroyed());
        assertEquals(mbrick.getTexture(),"brick_metal_hitted.png");

        repeat(4, () -> mbrick.hit());
        assertTrue(mbrick.isAlmostBroke());
        assertFalse(mbrick.isNormal());
        assertFalse(mbrick.isHitted());
        assertFalse(mbrick.isDestroyed());
        assertEquals(mbrick.getTexture(),"brick_metal_cracked.png");

        repeat(3, () -> mbrick.hit());
        assertTrue(mbrick.isDestroyed());
        assertFalse(mbrick.isNormal());
        assertFalse(mbrick.isHitted());
        assertFalse(mbrick.isAlmostBroke());
        assertEquals(mbrick.getTexture(),"");

    }

    @Test
    public void BehaviourSelectorTest(){
        Brick gbrick,mbrick,wbrick;
        gbrick= new GlassBrick();
        wbrick=new WoodenBrick();
        mbrick= new MetalBrick();

        BehaviourSelector bhgbrick= new BehaviourSelector(gbrick);
        BehaviourSelector bhwbrick= new BehaviourSelector(wbrick);
        BehaviourSelector bhmbrick= new BehaviourSelector(mbrick);
        assertEquals(bhgbrick.getSound(),"GlassHit.wav");
        assertEquals(bhwbrick.getSound(),"WoodenHit.wav");
        assertEquals(bhmbrick.getSound(),"MetalHit.wav");

    }
    private void repeat(int n, Runnable action) {
        IntStream.range(0, n).forEach(((i -> action.run())));
    }
}
