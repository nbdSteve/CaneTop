package gg.steve.bgbuddyboy.canetop.managers;

import co.aikar.commands.PaperCommandManager;
import gg.steve.bgbuddyboy.canetop.cmd.CaneCmd;
import gg.steve.bgbuddyboy.canetop.core.CanePlayerManager;
import gg.steve.bgbuddyboy.canetop.listener.CaneBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Class that handles setting up the plugin on start
 */
public class SetupManager {

    private SetupManager() throws IllegalAccessException {
        throw new IllegalAccessException("Manager class cannot be instantiated.");
    }

    /**
     * Loads the files into the file manager
     *
     * @param fileManager FileManager, the plugins file manager
     */
    public static void setupFiles(FileManager fileManager) {
        // general files
        for (ConfigManager file : ConfigManager.values()) {
            file.load(fileManager);
        }
        ConfigManager.entriesPerPage = ConfigManager.CONFIG.get().getInt("entries-per-page");
    }

    public static void registerCommands(Plugin instance) {
        PaperCommandManager manager = new PaperCommandManager(instance);
        manager.registerCommand(new CaneCmd());
    }

    /**
     * Register all of the events for the plugin
     *
     * @param instance Plugin, the main plugin instance
     */
    public static void registerEvents(Plugin instance) {
        PluginManager pm = instance.getServer().getPluginManager();
        pm.registerEvents(new CanePlayerManager(), instance);
        pm.registerEvents(new CaneBreakEvent(), instance);
    }
}
