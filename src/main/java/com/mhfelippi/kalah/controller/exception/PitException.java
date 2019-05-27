package com.mhfelippi.kalah.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PitException extends GameException {

    private final int pit;

    public PitException(Long gameId, int pit, String message) {
        super(gameId, message);
        this.pit = pit;
    }

    public int getPit() {
        return pit;
    }
}
