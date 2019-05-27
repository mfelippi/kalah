package com.mhfelippi.kalah.controller;

import com.mhfelippi.kalah.controller.exception.GameNotFoundException;
import com.mhfelippi.kalah.entity.Game;
import com.mhfelippi.kalah.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller to manage game entities.
 */
@Controller
public class GameController {

    @Autowired
    private GameRepository repository;

    /**
     * Creates a new game.
     * @return The newly created game.
     */
    public Game createGame() {
        Game game = new Game();
        this.repository.save(game);

        return game;
    }

    /**
     * Gets a game.
     * @param id The id of the game.
     * @return The game.
     * @throws GameNotFoundException If <code>id</code> is not found.
     */
    public Game getGame(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

}
