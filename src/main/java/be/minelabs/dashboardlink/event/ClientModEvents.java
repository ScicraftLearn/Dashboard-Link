package be.minelabs.dashboardlink.event;

import be.minelabs.dashboardlink.dashboard.Dashboard;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class ClientModEvents {
    public static void registerEvents() {

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            assert client.player != null;
            Dashboard.playerConnection(client.player.getName().getString(), true);
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            assert client.player != null;
            Dashboard.playerConnection(client.player.getName().getString(), false);
        });
    }
}
