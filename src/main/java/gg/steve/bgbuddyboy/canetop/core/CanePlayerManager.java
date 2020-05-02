package gg.steve.bgbuddyboy.canetop.core;

import gg.steve.bgbuddyboy.canetop.managers.ConfigManager;
import gg.steve.bgbuddyboy.canetop.utils.LogUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

public class CanePlayerManager implements Listener {
    private static Map<UUID, CanePlayer> canePlayers;
    private static List<CanePlayer> playersInCaneMinedOrder;

    public static void init() {
        canePlayers = new HashMap<>();
        playersInCaneMinedOrder = new ArrayList<>();
        if (ConfigManager.DATA.get().getConfigurationSection("cane-mined") == null) return;
        for (String playerId : ConfigManager.DATA.get().getConfigurationSection("cane-mined").getKeys(false)) {
            canePlayers.put(UUID.fromString(playerId), new CanePlayer(UUID.fromString(playerId)));
            playersInCaneMinedOrder.add(getCanePlayer(UUID.fromString(playerId)));
        }
    }

    public static List<CanePlayer> getPlayersInCaneMinedOrder() {
        playersInCaneMinedOrder.sort(new CaneComparator());
        return playersInCaneMinedOrder;
    }

    public static void saveCanePlayers() {
        if (canePlayers == null || canePlayers.isEmpty()) return;
        for (UUID playerId : canePlayers.keySet()) {
            ConfigManager.DATA.get().set("cane-mined." + playerId, canePlayers.get(playerId).getCaneMined());
            ConfigManager.DATA.save();
        }
    }

    public static CanePlayer getCanePlayer(UUID playerId) {
        if (canePlayers.containsKey(playerId)) return canePlayers.get(playerId);
        canePlayers.put(playerId, new CanePlayer(playerId));
        return canePlayers.get(playerId);
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        if (!canePlayers.containsKey(event.getPlayer().getUniqueId())) return;
        canePlayers.get(event.getPlayer().getUniqueId()).saveCaneMined();
        LogUtil.info("Saving cane data for disconnecting player: " + event.getPlayer().getUniqueId());
    }
}
