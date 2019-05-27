package com.mhfelippi.kalah.rest;

import com.mhfelippi.kalah.controller.GameController;
import com.mhfelippi.kalah.entity.Game;
import com.mhfelippi.kalah.entity.Player;
import com.mhfelippi.kalah.rest.exception.WrongTurnException;
import com.mhfelippi.kalah.rest.pojo.GameStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoveRestControllerTest {

    @Autowired
    private GameController gameController;

    @Autowired MoveRestController moveRestController;

    private Long gameId;

    @Before
    public void setUp() {
        this.gameId = this.gameController.createGame().getId();
    }

    @Test(expected = WrongTurnException.class)
    public void move() {
        ResponseEntity<GameStatus> response = this.moveRestController.move(this.gameId, 2);

        GameStatus expected = new GameStatus(this.gameController.getGame(this.gameId));
        GameStatus actual = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNextTurn(), actual.getNextTurn());
        assertEquals(expected.getStatus(), actual.getStatus());

        response = this.moveRestController.move(this.gameId, 9);

        expected = new GameStatus(this.gameController.getGame(this.gameId));
        actual = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNextTurn(), actual.getNextTurn());
        assertEquals(expected.getStatus(), actual.getStatus());

        response = this.moveRestController.move(this.gameId, 9);
    }
}