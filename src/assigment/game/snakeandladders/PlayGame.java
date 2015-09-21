package assigment.game.snakeandladders;

import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) {
		SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
		
		System.out.println("Snake & Ladder Game \n User Options");
        System.out.println("1. Start Game \n2. "
        + "Sinlge Player \n3. Multi Players(only 2) \n4. Exit Game");
        System.out.print("Please provide your option : ");

        int i = 1;
        
        while (i != 0)
        {
                Scanner in = new Scanner(System.in);
                i = in.nextInt();

                switch (i)
                {
                        case 1:
                                if (snakeAndLadder.playerList.size() == 0)
                                {
                                        System.out.println("Please create users and play the game..");
                                        break;
                                }
                                else
                                {
                                	snakeAndLadder.playGame();
                                }
                                break;

                        case 2:
                        	snakeAndLadder.createSingleUser();
                                break;
                        
                        case 3:
                        	snakeAndLadder.createMultipleUser();
                                break;
                        
                        case 4:
                                System.exit(0);
                }

                System.out.println();
                System.out.println("Snake & Ladder Game \n Select User Options");
                System.out.println("1. Start Game \n2. "
                + "Sinlge Player \n3. Multi Players(only 2) \n4. Exit Game");
                System.out.print("Please provide your option : ");
        }

	}

}
