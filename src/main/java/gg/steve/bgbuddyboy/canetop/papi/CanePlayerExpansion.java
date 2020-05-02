package gg.steve.bgbuddyboy.canetop.papi;

import gg.steve.bgbuddyboy.canetop.CaneTop;
import gg.steve.bgbuddyboy.canetop.core.CanePlayerManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class CanePlayerExpansion extends PlaceholderExpansion {
    private CaneTop instance;

    public CanePlayerExpansion(CaneTop instance) {
        this.instance = instance;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return instance.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "cane-top";
    }

    @Override
    public String getVersion() {
        return instance.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) return "";
        if (identifier.equalsIgnoreCase("mined")) {
            return String.valueOf(CanePlayerManager.getCanePlayer(player.getUniqueId()).getCaneMined());
        }
        return null;
    }
}
