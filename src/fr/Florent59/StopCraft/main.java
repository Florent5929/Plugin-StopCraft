package fr.Florent59.StopCraft;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin {

	private String ponctuation = "";
	public static String StrItemsAllow = new String();
	

	@Override
	public void onEnable(){
		super.onEnable();
		
		List<?> listItemAllowed = this.getConfig().getList("ItemsAllowed");
		
		StrItemsAllow = "";
		
		if (!this.getDataFolder().exists()) { 
	        this.saveDefaultConfig();
	        this.getConfig().options().copyDefaults(true);
	    } // S'il n'y a pas de dossier et de fichier de configuration, on cr�e ceux par d�faut. 
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BloquerCraft(), this);
		
		int i = 0;
		
		
		for(i = 0; i < listItemAllowed.size(); i++){
			
			if(i == listItemAllowed.size()-1){
				ponctuation = ".";
			} else { ponctuation = ", "; }
				
			 StrItemsAllow = StrItemsAllow + listItemAllowed.get(i) + ponctuation; }
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		if(sender instanceof Player ){
			
			// On v�rifie que l'entit� qui ex�cute la commande est un joueur.
			
		}
		if(label.equalsIgnoreCase("showItemsAllow")){ // Si c'est la commande /showItemsAllow ...
			 player.sendMessage(ChatColor.DARK_AQUA + "Les items dont le craft est autoris� � tous, r�pertori�s dans le fichier de configuration, sont : " + StrItemsAllow);
			} // ... on affiche tout simplement la cha�ne de caract�res StrItemsAllow : ItemAutoris�1, ItemAutoris�2 ...
		
		
		else if(label.equalsIgnoreCase("addItemAllow")){ // Si c'est la commande /addItemAllow <item> ...
				if(args.length != 1){ // ... on v�rifie qu'un item a bien �t� renseign�.
					player.sendMessage("Usage : /addItemAllow <item>"); // Sinon on envoie un message qui explique comment utiliser la commande.
				} else if(args.length == 1){
					 List<String> Items  = getConfig().getStringList("ItemsAllowed");
					 if(!main.StrItemsAllow.contains(args[0])){ Items.add(args[0]);
					 getConfig().set("ItemsAllowed", Items);
					 saveConfig(); // Si l'item n'est pas dans la cha�ne d'items "Item1, Item2, etc", on le rajoute � la config.
					 player.sendMessage(ChatColor.DARK_AQUA + args[0] + " a �t� ajout�(e) � la liste des items autoris�s, dans le fichier de configuration.");
					 } else {
							player.sendMessage(ChatColor.RED + args[0] + " existe d�j� dans la liste des items autoris�s, dans le fichier de configuration !");
							 } // Sinon, on envoie un message disant qu'il existe d�j�.
				}		
				getServer().getPluginManager().disablePlugin(this);
	            getServer().getPluginManager().enablePlugin(this);
			} // On relance le plugin pour qu'il recr�e la cha�ne de caract�res  en prenant en compte la modification.
		
		
		 else if(label.equalsIgnoreCase("removeItemAllow")){ // Si c'est la commande /removeItemAllow <item> ...
				if(args.length != 1){ // ... on v�rifie qu'un item a bien �t� renseign�.
					player.sendMessage("Usage : /delItemAllow <item>"); // Sinon on envoie un message qui explique comment utiliser la commande.
				} else if(args.length == 1){
					 List<String> Items  = getConfig().getStringList("ItemsAllowed");
					 if(main.StrItemsAllow.contains(args[0])){ Items.remove(args[0]);
					 getConfig().set("ItemsAllowed", Items);
					 saveConfig(); // Si l'item est dans la cha�ne d'items "Item1, Item2, etc", on le supprime de la config.
					 player.sendMessage(ChatColor.DARK_AQUA + args[0] + " a �t� supprim�(e) de la liste des items autoris�s, dans le fichier de configuration.");
					 } else {
					player.sendMessage(ChatColor.RED + args[0] + " n'existe pas dans la liste des items autoris�s, dans le fichier de configuration, vous ne pouvez donc pas le supprimer.");
					 } // Sinon, on envoie un message disant qu'il n'existe pas.
					 }
				getServer().getPluginManager().disablePlugin(this);
             getServer().getPluginManager().enablePlugin(this);	
			} // On relance le plugin pour qu'il recr�e la cha�ne de caract�res en prenant en compte la modification.
			
		 else if(label.equalsIgnoreCase("showItems")){ // Si c'est la commande /showItems ...
			 player.sendMessage(ChatColor.DARK_PURPLE + "Voici un lien o� vous trouverez l'int�gralit� des items existant sur MC : https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html");
			 player.sendMessage(ChatColor.DARK_PURPLE + "Cherchez ce qui vous int�resse dans le tableau 'Enum Constant and Description'. Par exemple, si c'est le craft des lits que vous voulez autoriser, vous remarquez que 'BED' est r�pertori�.");
			 player.sendMessage(ChatColor.DARK_PURPLE + "Il suffira alors de faire un /addItemAllow BED pour l'ajouter. Vous voyez, c'est tout simple !");
			} 
		
	
		return false;
	}
	
}

