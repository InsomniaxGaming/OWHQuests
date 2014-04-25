package info.omgwtfhax.quests;

import java.util.List;

import net.citizensnpcs.api.npc.NPC;

public class NPCQuest extends Quest{
	
	private List<NPC> questNpcs;

	public NPCQuest(String name) {
		super(name);
	}
	
	public boolean addQuestNPC(NPC npc)
	{
		return questNpcs.add(npc);
	}
	
	public boolean removeQuestNPC(NPC npc)
	{
		return questNpcs.remove(npc);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " [NPC]";
	}

}
