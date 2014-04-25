package info.omgwtfhax.quests.core;

import info.omgwtfhax.quests.Quest;
import info.omgwtfhax.quests.item.QuestBook;
import info.omgwtfhax.quests.item.QuestCompass;

import java.util.ArrayList;
import java.util.List;

public class OWHQuests {
	
	BukkitPlugin bukkitPlugin;
	
	private List<Quest> quests = new ArrayList<Quest>();
	
	public OWHQuests(BukkitPlugin instance)
	{
		bukkitPlugin = instance;
	}
	
	/**
	 * Load up defaults and/or saved data for the given player.
	 * */
	public void initializePlayer(String player)
	{
		QuestCompass.addCompass(player);
		
		if(QuestBook.getBook(player) == null)
			QuestBook.addBook(player, QuestBook.getDefaultBook());
	}
	
	/**
	 * Remove all contemporary data pertaining to the given player.
	 * */
	public void finalizePlayer(String player)
	{
		QuestCompass.resetCompassTarget(player);
	}
	
	/**
	 * Returns the list of current quests.
	 * */
	public List<Quest> getQuests()
	{
		return quests;
	}
	
	public void addQuest(Quest quest)
	{
		quests.add(quest);
	}
}
