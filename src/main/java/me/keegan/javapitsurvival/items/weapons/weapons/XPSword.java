package me.keegan.javapitsurvival.items.weapons.weapons;

import me.keegan.javapitsurvival.PitSurvival;
import me.keegan.javapitsurvival.builders.CheckItem;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class XPSword implements Listener {

    public static void XPSwordRecipe() {
        ShapedRecipe itemRecipe = new ShapedRecipe(new XPSword().GetXPSword());

        itemRecipe.shape("XDX", "DSD", "XDX");
        itemRecipe.setIngredient('X', Material.EXPERIENCE_BOTTLE);
        itemRecipe.setIngredient('D', Material.DIAMOND);
        itemRecipe.setIngredient('S', Material.DIAMOND_SWORD);

        PitSurvival.getPlugin().getServer().addRecipe(itemRecipe);
    }
    public ItemStack GetXPSword() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();

        ArrayList<String> itemLore = new ArrayList<String>();
        itemLore.add(ChatColor.GRAY + "Harvest " + ChatColor.AQUA + "+100%" + ChatColor.RESET + ChatColor.GRAY + " more xp");

        itemMeta.displayName(Component.text(ChatColor.AQUA + "" + ChatColor.MAGIC + "X " + ChatColor.RESET + ChatColor.AQUA  + "Soul Harvester" + ChatColor.MAGIC + " X"));
        itemMeta.setLore(itemLore);

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(PitSurvival.getPlugin(), "ItemType"), PersistentDataType.STRING, "XPSword");

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @EventHandler
    public void EntityDeath(EntityDeathEvent event) {
       Player killer = event.getEntity().getKiller();

       if (killer != null && CheckItem.IsSameItem(killer.getInventory().getItemInMainHand(), GetXPSword())) {
           event.setDroppedExp(event.getDroppedExp() * 2);
       }
    }
}
