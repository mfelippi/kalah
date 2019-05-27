package com.mhfelippi.kalah.entity;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * A board contains pits information of a player.
 */
@Entity
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private int[] pits;

    public Board() { this(6, 6); }

    Board(int pits, int stones) {
        this.pits = new int[pits + 1];

        // Fills all pits with stones.
        Arrays.fill(this.pits, stones);

        // Empties the kalah pit.
        this.pits[pits] = 0;
    }

    /**
     * The id of the board.
     * @return The if of the board.
     */
    public Long getId() {
        return id;
    }

    /**
     * The number of stones in each pit. The last pit is the kalah.
     * @return The number of stones in each pit. The returned array is a copy of the pits.
     */
    public int[] getPits() {
        return Arrays.copyOf(this.pits, this.pits.length);
    }

    /**
     * Sets the number of stones in each pit. The last pit is the kalah.
     * @param pits The number of stones in each pit. The array is copied to the board.
     * @throws IllegalArgumentException If the number of pits is different.
     */
    public void setPits(int[] pits) {
        Assert.isTrue(this.pits.length == pits.length, "Changing number of pits in a board is not possible.");
        this.pits = Arrays.copyOf(pits, pits.length);
    }

    /**
     * Gets the number of stones in a pit.
     * @param pit The pit.
     * @return The number of stones in <code>pit</code>.
     */
    public int numberOfStones(int pit) {
        return this.pits[pit];
    }

    /**
     * Sets the number of stones in a pit.
     * @param pit The pit.
     * @param stones The number of stones.
     */
    public void setNumberOfStones(int pit, int stones) {
        this.pits[pit] = stones;
    }

    /**
     * Gets the number of pits in this board. The number of pits includes the kalah.
     * @return The number of pits.
     */
    public int numberOfPits() {
        return this.pits.length;
    }

    /**
     * Gets the number of the pit used as kalah.
     * @return The number of the pit.
     */
    public int getKalahPit() {
        return this.pits.length - 1;
    }

    /**
     * Checks if <code>pit</code> is the kalah.
     * @param pit The pit.
     * @return If the pit is the kalah, <code>true</code>. Otherwise <code>false</code>.
     */
    public boolean isKalah(int pit) {
        return (pit == this.pits.length - 1);
    }

    /**
     * Checks if <code>pit</code> is empty.
     * @param pit The pit.
     * @return If the pit is empty, <code>true</code>. Otherwise <code>false</code>.
     */
    public boolean isEmpty(int pit) {
        return this.pits[pit] == 0;
    }

    public void addToKalah(int stones) {
        int pit = this.getKalahPit();
        this.pits[pit] += stones;
    }

    public int numberOfStonesInKalah() {
        return this.numberOfStones(this.getKalahPit());
    }

}
