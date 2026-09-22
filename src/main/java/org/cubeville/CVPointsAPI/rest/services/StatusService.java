package org.cubeville.CVPointsAPI.rest.services;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.cubeville.CVPointsAPI.dao.PointsDAO;
import org.cubeville.CVPointsAPI.dto.status.StatusRequest;
import org.cubeville.CVPointsAPI.dto.status.StatusSubResponse;
import org.cubeville.CVPointsAPI.hooks.VaultHook;

import java.util.*;

public class StatusService {
    // For each UUID, get Point amounts & if in permission Group
    public static Map<String, StatusSubResponse> processStatus(StatusRequest req) {
        // StatusResponse to return
        Map<String, StatusSubResponse> statuses = new HashMap<>();

        for (UUID uuid : req.uuids()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);

            // Check point value for each category
            Map<String, Integer> points = new HashMap<>();

            for (String category : req.categories()) {
                points.put(category, PointsDAO.getPoints(uuid, category));
            }

            // Check if in each group
            ArrayList<String> groups = new ArrayList<>();

            for (String group : req.groups()) {
                if (VaultHook.getPermissions().playerInGroup(null, player, group)) {
                    groups.add(group);
                }
            }

            // Add status to map for this "UUID"
            statuses.put(uuid.toString(), new StatusSubResponse(points, groups));
        }

        return statuses;
    }
}