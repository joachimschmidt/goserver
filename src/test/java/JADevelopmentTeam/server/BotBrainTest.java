package JADevelopmentTeam.server;

import JADevelopmentTeam.common.Intersection;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BotBrainTest {
    @Test
    public void testPossibleMoves() {
        GameManager gameManager = new GameManager(5);
        Assert.assertEquals(0, gameManager.processMove(new Intersection(0, 0), 0));
        Assert.assertEquals(0, gameManager.processMove(new Intersection(1, 0), 1));
        Assert.assertEquals(0, gameManager.processMove(new Intersection(0, 1), 1));
        Assert.assertEquals(0, gameManager.processMove(new Intersection(0, 2), 0));
        GameManager backup = gameManager.copy();
        ArrayList<Intersection> possibleMoves = BotBrain.getPossibleMoves(gameManager, false, backup);
        Assert.assertEquals(gameManager.getBoardAsIntersections()[0][1].isHasStone(), backup.getBoardAsIntersections()[0][1].isHasStone());
        Assert.assertEquals(gameManager.getBoardAsIntersections()[1][0].isHasStone(), backup.getBoardAsIntersections()[1][0].isHasStone());
        Assert.assertEquals(gameManager.getBoardAsIntersections()[0][1].isStoneBlack(), backup.getBoardAsIntersections()[0][1].isStoneBlack());
        Assert.assertEquals(gameManager.getBoardAsIntersections()[1][0].isStoneBlack(), backup.getBoardAsIntersections()[1][0].isStoneBlack());
        Assert.assertEquals(gameManager.playersStoneChains.get(0).size(), backup.playersStoneChains.get(0).size());
        Assert.assertEquals(gameManager.playersStoneChains.get(1).size(), backup.playersStoneChains.get(1).size());
        Assert.assertEquals(gameManager.playersStones.get(0).size(), backup.playersStones.get(0).size());
        Assert.assertEquals(gameManager.playersStones.get(1).size(), backup.playersStones.get(1).size());
        Assert.assertEquals(21, possibleMoves.size());
        for (Intersection intersection : possibleMoves) {
            Assert.assertEquals(false, intersection.getXCoordinate() == 0 && intersection.getYCoordinate() == 0);
        }


    }

    @Test
    public void testPossibleMoves2() {
        GameManager gameManager = new GameManager(5);
        Assert.assertEquals(0, gameManager.processMove(new Intersection(0, 0), 1));
        Assert.assertEquals(0, gameManager.processMove(new Intersection(4, 4), 0));
        Assert.assertEquals(0, gameManager.processMove(new Intersection(0, 2), 1));
        Assert.assertEquals(0, gameManager.processMove(new Intersection(2, 1), 0));
        Assert.assertEquals(0, gameManager.processMove(new Intersection(1, 1), 1));
        GameManager backup = gameManager.copy();
        ArrayList<Intersection> possibleMoves = BotBrain.getPossibleMoves(gameManager, false, backup);
        Intersection testIntersection = new Intersection(0,1);
        testIntersection.setStoneBlack(false);
        Assert.assertEquals(2,GameLogicCalculator.processMove(testIntersection,gameManager,0));
        for (Intersection intersection : possibleMoves) {
            Assert.assertEquals(false, intersection.getXCoordinate() == 0 && intersection.getYCoordinate() == 1);
        }
        Assert.assertEquals(19, possibleMoves.size());


    }
}
