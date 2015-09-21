package assigment.game.snakeandladders.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import assigment.game.snakeandladders.Player;
import assigment.game.snakeandladders.SnakeAndLadder;

public class TestSnakeAndLadders {
	
	int expectedBoardArray[] = new int[120];
	int expectedSnakePointersize= 7;
	int expectedLadderPointerSize=7;
	
	public  Map<String, Player>   expectedPlayerList = new HashMap<String, Player>();

	@Test
	public void testInitializeBoard() {
		SnakeAndLadder snakeAndLadder = new SnakeAndLadder();	
		
		
		 for (int i = 0; i < 120; i++)
         {
			 expectedBoardArray[i] = i + 1;
         }
		
		int[] resultArray =  snakeAndLadder.boardArray;
		
		//Test array length
		
		assertEquals(expectedBoardArray.length, resultArray.length);
		
		//Test Array contents
		
		assertArrayEquals(expectedBoardArray, resultArray);
		
		
		//Test Snake Pointers size.
		
		assertEquals(expectedSnakePointersize, snakeAndLadder.snakePointersSize);
		
		//Test Ladder Pointers size
		
		assertEquals(expectedLadderPointerSize, snakeAndLadder.ladderPointersSize);
		
		
	}

	@Test
	public void testCreateSingleUser() {
		
		SnakeAndLadder snakeAndLadder = new SnakeAndLadder();	
		Player player = new Player(1);
		this.expectedPlayerList.put("Player"+1, player);
		snakeAndLadder.createSingleUser();
		
		assertEquals(this.expectedPlayerList.keySet().size(), snakeAndLadder.playerList.keySet().size());
	}

	
}
