package me.keegan.javapitsurvival.builders;

import me.keegan.javapitsurvival.PitSurvival;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class CheckItem {
    public static boolean IsSameItem(ItemStack itemStack, ItemStack itemStack2) {
        if (itemStack == null || itemStack2 == null) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        ItemMeta itemMeta2 = itemStack2.getItemMeta();

        if (itemMeta != null && itemMeta2 != null) {
            PersistentDataContainer data = itemMeta.getPersistentDataContainer();
            PersistentDataContainer data2 = itemMeta2.getPersistentDataContainer();

            if (!data.has(new NamespacedKey(PitSurvival.getPlugin(), "ItemType")) || !data2.has(new NamespacedKey(PitSurvival.getPlugin(), "ItemType"))) {
                return false;
            }

            return Objects.equals(data.get(new NamespacedKey(PitSurvival.getPlugin(), "ItemType"), PersistentDataType.STRING), data2.get(new NamespacedKey(PitSurvival.getPlugin(), "ItemType"), PersistentDataType.STRING));
        }

        return false;
    }
}
