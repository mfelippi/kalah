package com.mhfelippi.kalah.controller.exception;

/**
 * Base exception for all game related exception.
 */
public abstract class GameException extends RuntimeException {

    private Long id;

    protected GameException(Long id) {
        this.id = id;
    }

    protected GameException(Long id, String message) {
        super(message);
        this.id = id;
    }

    /**
     * The id of the game responsible for creating the exception.
     * @return The id of the game.
     */
    public Long getId() {
        return id;
    }
}
