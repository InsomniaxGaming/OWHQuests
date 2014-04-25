package info.omgwtfhax.quests;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

/**
 * A destination that can be set in any quest, along with extra data
 * pertaining to this destination.
 * */
@SerializableAs("Waypoint")
public class Waypoint extends Location implements ConfigurationSerializable{
	
	public static String S_PLAYER_MESSAGE="pm",S_GLOBAL_MESSAGE="gm";
	
	private static int WAYPOINT_COUNT = 0;
	
	/**
	 * An optional message to send to the player when they reach this waypoint.
	 * */
	private String playerMessage = null;
	
	/**
	 * An optional message to send to everyone when the player reaches this waypoint. 
	 * */
	private String globalMessage = null;
	
	private int id;
	
	public Waypoint(World world, double x, double y, double z)
	{
		super(world, x, y, z);
		
		id = ++WAYPOINT_COUNT;
	}
	
	public Waypoint(Map<String, Object> map)
	{
		super(Bukkit.getWorld((String)map.get("world")), (double)map.get("x"), (double)map.get("y"), (double)map.get("z"));
		
		playerMessage = (String) map.get(S_PLAYER_MESSAGE);
		globalMessage = (String) map.get(S_GLOBAL_MESSAGE);
		
		id = ++WAYPOINT_COUNT;
	}
	
	public void activateWaypoint(Player player)
	{
		if(playerMessage != null)
		{
			String message = playerMessage.replace("-p", player.getName());
			String[] messages = StringUtils.split(message,"\n");
			
			for(String m : messages)
				player.sendMessage(m);
		}
		if(globalMessage != null)
		{
			String message = globalMessage.replace("-p", player.getName());
			String[] messages = StringUtils.split(message,"\n");
			
			for(String m : messages)
				Bukkit.broadcastMessage(m);
		}
	}
	
	public void setPlayerMessage(String message)
	{
		playerMessage = message;
	}
	
	public String getPlayerMessage()
	{
		if(playerMessage == null)
			return "N/A";
		else
			return playerMessage;
	}
	
	public void setGlobalMessage(String message)
	{
		globalMessage = message;
	}
	
	public String getGlobalMessage()
	{
		if(globalMessage == null)
			return "N/A";
		else
			return globalMessage;
	}
	
	public int getId()
	{
		return id;
	}

	@Override
	public Map<String, Object> serialize() 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("world",this.getWorld().getName());
		map.put("x", this.getX());
		map.put("y", this.getY());
		map.put("z", this.getZ());
		
		map.put(S_PLAYER_MESSAGE, playerMessage);
		map.put(S_GLOBAL_MESSAGE, globalMessage);
		
		return map;
	}
}
