package be.uantwerpen.dashboardlink.event;

import be.uantwerpen.dashboardlink.DashboardLink;
import be.uantwerpen.dashboardlink.dashboard.Dashboard;

public class ServerModEvents {

    public static void registerEvents() {
        AdvancementCallback.EVENT.register((player, advancement) -> {
            if (advancement.getId().getNamespace().equals(DashboardLink.MINELABS_ID)) {
                //Only trigger on our own ADVANCEMENTS
                DashboardLink.LOGGER.info("This was a Minelabs Advancement: " + advancement.getId());
                String adv_name = advancement.getId().getPath();
                if (advancement.getId().getPath().contains("/")) {
                    //TODO REMOVE FOLDER STRUCTURE
                    // "basic/decay" -> "decay"
                    adv_name = adv_name.split("/")[1]; // ONLY works with a single folder as depth
                    // ...
                }
                Dashboard.updateAdvancement(DashboardLink.advancements.getAdvancementID(adv_name), player.getEntityName());
            }
        });
    }
}
