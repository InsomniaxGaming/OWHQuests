package info.omgwtfhax.quests.event;

import info.omgwtfhax.quests.core.OWHQuests;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

}
