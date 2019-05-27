package com.mhfelippi.kalah.rest;

import antlr.collections.impl.IntRange;
import com.mhfelippi.kalah.controller.GameController;
import com.mhfelippi.kalah.entity.Board;
import com.mhfelippi.kalah.entity.Game;
import com.mhfelippi.kalah.repository.GameRepository;
import com.mhfelippi.kalah.rest.pojo.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/games")
public class GameRestController {

    @Autowired
    private GameController controller;

    @PutMapping
    public ResponseEntity<GameStatus> getGame() {
        Game game = this.controller.createGame();

        return new ResponseEntity<GameStatus>(new GameStatus(game.getId()), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<GameStatus> getGame(@PathVariable Long id) {
        return new ResponseEntity<GameStatus>(new GameStatus(this.controller.getGame(id)), HttpStatus.OK);
    }

    private String buildUri(Object... path) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        for (Object p : path) {
            builder.path(p.toString());
        }
        return builder.build().toUriString();
    }

}
