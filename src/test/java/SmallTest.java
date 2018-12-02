import controller.Game;
import facade.HomeworkTwoFacade;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.Level;
import logic.level.PlaceHolder;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(placeholder,placeholder.addPlayingLevel(hw2.newLevelWithBricksFull("",20,0.5,0.5,seed)));

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

}
