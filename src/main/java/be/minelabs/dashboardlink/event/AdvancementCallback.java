package be.minelabs.dashboardlink.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;

public interface AdvancementCallback {
    Event<AdvancementCallback> EVENT = EventFactory.createArrayBacked(AdvancementCallback.class,
            (listeners) -> (player, advancement) -> {
                for (AdvancementCallback listener : listeners) {
                    listener.completeAdvancement(player, advancement);
                }
            });

    /**
     * Method that actually happens when a player completes an Advancement
     *
     * @param player      : who completed the Advancement
     * @param advancement : the actual Advancement
     */
    void completeAdvancement(PlayerEntity player, Advancement advancement);
}
