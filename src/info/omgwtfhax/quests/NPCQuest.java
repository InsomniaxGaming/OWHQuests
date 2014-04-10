package info.omgwtfhax.quests;

import java.util.List;

import net.citizensnpcs.api.npc.NPC;

public class NPCQuest extends Quest{
	
	private List<NPC> questNpcs;

	public NPCQuest(String name) {
		super(name);
	}

}
