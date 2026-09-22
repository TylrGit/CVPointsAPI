package org.cubeville.CVPointsAPI.dto.update;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/* Request:
    {
        "uuid1": {
            "scores": {
                "patreon.patreon_points": 5,
                "patreon.patreon_points_total": 5
            },
            "permissions": {
                "donate1": false,
                "donate5": true,
                "donate10": false,
                "blockhat": true
            }
        },
        "uuid2": {
            "scores": {
                "patreon.patreon_points": 10,
                "patreon.patreon_points_total": 10
            },
            "permissions": {
                "donate1": false,
                "donate5": false,
                "donate10": true,
                "blockhat": true
            },
        },
        ...
    }
*/

// Actual response will be a Map<String, UpdateSubRequest>, String = UUID
public record UpdateSubRequest(
    @SerializedName("scores") Map<String, Integer> points,
    @SerializedName("permissions") Map<String, Boolean> groups
) {}