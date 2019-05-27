package com.mhfelippi.kalah.controller;

import com.mhfelippi.kalah.controller.exception.EmptyPitException;
import com.mhfelippi.kalah.controller.exception.PitException;
import com.mhfelippi.kalah.entity.Board;
import com.mhfelippi.kalah.entity.Game;
import com.mhfelippi.kalah.entity.Player;
import org.springframework.util.Assert;

import java.util.stream.IntStream;

/**
 * Helper class for moving stones in a game.
 */
class Mover {

    private final Game game;

    private int stones;

    private Player currentBoard;
    private int currentPit;

    Mover(Game game) {
        this.game = game;
    }

    /**
     * Moves stones in a game.
     * @param pit The selected pit.
     */
    void move(int pit) {
        if (pit < 0 || pit >= this.game.numberOfPits() - 1) {
            throw new PitException(this.game.getId(), pit, "Illegal pit.");
        }

        Board board = this.game.getBoard(this.game.getTurn());

        this.stones = board.numberOfStones(pit);
        if (this.stones == 0) {
            throw new PitException(this.game.getId(), pit, "Empty pit.");
        }
        board.setNumberOfStones(pit, 0);

        this.sow(pit);
    }

    /**
     * Start the move by sowing the stones from the pit of the current player.
     * @param pit The pit to pick up the stones.
     */
    private void sow(int pit) {
        this.currentBoard = this.game.getTurn();

        this.sowToSelf(pit + 1);
        while (this.stones > 0) {
            this.currentBoard = this.currentBoard.opponent();
            if (this.currentBoard == this.game.getTurn()) {
                this.sowToSelf(0);
            } else {
                this.sowToOpponent();
            }
        }

        this.endMove();
    }

    /**
     * Sows stones in pits of the current player.
     * @param pit THe pit that will get the first stone.
     */
    private void sowToSelf(int pit) {
        Board board = this.getGame().getBoard();

        // Sows one stone into each pit to the right.
        int[] pits = board.getPits();
        IntStream.range(pit, Integer.min(pit + stones, pits.length)).forEach(i -> pits[i]++);
        board.setPits(pits);

        this.currentPit = Integer.min(pit + this.stones - 1, pits.length - 1);
        this.stones = Integer.max(stones + pit - pits.length, 0);
    }

    /**
     * Sows stones into pits of the opponent. Stones are always sowed from the first pit to the last, excluding the kalah pit.
     */
    private void sowToOpponent() {
        Board board = this.getGame().getOpponentBoard();

        // Kalah from the opponent is not sowed with a stone.
        int numberOfPits = board.numberOfPits() - 1;

        int[] pits = board.getPits();
        IntStream.range(0, Integer.min(this.stones, numberOfPits)).forEach(i -> pits[i]++);
        board.setPits(pits);

        this.stones = Integer.max(stones - numberOfPits, 0 );
    }

    /**
     * Finished the move.
     *
     * <p>If the last stone was placed in the kalah of the current player, he gets another turn. If the last stone was placed in an empty pit of the current player, the stones of the same pit of the opponent are moved to the kalah of the current player along with the stone in the pit of the current player. If the last stone is sowed in any other location, the opponent gets a turn.</p>
     */
    private void endMove() {
        // If the last board sowed is from the player performing the move, checks if the last pit was empty or it was tha kalah.
        if (this.currentBoard.equals(this.game.getTurn())) {
            Board board = this.getGame().getBoard(this.currentBoard);
            if (board.isKalah(this.currentPit)) {
                // Player gets another turn.
                return;
            }
            if (board.numberOfStones(this.currentPit) == 1) {
                this.steal(this.currentPit);
            }
        }

        this.game.setTurn(this.game.getTurn().opponent());
    }

    /**
     * Steals the stones from the pit of the opponent.
     */
    private void steal(int pit) {
        Board self = this.game.getBoard(this.game.getTurn());
        Board opponent = this.game.getBoard(this.game.getTurn().opponent());

        int stones = opponent.numberOfStones(pit);
        if (stones == 0) {
            // If there is no stone in the opponents pit, there is nothing to steal.
            return;
        }

        self.addToKalah(stones + 1);

        self.setNumberOfStones(pit, 0);
        opponent.setNumberOfStones(pit, 0);
    }

    public Game getGame() {
        return game;
    }

}
