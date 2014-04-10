package info.omgwtfhax.quests;

public class Quest {
	
	/**Number of quests that currently exist.*/
	private static int COUNT = 0;
	
	/**The unique ID of this quest*/
	private int id;
	
	/**Name of the quest. Every quest requires a unique name.*/
	private String name;
	
	public Quest(String name)
	{
		this.name = name;
		
		//Set id equal to the current value of COUNT, then increment COUNT.
		id=++COUNT;
	}
	
	public String getName()
	{
		return name;
	}
	
	public static int getQuestCount()
	{
		return COUNT;
	}
	
	@Override
	public String toString()
	{
		return id + ": " + name;
	}
	
	@Override
	public void finalize()
	{
		COUNT--;
	}
}
