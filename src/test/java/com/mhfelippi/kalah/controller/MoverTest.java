package com.mhfelippi.kalah.controller;

import com.mhfelippi.kalah.controller.exception.PitException;
import com.mhfelippi.kalah.entity.Game;
import com.mhfelippi.kalah.entity.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoverTest {

    @Test
    public void move() {
        Game game = new Game();
        Mover mover = new Mover(game);

        mover.move(0);

        assertArrayEquals(new int[]{0,7,7,7,7,7,1}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{6,6,6,6,6,6,0}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_1, game.getTurn());

        mover.move(5);
        assertArrayEquals(new int[]{0,7,7,7,7,0,2}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{7,7,7,7,7,7,0}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_2, game.getTurn());

        mover.move(2);
        assertArrayEquals(new int[]{1,8,8,7,7,0,2}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{7,7,0,8,8,8,1}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_1, game.getTurn());

        mover.move(4);
        assertArrayEquals(new int[]{1,8,8,7,0,1,3}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{8,8,1,9,9,8,1}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_2, game.getTurn());

        mover.move(0);
        assertArrayEquals(new int[]{2,9,8,7,0,1,3}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{0,9,2,10,10,9,2}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_1, game.getTurn());

        mover.move(0);
        assertArrayEquals(new int[]{0,10,9,7,0,1,3}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{0,9,2,10,10,9,2}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_2, game.getTurn());

        mover.move(5);
        assertArrayEquals(new int[]{1,11,10,8,1,2,3}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{1,10,2,10,10,0,3}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_1, game.getTurn());

        mover.move(5);
        assertArrayEquals(new int[]{1,11,10,8,1,0,4}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{2,10,2,10,10,0,3}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_2, game.getTurn());

        mover.move(2);
        assertArrayEquals(new int[]{1,11,10,8,1,0,4}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{2,10,0,11,11,0,3}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_1, game.getTurn());

        mover.move(4);
        assertArrayEquals(new int[]{1,11,10,8,0,1,4}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{2,10,0,11,11,0,3}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_2, game.getTurn());

        mover.move(0);
        assertArrayEquals(new int[]{1,11,0,8,0,1,4}, game.getPlayer1().getPits());
        assertArrayEquals(new int[]{0,11,0,11,11,0,14}, game.getPlayer2().getPits());
        assertEquals(Player.PLAYER_1, game.getTurn());
    }

    @Test(expected = PitException.class)
    public void moveWithoutStones() {
        Game game = new Game();
        game.getPlayer1().setNumberOfStones(0, 0);

        new Mover(game).move(0);
    }

    @Test(expected = PitException.class)
    public void moveInvalid() {
        Game game = new Game();

        new Mover(game).move(6);
    }

}