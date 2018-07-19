package io.hexlet.xo.model;


public class Game<T> {

    private final Player<T>[] players;

    private final Field<T> field;

    private final String name;

    public Game(final Player<T>[] players,
                final Field<T> field,
                final String name) {
        this.players = players;
        this.field = field;
        this.name = name;
    }

    public Player<T>[] getPlayers() {
        return players;
    }

    public Field<T> getField() {
        return field;
    }

    public String getName() {
        return name;
    }

}
