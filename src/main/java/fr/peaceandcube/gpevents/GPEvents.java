package fr.peaceandcube.gpevents;

import fr.peaceandcube.gpevents.command.GPEventsCommand;
import fr.peaceandcube.gpevents.command.RemoveClaimAtCommand;
import fr.peaceandcube.gpevents.data.GPEvent;
import fr.peaceandcube.gpevents.event.GPEventListener;
import fr.peaceandcube.gpevents.file.EventsFile;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class GPEvents extends JavaPlugin {
    private static GriefPrevention griefPrevention;
    public static EventsFile eventsFile;
    public static List<GPEvent> events;

    @Override
    public void onEnable() {
        griefPrevention = GriefPrevention.getPlugin(GriefPrevention.class);

        this.getCommand("gpevents").setExecutor(new GPEventsCommand());
        this.getCommand("removeclaimat").setExecutor(new RemoveClaimAtCommand());

        eventsFile = new EventsFile("events.yml", this);
        events = eventsFile.getEvents();

        this.getServer().getPluginManager().registerEvents(new GPEventListener(), this);
    }

    public static void reload() {
        eventsFile.reload();
        events = eventsFile.getEvents();
    }

    public static GriefPrevention getGriefPrevention() {
        return griefPrevention;
    }
}
