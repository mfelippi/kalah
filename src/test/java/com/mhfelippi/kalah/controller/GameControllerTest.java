package com.mhfelippi.kalah.controller;

import com.mhfelippi.kalah.controller.exception.GameNotFoundException;
import com.mhfelippi.kalah.entity.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameControllerTest {

    @Autowired
    private GameController controller;

    @Test
    public void getGame() {
        Game game = this.controller.createGame();
        Game persistedGame = this.controller.getGame(game.getId());
        assertEquals(game.getId(), persistedGame.getId());
    }

    @Test(expected = GameNotFoundException.class)
    public void getInvalidGame() {
        this.controller.getGame(Long.MAX_VALUE);
    }
}