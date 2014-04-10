package info.omgwtfhax.quests.core;

import info.omgwtfhax.quests.Quest;

import java.util.ArrayList;
import java.util.List;

public class OWHQuests {
	
	BukkitPlugin bukkitPlugin;
	
	private List<Quest> quests = new ArrayList<Quest>();
	
	public OWHQuests(BukkitPlugin instance)
	{
		bukkitPlugin = instance;
	}

}
