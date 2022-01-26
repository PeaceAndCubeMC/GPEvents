package fr.peaceandcube.gpevents.data;

public enum GPEventType {
    CREATE,
    DELETE,
    RESIZE,
    TRUST,
    UNTRUST,
    COMMAND;

    public static GPEventType fromName(String name) {
        if (name != null) {
            for (GPEventType type : GPEventType.values()) {
                if (type.name().toLowerCase().equals(name)) {
                    return type;
                }
            }
        }
        return null;
    }
}
