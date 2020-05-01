package gg.steve.bgbuddyboy.canetop.core;

import java.util.Comparator;

public class CaneComparator implements Comparator<CanePlayer> {

    /**
     * Compares 2 players cane mined
     *
     * @return int
     */
    @Override
    public int compare(CanePlayer player1, CanePlayer player2) {
        return player2.getCaneMined() - player1.getCaneMined();
    }
}

