package org.cubeville.CVPointsAPI.rest.services;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.cubeville.CVPointsAPI.dao.PointsDAO;
import org.cubeville.CVPointsAPI.dto.update.UpdateSubRequest;
import org.cubeville.CVPointsAPI.dto.update.UpdateSubResponse;
import org.cubeville.CVPointsAPI.hooks.VaultHook;

import java.util.*;

public class UpdateService {
    // For each UUID, set Point amounts & change permission groups
    public static Map<String, UpdateSubResponse> processUpdate(Map<String, UpdateSubRequest> req) {
        // UpdateResponse to return
        Map<String, UpdateSubResponse> updates = new HashMap<>();

        for (String uuidStr : req.keySet()) {
            UUID uuid = UUID.fromString(uuidStr);
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            UpdateSubRequest subReq = req.get(uuidStr);

            // Set point value for each point category
            for (String category : subReq.points().keySet()) {
                PointsDAO.setPoints(uuid, category, subReq.points().get(category));
            }

            // Add/Remove group from player if true or false
            List<String> permsAdded = new ArrayList<>();
            List<String> permsRemoved = new ArrayList<>();

            for (String group : subReq.groups().keySet()) {
                if (subReq.groups().get(group)) { // Add to group
                    VaultHook.getPermissions().playerAdd(null, player, group);
                    permsAdded.add(group);
                } else { // Remove from group
                    VaultHook.getPermissions().playerRemove(null, player, group);
                    permsRemoved.add(group);
                }
            }

            updates.put(uuidStr, new UpdateSubResponse(permsAdded, permsRemoved));
        }

        return updates;
    }
}