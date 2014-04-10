package info.omgwtfhax.quests.items;

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
	
	//Test book
	public static ItemStack getBook()
	{
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK,1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.addPage(ChatColor.DARK_RED + "whatup");
		meta.setTitle("Quests");
		meta.setAuthor("Amperion");
		book.setItemMeta(meta);
		return book;
	}
}
