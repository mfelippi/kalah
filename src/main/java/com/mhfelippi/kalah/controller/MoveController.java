package com.mhfelippi.kalah.controller;

import com.mhfelippi.kalah.entity.Game;
import com.mhfelippi.kalah.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

@Controller
public class MoveController {

    @Autowired
    private GameRepository gameRepository;

    public void move(@NonNull Game game, int pit) {
        Mover mover = new Mover(game);
        mover.move(pit);

        this.gameRepository.save(game);
    }

}
