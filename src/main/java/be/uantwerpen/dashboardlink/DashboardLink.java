package be.uantwerpen.dashboardlink;

import be.uantwerpen.dashboardlink.dashboard.Advancements;
import be.uantwerpen.dashboardlink.event.ServerModEvents;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DashboardLink implements ModInitializer {
    public static final String MINELABS_ID = "minelabs";
    public static final String MOD_ID = "dashboard-link";
    public static Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static Advancements advancements = new Advancements();

    @Override
    public void onInitialize() {
        LOGGER.info("Welcome to Minelabs Dashboard Link!");
        ServerModEvents.registerEvents();
    }
}
