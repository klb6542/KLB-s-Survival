package me.keegan.javapitsurvival;

import me.keegan.javapitsurvival.items.weapons.bows.PenetratorBow;
import me.keegan.javapitsurvival.items.weapons.weapons.XPSword;
import org.bukkit.plugin.java.JavaPlugin;

public final class PitSurvival extends JavaPlugin {
    private static PitSurvival plugin;

    @Override
    public void onEnable() {
        plugin = this;

        registerEvents();
        registerRecipes();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new XPSword(), this);
        getServer().getPluginManager().registerEvents(new PenetratorBow(), this);
    }

    private void registerRecipes() {
        XPSword.XPSwordRecipe();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PitSurvival getPlugin() {
        return plugin;
    }
}