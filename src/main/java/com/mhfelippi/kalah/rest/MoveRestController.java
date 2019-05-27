package com.mhfelippi.kalah.rest;

import com.mhfelippi.kalah.controller.GameController;
import com.mhfelippi.kalah.controller.MoveController;
import com.mhfelippi.kalah.entity.Game;
import com.mhfelippi.kalah.entity.Player;
import com.mhfelippi.kalah.rest.exception.WrongTurnException;
import com.mhfelippi.kalah.rest.pojo.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/games/{gameId}/pits")
public class MoveRestController {

    @Autowired GameController gameController;

    @Autowired
    private MoveController moveController;

    @PutMapping
    @RequestMapping("/{pitId}")
    public ResponseEntity<GameStatus> move(@PathVariable Long gameId, @PathVariable Integer pitId) {
        Game game = this.gameController.getGame(gameId);

        if ((pitId <= game.numberOfPits() && game.getTurn() == Player.PLAYER_2) ||
                (pitId > game.numberOfPits() && game.getTurn() == Player.PLAYER_1)) {
            throw new WrongTurnException(gameId, game.getTurn());
        }

        pitId--;
        if (pitId >= game.numberOfPits()) {
            pitId -= game.numberOfPits();
        }

        this.moveController.move(game, pitId);

        return new ResponseEntity<GameStatus>(new GameStatus(game), HttpStatus.OK);
    }

}
