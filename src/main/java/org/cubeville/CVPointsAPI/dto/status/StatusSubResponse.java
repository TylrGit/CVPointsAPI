package org.cubeville.CVPointsAPI.dto.status;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/* Response:
    {
      "uuid1": {
        "scores": {
            "patreon.patreon_points": 123,
            "patreon.patreon_points_total": 12345
        },
        "permissions": [ "donate5", "blockhat" ]
      },
      "uuid2": {
        "scores": {
            "patreon.patreon_points": 456,
            "patreon.patreon_points_total": 54321
        }
        "permissions": []
      },
      ...
    }
 */

// Final response will be a Map<String, StatusSubResponse>, String = UUID
public record StatusSubResponse(
        @SerializedName("scores") Map<String, Integer> points,
        @SerializedName("permissions") List<String> groups
) {}