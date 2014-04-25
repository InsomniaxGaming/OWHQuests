package info.omgwtfhax.quests.core;

import java.util.Arrays;
import java.util.logging.Level;

import info.omgwtfhax.quests.event.BukkitListener;
import info.omgwtfhax.quests.item.QuestBook;
import info.omgwtfhax.quests.item.QuestCompass;
import info.omgwtfhax.quests.vault.Permissions;
import net.citizensnpcs.api.CitizensPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

/**
 * This class acts as a bridge between the plugin and the server software and other server plugins. When possible, 
 * anything that involves referencing bukkit should have to pass through this plugin to make for optimal portability.
 * */
public class BukkitPlugin extends JavaPlugin
{
	
	Permissions p;
	
	/**Main class for Citizens plugin API.*/
	CitizensPlugin citizensPlugin;

	/**Main class for WorldGuard plugin API.*/
	WorldGuardPlugin worldGuardPlugin;
	
	OWHQuests questPlugin;
	
	QuestCompass questCompass = new QuestCompass();
	
	
	public void onEnable()
	{			
		if((citizensPlugin = (CitizensPlugin)Bukkit.getPluginManager().getPlugin("Citizens")) == null)
		{
			this.getLogger().log(Level.SEVERE, "Citizens not found. Disabling OWHQuests.");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		if((worldGuardPlugin = (WorldGuardPlugin)Bukkit.getPluginManager().getPlugin("WorldGuard")) == null)
		{
			this.getLogger().log(Level.SEVERE, "WorldGuard not found. Disabling OWHQuests.");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		
		p = new Permissions(this);
		if(p.setupPermissions())
			this.getLogger().info("Permissions setup successful.");
		else
			this.getLogger().warning("Permissions setup failed, falling back to OP-only for any permissions");
		
		questPlugin = new OWHQuests(this);
		
		Bukkit.getPluginManager().registerEvents(new BukkitListener(questPlugin), this);
	}
	
	public void onDisable()
	{
		this.saveConfig();
	}
	
	public static void broadcast(String message)
	{
		Bukkit.broadcastMessage(message);
	}
	
	public static void setCompassTarget(String player, Location loc)
	{
		Bukkit.getPlayer(player).setCompassTarget(loc);
	}
	
	public static Location getCompassTarget(String player)
	{
		return Bukkit.getPlayer(player).getCompassTarget();
	}
	
	public void toggleQuestBook(Player player)
	{
		ItemStack book = QuestBook.getBook(player.getName());
		
		if(player.getInventory().contains(book))
		{
			player.getInventory().remove(book);
			player.sendMessage(ChatColor.GREEN + "Quest book was removed from your inventory.");
		}
		else
		{
			if(player.getInventory().addItem(book).containsKey(book))
			{				
				//If the book we tried to add is in the list of not added items tell the user their inventory is full
				player.sendMessage(ChatColor.RED + "There's no place in your inventory to put the quest book.");
			}
			else
			{
				//Book added successfully
				player.sendMessage(ChatColor.GREEN + "Quest book was added to your inventory.");
			}
		}
	}
	
	public void toggleCompass(Player player)
	{
		ItemStack compass = QuestCompass.getCompass();
		
		if(player.getInventory().contains(compass))
		{
			player.getInventory().remove(compass);
			player.sendMessage(ChatColor.GREEN + "Quest compass was removed from your inventory.");
		}
		else
		{
			if(player.getInventory().addItem(compass).containsKey(compass))
			{				
				//If the compass we tried to add is in the list of not added items tell the user their inventory is full
				player.sendMessage(ChatColor.RED + "There's no place in your inventory to put the quest compass.");
			}
			else
			{
				//Book added successfully
				player.sendMessage(ChatColor.GREEN + "Quest compass was added to your inventory.");
			}
		}
	}
	
	public boolean createQuest(Player player, String[] args)
	{
		String questName = null;
		boolean buildingName = false;
		
		for(int i = 1; i < args.length; i++)
		{
			if(args[i].startsWith("\""))
			{
				questName = args[i].substring(1);
				buildingName = true;
			}
			if(args[i].endsWith("\""))
			{
				questName += (" " + args[i].substring(0,args[i].length()-1));
				break;
			}
			
			else if(buildingName)
			{
				questName += (" " + args[i]);
			}
		}
		
		if((questName == null) || (questName.equals(" ")))
		{
			player.sendMessage(ChatColor.RED + "You must specify a name.");
			return false;
		}
		
		player.sendMessage(ChatColor.GREEN + "Quest added successfully!");
		return true;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof ConsoleCommandSender)
		{
			getLogger().info("Console cannot use this plugin.");
			return true;
		}
		
		if(args.length == 0)
			return true;
		
		if(p.has(sender, cmd, Arrays.copyOfRange(args, 0, 1), false))
		{
			if(cmd.getName().equalsIgnoreCase("owhquests"))
			{
				if(args.length > 1)
				{
					if(args[0].equalsIgnoreCase("addquest"))
					{
						return createQuest((Player)sender, Arrays.copyOfRange(args, 1, args.length));
					}
				}
				else if(args.length > 0)
				{
					if(args[0].equalsIgnoreCase("book"))
					{
						this.toggleQuestBook((Player)sender);
						return true;
					}
					if(args[0].equalsIgnoreCase("compass"))
					{
						this.toggleCompass((Player)sender);
						return true;
					}
				}
			}
		}
		return false;
	}
}
