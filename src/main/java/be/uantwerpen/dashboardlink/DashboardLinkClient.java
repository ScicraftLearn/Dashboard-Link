package be.uantwerpen.dashboardlink;

import be.uantwerpen.dashboardlink.event.ClientModEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DashboardLinkClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModEvents.registerEvents();
    }
}
