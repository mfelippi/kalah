package com.mhfelippi.kalah.rest.exception;

import com.mhfelippi.kalah.controller.exception.GameException;
import com.mhfelippi.kalah.entity.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongTurnException extends GameException {

    private final Player turn;

    public WrongTurnException(Long gameId, Player turn) {
        super(gameId);
        this.turn = turn;
    }

    public Player getTurn() {
        return turn;
    }

    @Override
    public String getMessage() {
        return "Expected "+ turn + " to play next.";
    }
}
