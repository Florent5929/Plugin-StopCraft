package fr.Florent59.StopCraft;



import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import net.md_5.bungee.api.ChatColor;


public class BloquerCraft implements Listener {
	
	
	
	@EventHandler (priority = EventPriority.LOW)
	public void QuandUnItemEstCraft(CraftItemEvent e){
		
		HumanEntity p = e.getWhoClicked();
		String item = "" + e.getCurrentItem().getType();
		
			if(!p.isOp() && !main.StrItemsAllow.contains(item)){
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Afin de préserver l'économie, la plupart des crafts sont bloqués sur ce serveur. Tapez la commande /showItemsAllow pour voir ceux qui font figure d'exception.");
			}
		
	}
	

}

