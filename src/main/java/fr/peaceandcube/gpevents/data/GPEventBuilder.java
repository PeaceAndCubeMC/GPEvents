package fr.peaceandcube.gpevents.data;

import com.google.common.base.Preconditions;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.List;

public class GPEventBuilder {
    private GPEventType type;
    private List<Entity> player;
    private World world;
    private int area;
    private GPPos greaterPos;
    private GPPos lesserPos;
    private List<Entity> claimOwner;
    private boolean isSubdivision;
    private TrustPermission trustPermission;
    private String trustTarget;
    private String playerCommand;
    private GPPos playerPosition;
    private List<String> commands;

    public GPEventBuilder(GPEventType type) {
        this.type = type;
    }

    public GPEventBuilder player(List<Entity> player) {
        this.player = player;
        return this;
    }

    public GPEventBuilder world(World world) {
        this.world = world;
        return this;
    }

    public GPEventBuilder area(int area) {
        this.area = area;
        return this;
    }

    public GPEventBuilder greaterPos(GPPos greaterPos) {
        this.greaterPos = greaterPos;
        return this;
    }

    public GPEventBuilder lesserPos(GPPos lesserPos) {
        this.lesserPos = lesserPos;
        return this;
    }

    public GPEventBuilder claimOwner(List<Entity> claimOwner) {
        this.claimOwner = claimOwner;
        return this;
    }

    public GPEventBuilder subdivision(boolean isSubdivision) {
        this.isSubdivision = isSubdivision;
        return this;
    }

    public GPEventBuilder trustPermission(TrustPermission trustPermission) {
        this.trustPermission = trustPermission;
        return this;
    }

    public GPEventBuilder trustTarget(String trustTarget) {
        this.trustTarget = trustTarget;
        return this;
    }

    public GPEventBuilder playerCommand(String playerCommand) {
        this.playerCommand = playerCommand;
        return this;
    }

    public GPEventBuilder playerPosition(GPPos playerPosition) {
        this.playerPosition = playerPosition;
        return this;
    }

    public GPEventBuilder commands(List<String> commands) {
        this.commands = commands;
        return this;
    }

    public GPEvent build() {
        Preconditions.checkState(type != null, "GPEvent.type must be specified!");
        return new GPEvent(type, player, world, area, greaterPos, lesserPos, claimOwner, isSubdivision, trustPermission, trustTarget, playerCommand, playerPosition, commands);
    }
}
