package com.mhfelippi.kalah.controller.exception;

import com.mhfelippi.kalah.controller.exception.GameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a game is not found.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Game not found.")
public class GameNotFoundException extends GameException {

    public GameNotFoundException(Long id) {
        super(id);
    }

}
