package gg.steve.bgbuddyboy.canetop.managers;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public enum ConfigManager {
    CONFIG("cane-top.yml"),
    DATA("data" + File.separator + "cane-data.yml");

    private final String path;

    ConfigManager(String path) {
        this.path = path;
    }

    public void load(FileManager fileManager) {
        fileManager.add(name(), this.path);
    }

    public YamlConfiguration get() {
        return FileManager.get(name());
    }

    public void save() {
        FileManager.save(name());
    }

    public static void reload() {
        FileManager.reload();
    }
}
