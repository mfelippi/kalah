package com.mhfelippi.kalah.entity;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Board player1 = new Board();

    @OneToOne(cascade = CascadeType.ALL)
    private Board player2 = new Board();

    private Player turn = Player.PLAYER_1;

    /**
     * Gets the id of the game.
     * @return The id of the game.
     */
    public Long getId() {
        return id;
    }

    public Board getPlayer1() {
        return player1;
    }

    public Board getPlayer2() {
        return player2;
    }

    /**
     * Gets who is the next player to play.
     * @return The next player to play.
     */
    public Player getTurn() {
        return turn;
    }

    /**
     * Sets the next player to play.
     * @param turn The next player to play.
     */
    public void setTurn(Player turn) {
        this.turn = turn;
    }

    /**
     * Gets the number of pits available in this game, including the kalah pit.
     * @return The number of pits in this game.
     */
    public int numberOfPits() {
        return this.getPlayer1().numberOfPits();
    }

    /**
     * Gets the board of the current player.
     * @return The board of the current player.
     */
    public Board getBoard() {
        return this.getBoard(this.getTurn());
    }

    /**
     * Gets the board of the opponent.
     * @return The board of the opponent.
     */
    public Board getOpponentBoard() {
        return this.getBoard(this.getTurn().opponent());
    }

    public Board getBoard(Player player) {
        switch (player) {
            case PLAYER_1:
                return this.getPlayer1();
            case PLAYER_2:
                return this.getPlayer2();
            default:
                throw new IllegalArgumentException();
        }
    }

}
