package org.cubeville.CVPointsAPI.dto.status;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

/* Request:
    {
      "players": [ "uuid1", "uuid2", "uuid3", ... ],
      "permissions": [ "donate1", "donate5", "donate10", "blockhat" ],
      "scores": [ "patreon.patreon_points", "patreon.patreon_points_total" ]
    }
 */
public record StatusRequest(
        // SerializedName -> Signals for GSON converting
        @SerializedName("players") List<UUID> uuids,
        @SerializedName("permissions") List<String> groups,
        @SerializedName("scores") List<String> categories
) {}