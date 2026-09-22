package org.cubeville.CVPointsAPI.dto.update;

import java.util.List;

/* Response:
  {
    "uuid1": {
        "permissionsAdded": [ "donate5", "blockhat" ],
        "permissionsRemoved": [ "donate1", "donate10" ]
    },
    "uuid2": {
        "permissionsAdded": [...],
        "permissionsRemoved": [...]
    },
    ...
  }
*/

// Final response will be a Map<String, UpdateSubResponse>, String = UUID
public record UpdateSubResponse(
    List<String> permissionsAdded,
    List<String> permissionsRemoved
) {}