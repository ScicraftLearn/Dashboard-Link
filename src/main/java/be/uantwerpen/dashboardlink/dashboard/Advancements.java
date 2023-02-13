package be.uantwerpen.dashboardlink.dashboard;

import java.util.Map;

public class Advancements {
    private final Map<String, Long> mapping;

    public Advancements() {
        this.mapping = Dashboard.fetchMapping();
    }

    public Long getAdvancementID(String name) {
        for (Map.Entry<String, Long> pair : mapping.entrySet()) {
            if (pair.getKey().equals(name)) {
                return pair.getValue();
            }
        }
        return null;
    }
}

