package be.uantwerpen.dashboardlink.dashboard;

import java.util.Map;

public class Advancements {
    private final Map<String, Long> mapping;

    public Advancements() {
        this.mapping = Dashboard.fetchMapping();
    }

    /**
     * Get the Advancements ID with it's minecraft name
     *
     * @param name : Minecraft name, without subfolder
     * @return Long, the ID
     */
    public Long getAdvancementID(String name) {
        for (Map.Entry<String, Long> pair : mapping.entrySet()) {
            if (pair.getKey().equals(name)) {
                return pair.getValue();
            }
        }
        return null;
    }
}

