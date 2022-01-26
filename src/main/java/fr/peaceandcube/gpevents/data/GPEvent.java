package fr.peaceandcube.gpevents.data;

import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.List;

public record GPEvent(GPEventType type, List<Entity> player, World world, int area,
                      GPPos greaterPos, GPPos lesserPos, List<Entity> claimOwner,
                      boolean isSubdivision, TrustPermission trustPermission, String trustTarget,
                      String playerCommand, GPPos playerPosition, List<String> commands) {
}
