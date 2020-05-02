package gg.steve.bgbuddyboy.canetop.listener;

import gg.steve.bgbuddyboy.canetop.core.CanePlayer;
import gg.steve.bgbuddyboy.canetop.core.CanePlayerManager;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CaneBreakEvent implements Listener {
    private int caneMined = 1;

    @EventHandler
    public void caneBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!event.getBlock().getType().toString().equalsIgnoreCase("sugar_cane_block")) return;
        this.caneMined = 1;
        CanePlayer player = CanePlayerManager.getCanePlayer(event.getPlayer().getUniqueId());
        isSugarCaneAbove(event.getBlock());
        player.incrementCaneMined(this.caneMined);
    }

    public boolean isSugarCaneAbove(Block block) {
        Block above = block.getRelative(BlockFace.UP);
        if (above.getType().toString().equalsIgnoreCase("sugar_cane_block")) {
            this.caneMined++;
            isSugarCaneAbove(above);
        } else {
            return false;
        }
        return true;
    }
}