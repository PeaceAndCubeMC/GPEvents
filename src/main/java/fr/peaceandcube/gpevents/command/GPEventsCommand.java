package fr.peaceandcube.gpevents.command;

import fr.peaceandcube.gpevents.GPEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class GPEventsCommand implements CommandExecutor, TabExecutor {
    private static final String PERM_GPEVENTS = "gpevents.gpevents";
    private static final TextComponent MESSAGE_RELOAD_SUCCESS = Component.text("GPEvents a été rechargé avec succès", TextColor.color(0x55FF55));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(PERM_GPEVENTS)) {
            if (args.length == 1) {
                if (args[0].equals("reload")) {
                    GPEvents.reload();
                    sender.sendMessage(MESSAGE_RELOAD_SUCCESS);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (sender.hasPermission(PERM_GPEVENTS)) {
            if (args.length == 1) {
                return Stream.of("reload").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return List.of();
    }
}
