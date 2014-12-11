/**
 * The door class stores the next room and a password.
 * @author Artem Los
 *
 */
public class Door {
	private Room room;
	private String password;
	
	/**
	 * The initialisation of a door.
	 * @param room The room the door should lead to.
	 * @param password The password required to open the door.
	 */
	public Door(Room room, String password)
	{
		this.room = room;
		this.password = password;
	}
	
	/**
	 * Returns the room object.
	 * @return A room.
	 */
	public Room getRoom()
	{
		return room;
	}
	
	/**
	 * Checks whether a room exists.
	 * @return true if a room exists, false otherwise.
	 */
	public boolean hasDoor()
	{
		if(room == null)
		{
			return false;
		}
		return true;
	}
	
    /**
     * Check if a room is password protected
     * @return true if password was assigned, false otherwise.
     */
    public boolean hasPassword()
    {
    	if(password != "")
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /**
     * Check whether the provided password equals to the password in the room.
     * @param password
     * @return true if correct password, false otherwise.
     */
    public boolean doUnlock(String password)
    {
    	if(this.password.equals(password))
    	{
    		password ="";
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
