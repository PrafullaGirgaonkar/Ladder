package assigment.game.snakeandladders;

public class Player {
	
    int     id;

    int     currentPosition = 0;
	
	public Player(int i ) {
			id=i;
	}
	
    public int getCurrentPosition()
    {
            return currentPosition;
    }

    public void setCurrentPosition(int currentPosition)
    {
            this.currentPosition = currentPosition;
    }

}
