package apipublic;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import hotel.Hotel;
import fetch.Fetcher;

public class Badges {
    /**
     * Collect badges from selected user
     * @param hotel Hotel to search
     * @param userUniqueId User's unique id
     * @return List<Badge>
     */
    public static List<Badge> getBadgesFromUserUniqueId(Hotel hotel, String userUniqueId) {
        try {
            JSONArray badgesJson = Fetcher.fetchJSONArray(String.format("%sapi/public/users/%s/badges", hotel.domain, userUniqueId));

            return Badge.parse(badgesJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static class Badge {
        public final String code, name, description;

        private Badge(JSONObject jsonObject) {
            this.code = jsonObject.optString("code", null);
            this.name = jsonObject.optString("name", null);
            this.description = jsonObject.optString("description", null);
        }

        public static List<Badge> parse(JSONArray jsonArray) {
            return Collections.unmodifiableList(
                    jsonArray
                        .toList()
                        .parallelStream()
                        .map(o -> (Map<String, Object>) o)
                        .map(JSONObject::new)
                        .map(Badge::new)
                        .collect(Collectors.toList()));
        }
    }

    public static class SelectedBadge extends Badge {
        public final int badgeIndex;

        private SelectedBadge(JSONObject jsonObject) {
            super(jsonObject);
            this.badgeIndex = jsonObject.getInt("badgeIndex");
        }

        public static List<Badge> parse(JSONArray jsonArray) {
            return Collections.unmodifiableList(
                    jsonArray
                        .toList()
                        .parallelStream()
                        .map(o -> (Map<String, Object>) o)
                        .map(JSONObject::new)
                        .map(SelectedBadge::new)
                        .collect(Collectors.toList()));
        }
    }
}
