package info.omgwtfhax.quests.item;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class QuestBook{
	
	private static HashMap<String, ItemStack> QUEST_BOOKS = new HashMap<String, ItemStack>();
	
	public static void addBook(String player, ItemStack book)
	{
		QUEST_BOOKS.put(player, book);
	}
	
	public static void removeBook(String player)
	{
		QUEST_BOOKS.remove(player);
	}
	
	public static ItemStack getBook(String player)
	{
		return QUEST_BOOKS.get(player);
	}
	
	public static HashMap<String, ItemStack> getBooks()
	{
		return QUEST_BOOKS;
	}
	
	//Test book
	public static ItemStack getDefaultBook()
	{
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK,1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle("Quests");
		meta.setAuthor("Amperion");
		meta.addPage("Welcome to OWHQuests! This book will contain information about all quests you receive. You currently have no missions. Read the following pages for useful command references.");
		meta.addPage(ChatColor.GOLD + "/owhquests compass\n" + ChatColor.BLACK + " will toggle a compass pointing to your current mission's waypoint. If you already have a compass equipped, that compass will be used instead.");
		meta.addPage(ChatColor.GOLD + "/owhquests book\n" + ChatColor.BLACK + " will toggle this quest book. It is the only way to remove this book from your inventory.");
		book.setItemMeta(meta);
		return book;
	}
}
