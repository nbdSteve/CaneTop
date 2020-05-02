package gg.steve.bgbuddyboy.canetop;

import gg.steve.bgbuddyboy.canetop.core.CanePlayerManager;
import gg.steve.bgbuddyboy.canetop.managers.FileManager;
import gg.steve.bgbuddyboy.canetop.managers.SetupManager;
import gg.steve.bgbuddyboy.canetop.papi.CanePlayerExpansion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;

public final class CaneTop extends JavaPlugin {
    private static CaneTop instance;
    private static DecimalFormat numberFormat = new DecimalFormat("#,###.##");

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        SetupManager.setupFiles(new FileManager(instance));
        SetupManager.registerCommands(instance);
        SetupManager.registerEvents(instance);
        CanePlayerManager.init();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new CanePlayerExpansion(instance).register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        CanePlayerManager.saveCanePlayers();
    }

    public static CaneTop get() {
        return instance;
    }

    public static DecimalFormat getNumberFormat() {
        return numberFormat;
    }
}
