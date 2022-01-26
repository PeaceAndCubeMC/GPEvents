package fr.peaceandcube.gpevents.file;

import fr.peaceandcube.gpevents.data.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class EventsFile extends YamlFile {

    public EventsFile(String name, Plugin plugin) {
        super(name, plugin);
    }

    public List<GPEvent> getEvents() {
        List<GPEvent> events = new ArrayList<>();

        for (String eventName : config.getKeys(false)) {
            ConfigurationSection event = config.getConfigurationSection(eventName);

            if (event != null) {
                GPEventType type = GPEventType.fromName(event.getString("type", null));
                List<Entity> player = parseSelector(event.getString("player", null));
                World world = Bukkit.getWorld(event.getString("world", ""));
                int area = event.getInt("area", -1);
                GPPos greaterPos = getPos(event, "greater_pos", false);
                GPPos lesserPos = getPos(event, "lesser_pos", false);
                List<Entity> claimOwner = parseSelector(event.getString("claim_owner", null));
                boolean isSubdivision = event.getBoolean("is_subdivision", false);
                TrustPermission trustPermission = TrustPermission.fromName(event.getString("trust_permission", null));
                String trustTarget = event.getString("trust_target", null);
                String playerCommand = event.getString("player_command", null);
                GPPos playerPosition = getPos(event, "player_position", true);
                List<String> commands = event.getStringList("commands");

                GPEvent gpEvent = new GPEventBuilder(type).player(player).world(world).area(area)
                        .greaterPos(greaterPos).lesserPos(lesserPos).claimOwner(claimOwner)
                        .subdivision(isSubdivision).trustPermission(trustPermission).trustTarget(trustTarget)
                        .playerCommand(playerCommand).playerPosition(playerPosition).commands(commands).build();
                events.add(gpEvent);
            }
        }

        return events;
    }

    private List<Entity> parseSelector(String selector) {
        if (selector != null) {
            return Bukkit.selectEntities(Bukkit.getConsoleSender(), selector);
        }
        return List.of();
    }

    private GPPos getPos(ConfigurationSection event, String sectionName, boolean includeY) {
        GPPos pos = null;
        ConfigurationSection posSection = event.getConfigurationSection(sectionName);

        if (posSection != null) {
            GPCoord x = getCoord(posSection, "x");
            GPCoord y = null;
            if (includeY) {
                y = getCoord(posSection, "y");
            }
            GPCoord z = getCoord(posSection, "z");
            pos = new GPPos(x, y, z);
        }

        return pos;
    }

    private GPCoord getCoord(ConfigurationSection posSection, String coordName) {
        GPCoord coord;
        ConfigurationSection coordSection = posSection.getConfigurationSection(coordName);
        if (coordSection != null) {
            int min = coordSection.getInt("min");
            int max = coordSection.getInt("max");
            coord = new GPCoord(min, max);
        } else {
            coord = new GPCoord(posSection.getInt(coordName));
        }
        return coord;
    }
}
