package info.omgwtfhax.quests.item;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A class to hold all compasses and their respective owner.
 * */
public class QuestCompass{
	
	private static HashMap<String, ItemStack> QUEST_COMPASSES = new HashMap<String, ItemStack>();
	
	/**Assigns a new compass to the player.*/
	public static void addCompass(String player)
	{
		QUEST_COMPASSES.put(player, new ItemStack(Material.COMPASS));
	}
	
	/**Removes the compass belonging to the player.*/
	public static void removeCompass(String player)
	{
		QUEST_COMPASSES.remove(player);
	}
	
	public static ItemStack getCompass(String player)
	{
		return QUEST_COMPASSES.get(player);
	}
}
