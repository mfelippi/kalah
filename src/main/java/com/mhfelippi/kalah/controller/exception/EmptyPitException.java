package com.mhfelippi.kalah.controller.exception;

import com.mhfelippi.kalah.controller.exception.GameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a player tries to perform a move in an empty pit.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmptyPitException extends GameException {

    private int pit;

    /**
     * Creates a new exception/
     * @param gameId The id of the game.
     * @param pit The number of the empty pit.
     */
    EmptyPitException(Long gameId, int pit) {
        super(gameId);
        this.pit = pit;
    }

    /**
     * Gets the number of the pit.
     * @return The number of the pit.
     */
    public int getPit() {
        return pit;
    }

    @Override
    public String toString() {
        return "Pit is empty";
    }
}
