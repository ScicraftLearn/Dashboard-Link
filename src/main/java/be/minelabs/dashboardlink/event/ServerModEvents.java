package be.minelabs.dashboardlink.event;

import be.minelabs.dashboardlink.Commands;
import be.minelabs.dashboardlink.DashboardLink;
import be.minelabs.dashboardlink.dashboard.Dashboard;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ServerModEvents {

    public static void registerEvents() {
        AdvancementCallback.EVENT.register((player, advancement) -> {
            if (advancement.getId().getNamespace().equals(DashboardLink.MINELABS_ID)) {
                //Only trigger on our own ADVANCEMENTS
                String adv_name = advancement.getId().getPath();
                if (advancement.getId().getPath().contains("/")) {
                    String[] split = adv_name.split("/");
                    adv_name = split[split.length-1];
                }
                Dashboard.updateAdvancement(DashboardLink.advancements.getAdvancementID(adv_name), player.getEntityName());
            }
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("link").requires(source -> source.hasPermissionLevel(4))
                    .then(literal("reset").then(argument("who", EntityArgumentType.entities()).executes(context -> {
                        Commands.resetCommand(context.getSource(), EntityArgumentType.getEntities(context, "who"));
                        return 1;
                    })).executes(context -> { // No arugment (@a, @p)/
                        Commands.resetCommand(context.getSource(), ImmutableList.of(context.getSource().getEntityOrThrow()));
                        return 1;
                    })
            ).then(literal("reload") .executes(context -> {
                //Register reload command
                Commands.reloadCommand(context.getSource());
                return 1;
            })));
        });
    }
}
