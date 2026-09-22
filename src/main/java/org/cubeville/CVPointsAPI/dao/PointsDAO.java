package org.cubeville.CVPointsAPI.dao;

import org.betonquest.betonquest.BetonQuest;
import org.betonquest.betonquest.Point;
import org.betonquest.betonquest.api.profiles.Profile;
import org.betonquest.betonquest.database.PlayerData;
import org.betonquest.betonquest.utils.PlayerConverter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.cubeville.CVPointsAPI.hooks.BetonQuestHook;
import java.util.List;
import java.util.UUID;

// BQ Points Direct Access Object
public class PointsDAO {

    public static int getPoints(UUID uuid, String category) {
        BetonQuest bq = BetonQuestHook.getBetonQuest();

        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        Profile profile = PlayerConverter.getID(player);
        PlayerData pData = bq.getOfflinePlayerData(profile);

        if (pData == null) return 0; // No player data

        List<Point> points = pData.getPoints();

        if (points == null) return 0; // No points

        for (Point point : points) {
            if (category.equals(point.getCategory())) {
                return point.getCount();
            }
        }

        return 0; // Category doesn't exist OR player has no defined value yet
    }

    public static void setPoints(UUID uuid, String category, int amount) {
        BetonQuest bq = BetonQuestHook.getBetonQuest();

        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        Profile profile = PlayerConverter.getID(player);
        PlayerData pData = bq.getOfflinePlayerData(profile);

        if (pData == null) return; // No player data

        pData.setPoints(category, amount);
    }
}
