package com.mhfelippi.kalah.entity;

public enum Player {
    PLAYER_1, PLAYER_2;

    public Player opponent() {
        switch (this) {
            case PLAYER_1:
                return PLAYER_2;
            case PLAYER_2:
                return PLAYER_1;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case PLAYER_1:
                return "Player 1";
            case PLAYER_2:
                return "Player 2";
        }
        throw new RuntimeException();
    }
}
