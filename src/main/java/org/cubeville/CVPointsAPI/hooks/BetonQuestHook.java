package org.cubeville.CVPointsAPI.hooks;

import org.betonquest.betonquest.BetonQuest;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BetonQuestHook {
    private static BetonQuest BQ;

    public static BetonQuest getBetonQuest() { return BQ; }

    public static boolean hook() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("BetonQuest");

        if (plugin instanceof BetonQuest && plugin.isEnabled()) {
            BQ = (BetonQuest) plugin;
            return true;
        }

        return false;
    }
}