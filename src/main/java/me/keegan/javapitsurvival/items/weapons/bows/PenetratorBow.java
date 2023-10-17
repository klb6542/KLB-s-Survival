package me.keegan.javapitsurvival.items.weapons.bows;

import me.keegan.javapitsurvival.PitSurvival;
import me.keegan.javapitsurvival.builders.CheckItem;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class PenetratorBow implements Listener {
    public int numParticles = 3;
    public ItemStack GetPenetratorBow() {
        ItemStack itemStack = new ItemStack(Material.BOW);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.displayName(Component.text(ChatColor.RED + "Penetrator Bow"));

        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 6, true);
        itemMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
        itemMeta.addEnchant(Enchantment.ARROW_FIRE, 2, true);

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(PitSurvival.getPlugin(), "ItemType"), PersistentDataType.STRING, "PenetratorBow");

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Entity shooter = (Entity) event.getEntity().getShooter();

        if (shooter instanceof Player) {
            Player player = (Player) shooter;

            if (!CheckItem.IsSameItem(player.getInventory().getItemInMainHand(), GetPenetratorBow()) && !CheckItem.IsSameItem(player.getInventory().getItemInOffHand(), GetPenetratorBow())) {
                return;
            }
        }

        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (arrow.isOnGround() || arrow.isDead() || arrow.isInWaterOrRainOrBubbleColumn()) {
                        this.cancel();
                        return;
                    }

                    Location arrowLocation = arrow.getLocation();

                    for (int i = 0; i < numParticles; i++) {
                        Location particleLocation = arrowLocation.clone().subtract(arrow.getVelocity().normalize().multiply(1 - ((i * 0.1) + (i + 1) * 0.1))); // Spreads the particle out in a straight line
                        arrow.getWorld().spawnParticle(Particle.FLAME, particleLocation, numParticles, 0.0, 0.0, 0.0, 0.0);
                    }
                }
            }.runTaskTimer(PitSurvival.getPlugin(), 0, 1); // Run the task every tick
        }
    }
}
