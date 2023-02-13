package be.uantwerpen.dashboardlink.dashboard;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Dashboard {
    private static final String connection = "https://dashboard.minelabs.be/api";

    /**
     * Get the Advancement mapping
     *
     * @return Map<String, Long> "minecraft_name <-> ID"
     */
    public static Map<String, Long> fetchMapping() {
        HttpResponse<String> response = makeConnection(connection + "/advancements");
        // TODO Map HTTP RESPONSE (contains Json mapping, only need ID and minecraft_name)
        return new HashMap<>();
    }

    /**
     * Set the player's online status
     *
     * @param player_name : player's in-game name
     * @param type        : true = online, false = offline
     */
    public static void playerConnection(String player_name, boolean type) {
        //TODO CHECK
        makeConnection(connection + String.format("/isonline/%s/%s", player_name, type));
    }

    /**
     * Update the Advancement with given ID for the given player_name
     *
     * @param id          : unique ID for advancement
     * @param player_name : String, who completed the advancement
     */
    public static void updateAdvancement(long id, String player_name) {
        //TODO CHECK
        makeConnection(connection + String.format("/advancement/%s/%s", id, player_name));
    }

    private static HttpResponse<String> makeConnection(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
