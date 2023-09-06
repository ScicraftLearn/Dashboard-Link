package be.minelabs.dashboardlink;

import be.minelabs.dashboardlink.dashboard.Advancements;
import be.minelabs.dashboardlink.dashboard.Dashboard;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Collection;

public class Commands {

    public static void resetCommand(ServerCommandSource source, Collection<? extends Entity> targets) {
        for (Entity entity : targets) {
            PlayerEntity player = (PlayerEntity) entity;
            Dashboard.revokeAdvancements(player.getName().getString());
        }

        if (targets.size() == 1) {
            source.sendFeedback(() ->
                    Text.literal("Reseting progress for " + targets.iterator().next().getDisplayName().getString()), true);
        } else {
            source.sendFeedback(() ->
                    Text.literal("Reseting progress for " + targets.size() + " players"), true);

        }
    }

    public static void reloadCommand(ServerCommandSource source) {
        source.sendFeedback(() -> Text.literal("Reloading Mapping"), true);
        DashboardLink.advancements = new Advancements();
    }
}
