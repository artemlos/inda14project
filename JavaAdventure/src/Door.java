
public class Door {
	private Room room;
	private String password;
	
	public Door(Room room, String password)
	{
		this.room = room;
		this.password = password;
	}
	
	public Room getRoom()
	{
		return room;
	}
	
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
     * @return
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
