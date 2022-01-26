package fr.peaceandcube.gpevents.data;

public record GPCoord(int min, int max) {

    public GPCoord(int value) {
        this(value, value);
    }
}
