package apipublic;

import fetch.Fetcher;
import hotel.Hotel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Achievements {
    public static List<GlobalAchievement> getAllAchievements(Hotel hotel) {
        try {
            JSONArray achievementsJson = Fetcher.fetchJSONArray(String.format("%sapi/public/achievements", hotel.domain));

            return GlobalAchievement.parse(achievementsJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }
    public static List<UserAchievement> getAchievementsFromUser(Hotel hotel, String userUniqueId) {
        try {
            JSONArray achievementsJson = Fetcher.fetchJSONArray(String.format("%sapi/public/achievements/%s", hotel.domain, userUniqueId));

            return UserAchievement.parse(achievementsJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static class Achievement {
        public final AchievementInfo info;

        private Achievement(JSONObject jsonObject) {
            this.info = new AchievementInfo(jsonObject.getJSONObject("achievement"));
        }

        public static class AchievementInfo {
            public final int id;
            public final String name, state, category;
            public final Date creationTime;

            private AchievementInfo(JSONObject jsonObject) {
                this.id = jsonObject.getInt("id");

                this.name = jsonObject.getString("name");
                this.state = jsonObject.getString("state");
                this.category = jsonObject.getString("category");

                this.creationTime = Date.from(Instant.parse(jsonObject.getString("creationTime").substring(0, 22) + "Z"));
            }
        }
    }

    public static class GlobalAchievement extends Achievement {
        public final List<LevelRequirement> levelRequirements;

        private GlobalAchievement(JSONObject jsonObject) {
            super(jsonObject);
            this.levelRequirements = LevelRequirement.parse(jsonObject.getJSONArray("levelRequirements"));
        }

        private static List<GlobalAchievement> parse(JSONArray jsonArray) {
            return Collections.unmodifiableList(
                    jsonArray
                        .toList()
                        .parallelStream()
                        .map(o -> (Map<String, Object>) o)
                        .map(JSONObject::new)
                        .map(GlobalAchievement::new)
                        .collect(Collectors.toList())
            );
        }

        public static class LevelRequirement {
            public final int level, requiredScore;

            private LevelRequirement(JSONObject jsonObject) {
                this.level = jsonObject.getInt("level");
                this.requiredScore = jsonObject.getInt("requiredScore");
            }

            private static List<LevelRequirement> parse(JSONArray jsonArray) {
                return Collections.unmodifiableList(
                        jsonArray
                            .toList()
                            .parallelStream()
                            .map(o -> (Map<String, Object>) o)
                            .map(JSONObject::new)
                            .map(LevelRequirement::new)
                            .collect(Collectors.toList())
                );
            }
        }
    }

    public static class UserAchievement extends Achievement {
        public final int level, score;

        private UserAchievement(JSONObject jsonObject) {
            super(jsonObject);
            this.level = jsonObject.getInt("level");
            this.score = jsonObject.getInt("score");
        }

        private static List<UserAchievement> parse(JSONArray jsonArray) {
            return Collections.unmodifiableList(
                    jsonArray
                            .toList()
                            .parallelStream()
                            .map(o -> (Map<String, Object>) o)
                            .map(JSONObject::new)
                            .map(UserAchievement::new)
                            .collect(Collectors.toList())
            );
        }
    }
}
