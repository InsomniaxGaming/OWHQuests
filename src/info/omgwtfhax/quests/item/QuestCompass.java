package info.omgwtfhax.quests.item;

import info.omgwtfhax.quests.core.BukkitPlugin;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A class to hold all compasses and their respective owner.
 * */
public class QuestCompass {
	
	private static ItemStack QUEST_COMPASS = new ItemStack(Material.COMPASS,1);
	
	private static HashMap<String, Location> OLD_TARGETS = new HashMap<String, Location>();
	
	/**Assigns a new compass to the player.*/
	public static void addCompass(String player)
	{
		OLD_TARGETS.put(player, BukkitPlugin.getCompassTarget(player));
	}
	
	/**Removes the compass belonging to the player.*/
	public static void resetCompassTarget(String player)
	{
		BukkitPlugin.setCompassTarget(player, OLD_TARGETS.get(player));
	}
	
	public static ItemStack getCompass()
	{
		return QUEST_COMPASS;
	}
}
