package assigment.game.snakeandladders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class SnakeAndLadder {

	public int                   boardArray[]           = new int[120];
	public int                   snakePointers[][]  = null;
	public int                   ladderPointers[][]        = null;
	public int                   snakePointersSize  = 0;
	public  Map<String, Player>   playerList             = new HashMap<String, Player>();
	public int                   ladderPointersSize        = 0;
	public int                   currentUserIndex   = 0;
	public boolean               multiUser              = true;
     
     public SnakeAndLadder() {
    	 
    	 initializeBoard();
    	 
	}
     

     public void initializeBoard(){
    	 
    	 for (int i = 0; i < 120; i++)
         {
                 boardArray[i] = i + 1;
         }

         Properties prop = new Properties();
         try
         {
                 prop.load(getClass().getClassLoader().getResourceAsStream("game.properties"));
         }
         catch (FileNotFoundException e)
         {
                 e.printStackTrace();
         }
         catch (IOException e)
         {
                 e.printStackTrace();
         }

         snakePointersSize = Integer.parseInt(prop.getProperty("snake.pointers.total"));

         ladderPointersSize = Integer.parseInt(prop.getProperty("ladder.pointers.total"));
         
         
         snakePointers = new int[snakePointersSize][2];

         ladderPointers = new int[ladderPointersSize][2];
         
         for (int i = 0; i < snakePointersSize; i++)
         {
                 String a[] = prop.getProperty("snake.pointers." + (i + 1)).split(",");
                 snakePointers[i][0] = Integer.parseInt(a[0]);
                 snakePointers[i][1] = Integer.parseInt(a[1]);
         }

         for (int i = 0; i < ladderPointersSize; i++)
         {
                 String a[] = prop.getProperty("ladder.pointers." + (i + 1)).split(",");
                 ladderPointers[i][0] = Integer.parseInt(a[0]);
                 ladderPointers[i][1] = Integer.parseInt(a[1]);
         }
    	 
    	 
     }
     
     
     public void viewBoard(){
    	 
    	   System.out.println();
           for (int i = 119; i >= 0; i--)
           {
                   if (i == 119)
                   {
                           System.out.print("   ");
                   }

                   System.out.print(boardArray[i]);
                   System.out.print("    ");

                   if (i != 119 && (i) % 10 == 0)
                   {
                           int f = i - 10;
                           for (int k = f; k < i; k++)
                           {
                                   if (k == f)
                                   {
                                           System.out.println();
                                           System.out.print("    ");
                                   }
                                   if (boardArray[k] / 10 == 0)
                                   {
                                           System.out.print(" ");
                                           System.out.print(boardArray[k]);
                                   }
                                   else
                                   {
                                           System.out.print(boardArray[k]);
                                   }
                                   System.out.print("    ");
                           }
                           i = f;
                           System.out.println();
                           System.out.print("    ");
                   }
           }
           
           displaySLPointers();
     }
     public void displaySLPointers()
     {
             System.out.println();
             System.out.print("Snake Pointers  ");
             for (int i = 0; i < snakePointers.length; i++)
             {
                     System.out.print("[" + snakePointers[i][0] + "->" + snakePointers[i][1] + "] ");
             }
             System.out.println();
             System.out.print("Ladder Pointers  ");
             for (int i = 0; i < ladderPointers.length; i++)
             {
                     System.out.print("[" + ladderPointers[i][0] + "->" + ladderPointers[i][1] + "] ");
             }
             System.out.println();
     }
	     
     public void createSingleUser()
        {
    	 		playerList.clear();
                int id = 1;
                Player usr = new Player(id);
                playerList.put("Player" + id, usr);
                System.out.println("Player" + id + " created successfully");
        }
     
     public void createMultipleUser()
     {
    	 	 playerList.clear();
             multiUser = true;
             for (int i = 0; i < 2; i++)
             {
                     int id = i + 1;
                     Player usr = new Player(id);
                     playerList.put("Player" + id, usr);
                     System.out.println("Player" + id + " created successfully");
             }
     }
     
     public int rollDice()
     {
             Random rn = new Random();
             int op = rn.nextInt(5) + 1;
             System.out.println("Dice Value is...." + op);
             return op;
     }
     
     public void playGame()
     {
             int i = 0;
             System.out.println("Game Started.....  ");
             do
             {
                     String curName = getCurrentUserName();
                     System.out.println("1. Roll Dice \n2. Show Positions \n3. Display Board \n4. Stop Game");
                     System.out.println("Chance for User..." + curName);
                     System.out.print("Please provide your option : ");

                     Scanner in = new Scanner(System.in);
                     i = in.nextInt();

                     switch (i)
                     {
                             case 1:
                                     int curPos = this.updateUserPositions(curName);
                                     if (curPos >= 100)
                                     {
                                             System.out
                                             .println(curName + " has successfully won the game. \n Game exit");
                                             return;
                                     }
                                     break;

                             case 2:
                                     this.displayUserPositions();
                                     if (multiUser == true)
                                             currentUserIndex = (currentUserIndex == 2) ? 1 : 0;
                                     break;
                             
                             case 3:
                                     this.viewBoard();
                                     if (multiUser == true)
                                             currentUserIndex = (currentUserIndex == 2) ? 1 : 0;
                                     break;
                             
                             case 4:
                                     return;
                     }
                     System.out.println();
             } while (i != 0);
     }
     
     public int updateUserPositions(String name)
     {
             Set keyset = playerList.keySet();
             if (keyset.contains(name))
             {
                     Player ur = playerList.get(name);
                     ur.setCurrentPosition(checkSLPointersForCurPos(ur.getCurrentPosition()
                             + this.rollDice()));

                     System.out.println(name + " current position is " + ur.getCurrentPosition());
                     return ur.getCurrentPosition();
             }
             return 0;
     }
     
     public void displayUserPositions()
     {
             Set keyset = playerList.keySet();
             Iterator it = keyset.iterator();
             while (it.hasNext())
             {
                     String key = (String) it.next();
                     Player us = playerList.get(key);
                     System.out.println(key + "current position is...." + us.getCurrentPosition());
             }
     }
     
     public String getCurrentUserName()
     {
             if (currentUserIndex == 0)
             {
                     currentUserIndex++;
                     return "Player" + (currentUserIndex);
             }
             else if (currentUserIndex == 1)
             {
                     if (multiUser == false)
                             return "Player" + currentUserIndex;
                     else
                             currentUserIndex++;
                     return "Player" + currentUserIndex;
             }
             else
             {
                     currentUserIndex--;
                     return "Player" + (currentUserIndex);
             }
     }
     
     public int checkSLPointersForCurPos(int curPos)
     {
             for (int i = 0; i < snakePointers.length; i++)
             {
                     if (snakePointers[i][0] == curPos)
                     {
                             System.out.println("OOPs!! you have been dropped down by Snake...");
                             return snakePointers[i][1];
                     }
             }

             for (int i = 0; i < ladderPointers.length; i++)
             {
                     if (ladderPointers[i][0] == curPos)
                     {
                             System.out.println("Wow!! you have climbed up the ladder...");
                             return ladderPointers[i][1];
                     }
             }
             return curPos;
     }
     
     
}
