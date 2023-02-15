package be.uantwerpen.dashboardlink.dashboard;

import be.uantwerpen.dashboardlink.DashboardLink;

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
        if (response.statusCode() == 200) {
            // TODO actual Mapping  (contains Json mapping, only need ID and minecraft_name)
            response.body();
        } else {
            DashboardLink.LOGGER.info("Failed to get data, response code: " + response.statusCode());
        }

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

    /**
     * Resets all the player's made advancements
     *
     * @param player_name : String, who do we need to reset
     */
    public static void revokeAdvancements(String player_name) {
        //TODO CHECK
        makeConnection(connection + String.format("/reset_advancements/%s", player_name));
    }

    /**
     * Make the actual Dashboard connection
     *
     * @param url : API end-point url
     * @return Response from the Dashboard
     */
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
