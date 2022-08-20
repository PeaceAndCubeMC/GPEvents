package fr.peaceandcube.gpevents.event;

import fr.peaceandcube.gpevents.GPEvents;
import fr.peaceandcube.gpevents.data.GPCoord;
import fr.peaceandcube.gpevents.data.GPEvent;
import fr.peaceandcube.gpevents.data.GPEventType;
import fr.peaceandcube.gpevents.data.TrustPermission;
import fr.peaceandcube.gpevents.file.EventsFile;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.events.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GPEventListener implements Listener {

    public GPEventListener() {
    }

    @EventHandler
    public void onClaimCreated(ClaimCreatedEvent event) {
        List<GPEvent> createEvents = GPEvents.events.stream().filter(e -> e.type().equals(GPEventType.CREATE)).toList();
        // System.out.println(createEvents);
        for (GPEvent createEvent : createEvents) {
            if (isEqual(createEvent.world(), event.getClaim().getGreaterBoundaryCorner().getWorld())
                    && (createEvent.area() < 0 || createEvent.area() == event.getClaim().getArea())
                    && event.getCreator() instanceof Player player && isInList(createEvent.player(), player.getUniqueId())
                    && (createEvent.greaterPos() == null || isInRange(createEvent.greaterPos().x(), event.getClaim().getGreaterBoundaryCorner().getBlockX()))
                    && (createEvent.greaterPos() == null || isInRange(createEvent.greaterPos().z(), event.getClaim().getGreaterBoundaryCorner().getBlockZ()))
                    && (createEvent.lesserPos() == null || isInRange(createEvent.lesserPos().x(), event.getClaim().getLesserBoundaryCorner().getBlockX()))
                    && (createEvent.lesserPos() == null || isInRange(createEvent.lesserPos().z(), event.getClaim().getLesserBoundaryCorner().getBlockZ()))
                    && isInList(createEvent.claimOwner(), event.getClaim().getOwnerID())
                    && isEqual(createEvent.isSubdivision(), event.getClaim().parent != null))
            {
                for (String command : createEvent.commands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
        }
    }

    @EventHandler
    public void onClaimDeleted(ClaimDeletedEvent event) {
        List<GPEvent> deleteEvents = GPEvents.events.stream().filter(e -> e.type().equals(GPEventType.DELETE)).toList();
        // System.out.println(deleteEvents);
        for (GPEvent deleteEvent : deleteEvents) {
            if (isEqual(deleteEvent.world(), event.getClaim().getGreaterBoundaryCorner().getWorld())
                    && (deleteEvent.area() < 0 || deleteEvent.area() == event.getClaim().getArea())
                    && (deleteEvent.greaterPos() == null || isInRange(deleteEvent.greaterPos().x(), event.getClaim().getGreaterBoundaryCorner().getBlockX()))
                    && (deleteEvent.greaterPos() == null || isInRange(deleteEvent.greaterPos().z(), event.getClaim().getGreaterBoundaryCorner().getBlockZ()))
                    && (deleteEvent.lesserPos() == null || isInRange(deleteEvent.lesserPos().x(), event.getClaim().getLesserBoundaryCorner().getBlockX()))
                    && (deleteEvent.lesserPos() == null || isInRange(deleteEvent.lesserPos().z(), event.getClaim().getLesserBoundaryCorner().getBlockZ()))
                    && isInList(deleteEvent.claimOwner(), event.getClaim().getOwnerID())
                    && isEqual(deleteEvent.isSubdivision(), event.getClaim().parent != null))
            {
                for (String command : deleteEvent.commands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
        }
    }

    @EventHandler
    public void onClaimResized(ClaimResizeEvent event) {
        List<GPEvent> resizeEvents = GPEvents.events.stream().filter(e -> e.type().equals(GPEventType.RESIZE)).toList();
        // System.out.println(resizeEvents);
        for (GPEvent resizeEvent : resizeEvents) {
            if (isEqual(resizeEvent.world(), event.getTo().getGreaterBoundaryCorner().getWorld())
                    && (resizeEvent.area() < 0 || resizeEvent.area() == event.getTo().getArea())
                    && (resizeEvent.greaterPos() == null || isInRange(resizeEvent.greaterPos().x(), event.getTo().getGreaterBoundaryCorner().getBlockX()))
                    && (resizeEvent.greaterPos() == null || isInRange(resizeEvent.greaterPos().z(), event.getTo().getGreaterBoundaryCorner().getBlockZ()))
                    && (resizeEvent.lesserPos() == null || isInRange(resizeEvent.lesserPos().x(), event.getTo().getLesserBoundaryCorner().getBlockX()))
                    && (resizeEvent.lesserPos() == null || isInRange(resizeEvent.lesserPos().z(), event.getTo().getLesserBoundaryCorner().getBlockZ()))
                    && isInList(resizeEvent.claimOwner(), event.getTo().getOwnerID())
                    && isEqual(resizeEvent.isSubdivision(), event.getTo().parent != null))
            {
                for (String command : resizeEvent.commands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
        }
    }

    @EventHandler
    public void onTrustChanged(TrustChangedEvent event) {
        List<GPEvent> trustEvents = GPEvents.events.stream().filter(e -> e.type().equals(GPEventType.TRUST) || e.type().equals(GPEventType.UNTRUST)).toList();
        // System.out.println(trustEvents);
        for (GPEvent trustEvent : trustEvents) {
            List<Claim> claims = new ArrayList<>(event.getClaims());
            if (isEqual(trustEvent.world(), claims.get(0).getGreaterBoundaryCorner().getWorld())
                    && (trustEvent.area() < 0 || trustEvent.area() == claims.get(0).getArea())
                    && (trustEvent.greaterPos() == null || isInRange(trustEvent.greaterPos().x(), claims.get(0).getGreaterBoundaryCorner().getBlockX()))
                    && (trustEvent.greaterPos() == null || isInRange(trustEvent.greaterPos().z(), claims.get(0).getGreaterBoundaryCorner().getBlockZ()))
                    && (trustEvent.lesserPos() == null || isInRange(trustEvent.lesserPos().x(), claims.get(0).getLesserBoundaryCorner().getBlockX()))
                    && (trustEvent.lesserPos() == null || isInRange(trustEvent.lesserPos().z(), claims.get(0).getLesserBoundaryCorner().getBlockZ()))
                    && isInList(trustEvent.claimOwner(), claims.get(0).getOwnerID())
                    && isEqual(trustEvent.isSubdivision(), claims.get(0).parent != null)
                    && (!event.isGiven() || isEqual(trustEvent.trustPermission(), TrustPermission.TRUST_PERMISSIONS.get(event.getClaimPermission())))
                    && isEqual(trustEvent.trustTarget(), event.getIdentifier()))
            {
                if ((event.isGiven() && trustEvent.type().equals(GPEventType.TRUST)) || (!event.isGiven() && trustEvent.type().equals(GPEventType.UNTRUST))) {
                    for (String command : trustEvent.commands()) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        List<GPEvent> commandEvents = GPEvents.events.stream().filter(e -> e.type().equals(GPEventType.COMMAND)).toList();
        // System.out.println(commandEvents);
        for (GPEvent commandEvent : commandEvents) {
            if (isInList(commandEvent.player(), event.getPlayer().getUniqueId())
                    && (commandEvent.playerPosition() == null || isInRange(commandEvent.playerPosition().x(), event.getPlayer().getLocation().getX()))
                    && (commandEvent.playerPosition() == null || isInRange(commandEvent.playerPosition().y(), event.getPlayer().getLocation().getY()))
                    && (commandEvent.playerPosition() == null || isInRange(commandEvent.playerPosition().z(), event.getPlayer().getLocation().getZ()))
                    && isEqual(commandEvent.playerCommand(), event.getMessage()))
            {
                for (String command : commandEvent.commands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
        }
    }

    private boolean isEqual(Object obj1, Object obj2) {
        return obj1 == null || obj1.equals(obj2);
    }

    private boolean isInList(List<Entity> entities, UUID playerUuid) {
        return entities.isEmpty() || entities.stream().map(Entity::getUniqueId).toList().contains(playerUuid);
    }

    private boolean isInRange(GPCoord coord, double value) {
        return coord == null || (value >= coord.min() && value <= coord.max());
    }
}
