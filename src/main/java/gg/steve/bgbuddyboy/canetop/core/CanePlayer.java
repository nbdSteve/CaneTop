package gg.steve.bgbuddyboy.canetop.core;

import gg.steve.bgbuddyboy.canetop.managers.ConfigManager;
import gg.steve.bgbuddyboy.canetop.utils.LogUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CanePlayer {
    private UUID playerId;
    private int caneMined;

    public CanePlayer(UUID playerId) {
        this.playerId = playerId;
        if (ConfigManager.DATA.get().get("cane-mined." + playerId) == null) {
            ConfigManager.DATA.get().set("cane-mined." + playerId, 0);
            ConfigManager.DATA.save();
            LogUtil.info("Cane data for player " + playerId + " was not found, creating a new data entry for them now.");
        }
        this.caneMined = ConfigManager.DATA.get().getInt("cane-mined." + playerId);
    }

    public void incrementCaneMined(int amount) {
        this.caneMined += amount;
    }

    public void saveCaneMined() {
        ConfigManager.DATA.get().set("cane-mined." + playerId, this.caneMined);
        ConfigManager.DATA.save();
    }

    public boolean isOnline() {
        return Bukkit.getPlayer(playerId) != null;
    }

    public Player getPlayer() {
        if (!isOnline()) return null;
        return Bukkit.getPlayer(playerId);
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(playerId);
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public int getCaneMined() {
        return caneMined;
    }
}
