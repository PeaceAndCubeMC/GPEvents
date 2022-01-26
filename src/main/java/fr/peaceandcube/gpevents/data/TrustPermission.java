package fr.peaceandcube.gpevents.data;

import me.ryanhamshire.GriefPrevention.ClaimPermission;

import java.util.Map;

public enum TrustPermission {
    BUILD,
    CONTAINER,
    ACCESS;

    public static final Map<ClaimPermission, TrustPermission> TRUST_PERMISSIONS = Map.of(
            ClaimPermission.Build, BUILD,
            ClaimPermission.Inventory, CONTAINER,
            ClaimPermission.Access, ACCESS
    );

    public static TrustPermission fromName(String name) {
        if (name != null) {
            for (TrustPermission type : TrustPermission.values()) {
                if (type.name().toLowerCase().equals(name)) {
                    return type;
                }
            }
        }
        return null;
    }
}
