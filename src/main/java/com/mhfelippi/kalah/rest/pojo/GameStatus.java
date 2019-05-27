package com.mhfelippi.kalah.rest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mhfelippi.kalah.entity.Game;
import com.mhfelippi.kalah.entity.Player;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameStatus {

    private Long id;
    private String uri;
    private Map<String, String> status;
    private Player nextTurn;

    public GameStatus(Long gameId) {
        this.id = gameId;
        this.uri = ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath("/games/{id}").build(this.id).toString();
    }

    public GameStatus(Game game) {
        this(game.getId());

        List<String> pits = Arrays.stream(game.getPlayer1().getPits()).boxed().map(i -> i.toString()).collect(Collectors.toList());
        pits.addAll(Arrays.stream(game.getPlayer2().getPits()).boxed().map(i -> i.toString()).collect(Collectors.toList()));

        this.status = IntStream.rangeClosed(1, pits.size()).boxed().collect(Collectors.toMap(i -> i.toString(), i -> pits.get(i-1)));
        this.nextTurn = game.getTurn();
    }

    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public Map<String, String> getStatus() {
        return status;
    }

    public Player getNextTurn() {
        return nextTurn;
    }
}
