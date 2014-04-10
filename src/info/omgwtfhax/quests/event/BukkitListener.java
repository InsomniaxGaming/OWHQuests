package info.omgwtfhax.quests.event;

import info.omgwtfhax.quests.core.OWHQuests;
import info.omgwtfhax.quests.item.QuestBook;
import info.omgwtfhax.quests.item.QuestCompass;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class BukkitListener implements Listener{
	
	OWHQuests questsPlugin;
	
	public BukkitListener(OWHQuests instance)
	{
		questsPlugin = instance;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		questsPlugin.initializePlayer(e.getPlayer().getName());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		questsPlugin.finalizePlayer(e.getPlayer().getName());
	}
	
	@EventHandler
	public void onItemMoved(InventoryMoveItemEvent e)
	{
		System.out.println("item moved: " + e.getItem());
			if(e.getItem().equals(QuestCompass.getCompass()))
			{
				e.setCancelled(true);
				return;
			}
			
			for(String key : QuestBook.getBooks().keySet())
			{
				if(e.getItem().equals(QuestBook.getBook(key)))
				{
					e.setCancelled(true);
					return;
				}
			}
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e)
	{
		if(e.getItemDrop().getItemStack().equals(QuestCompass.getCompass()))
		{
			e.getPlayer().sendMessage(ChatColor.GOLD + "Waypoint compass deleted. Use /owhquests compass to retrieve it.");
			return;
		}
			
		for(String key : QuestBook.getBooks().keySet())
		{
			if(e.getItemDrop().getItemStack().equals(QuestBook.getBook(key)))
			{
				e.getPlayer().sendMessage(ChatColor.GOLD + "Quest book deleted. Use /owhquests book to retrieve it.");
				return;
			}
		}
	}
	
	@EventHandler
	public void onItemSpawn(ItemSpawnEvent e)
	{
		if(e.getEntity().getItemStack().equals(QuestCompass.getCompass()))
		{
			e.setCancelled(true);
			return;
		}
		
		for(String key : QuestBook.getBooks().keySet())
		{
			if(e.getEntity().getItemStack().equals(QuestBook.getBook(key)))
			{
				e.setCancelled(true);
				return;
			}
		}
	}

}
