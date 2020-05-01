package gg.steve.bgbuddyboy.canetop.utils;

import gg.steve.bgbuddyboy.canetop.CaneTop;

public class LogUtil {

    public static void info(String message) {
        CaneTop.get().getLogger().info(message);
    }

    public static void warning(String message) {
        CaneTop.get().getLogger().warning(message);
    }
}
