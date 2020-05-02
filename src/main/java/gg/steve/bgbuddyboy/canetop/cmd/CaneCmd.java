package gg.steve.bgbuddyboy.canetop.cmd;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import gg.steve.bgbuddyboy.canetop.CaneTop;
import gg.steve.bgbuddyboy.canetop.core.CanePlayer;
import gg.steve.bgbuddyboy.canetop.core.CanePlayerManager;
import gg.steve.bgbuddyboy.canetop.managers.ConfigManager;
import gg.steve.bgbuddyboy.canetop.managers.MessageType;
import gg.steve.bgbuddyboy.canetop.permission.PermissionNode;
import org.bukkit.entity.Player;

@CommandAlias("c|ct|cane")
public class CaneCmd extends BaseCommand {

    @CatchUnknown @Default
    public void base(Player player) {
        this.help(player);
    }

    @Subcommand("t|top")
    @CommandCompletion("top")
    @Syntax("<name> [page] &e- View the players who have mined the most sugarcane.")
    public void top(Player player, @Default("1") Integer page) {
        if (!PermissionNode.TOP.hasPermission(player)) {
            MessageType.PERMISSION_DEBUG.message(player, PermissionNode.TOP.get());
            return;
        }
        MessageType.TOP_HEADER.message(player, String.valueOf(page));
        for (int i = (page - 1) * ConfigManager.entriesPerPage; i <= ((page - 1) * ConfigManager.entriesPerPage + (ConfigManager.entriesPerPage - 1)); i++) {
            if (i >= CanePlayerManager.getPlayersInCaneMinedOrder().size()) break;
            CanePlayer canePlayer = CanePlayerManager.getPlayersInCaneMinedOrder().get(i);
            MessageType.TOP_ENTRY.message(player, String.valueOf(i + 1), canePlayer.getOfflinePlayer().getName(), String.valueOf(canePlayer.getCaneMined()));
        }
        MessageType.TOP_FOOTER.message(player, String.valueOf(page));
    }

    @Subcommand("r|reload")
    @CommandCompletion("reload")
    @Syntax("<name> reload &e- Reload configuration for the plugin.")
    public void reload(Player player) {
        if (!PermissionNode.RELOAD.hasPermission(player)) {
            MessageType.PERMISSION_DEBUG.message(player, PermissionNode.RELOAD.get());
            return;
        }
        ConfigManager.reload();
        CaneTop.get().onDisable();
        CaneTop.get().onEnable();
        MessageType.RELOAD.message(player);
    }

    @Subcommand("h|help")
    @CommandCompletion("help")
    @Syntax("<name> help &e- Displays a helpful message about the plugin.")
    public void help(Player player) {
        if (!PermissionNode.HELP.hasPermission(player)) {
            MessageType.PERMISSION_DEBUG.message(player, PermissionNode.HELP.get());
            return;
        }
        MessageType.HELP.message(player);
    }
}
