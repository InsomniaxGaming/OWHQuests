package info.omgwtfhax.quests.event;

import info.omgwtfhax.quests.core.OWHQuests;
import info.omgwtfhax.quests.item.QuestBook;
import info.omgwtfhax.quests.item.QuestCompass;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
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
	public void onInventoryClick(InventoryClickEvent e)
	{
		// If either item is null (caused by failure to immediately update inventories after cancelled events),
		// return as to avoid big ugly errors in console that make me sad.
		if(e.getCursor() == null)
			return;
		if(e.getCurrentItem() == null)
			return;
		
		if(e.getInventory() != e.getWhoClicked().getInventory())
		{
			if(e.getCursor().equals(QuestCompass.getCompass()) || e.getCurrentItem().equals(QuestCompass.getCompass()))
			{
				e.setResult(Result.DENY);
				e.setCancelled(true);
				return;
			}
			
			for(String key : QuestBook.getBooks().keySet())
			{
				if(e.getCursor().equals(QuestBook.getBook(key)) || e.getCurrentItem().equals(QuestCompass.getCompass()))
				{
					e.setResult(Result.DENY);
					e.setCancelled(true);
					return;
				}
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
