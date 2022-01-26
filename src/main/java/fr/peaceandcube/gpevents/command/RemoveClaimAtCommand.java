package fr.peaceandcube.gpevents.command;

import fr.peaceandcube.gpevents.GPEvents;
import me.ryanhamshire.GriefPrevention.Claim;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class RemoveClaimAtCommand implements CommandExecutor, TabExecutor {
    private static final String PERM_REMOVECLAIMAT = "gpevents.removeclaimat";
    private static final TextComponent MESSAGE_SUCCESS = Component.text("Le claim a bien été supprimé", TextColor.color(0x55FF55));
    private static final TextComponent MESSAGE_WORLD_NOT_FOUND = Component.text("Le monde n'a pas été trouvé !", TextColor.color(0xFF5555));
    private static final TextComponent MESSAGE_CLAIM_NOT_FOUND = Component.text("Aucun claim n'a été trouvé !", TextColor.color(0xFF5555));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(PERM_REMOVECLAIMAT)) {
            if (args.length >= 4 && args.length <= 5) {
                World world = Bukkit.getWorld(args[0]);
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                int z = Integer.parseInt(args[3]);
                boolean checkSubdivisions = args.length >= 5 && Boolean.parseBoolean(args[4]);

                if (world == null) {
                    sender.sendMessage(MESSAGE_WORLD_NOT_FOUND);
                    return true;
                }

                Location location = new Location(world, x, y, z);
                Claim claim = GPEvents.getGriefPrevention().dataStore.getClaimAt(location, true, !checkSubdivisions, null);

                if (claim == null) {
                    sender.sendMessage(MESSAGE_CLAIM_NOT_FOUND);
                    return true;
                }

                GPEvents.getGriefPrevention().dataStore.deleteClaim(claim);
                sender.sendMessage(MESSAGE_SUCCESS);
                return true;
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (sender.hasPermission(PERM_REMOVECLAIMAT)) {
            if (args.length == 1) {
                return Bukkit.getWorlds().stream().map(WorldInfo::getName).filter(s -> s.startsWith(args[0])).toList();
            } else if (args.length == 2) {
                return Stream.of("0").filter(s -> s.startsWith(args[1])).toList();
            } else if (args.length == 3) {
                return Stream.of("0").filter(s -> s.startsWith(args[2])).toList();
            } else if (args.length == 4) {
                return Stream.of("0").filter(s -> s.startsWith(args[3])).toList();
            } else if (args.length == 5) {
                return Stream.of("false", "true").filter(s -> s.startsWith(args[4])).toList();
            }
        }
        return List.of();
    }
}
