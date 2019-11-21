package com.webcheckers.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing for PlayerName.java
 * @author Elana Aronson
 */
public class TestPlayerName {
    private PlayerName playerName = new PlayerName();

    /**
     * Test to see if there are special characters
     */
    @Test
    public void test_has_special_char(){
        assertFalse(playerName.hasSpecialChar("player"));
        assertTrue(playerName.hasSpecialChar("Player$"));
        assertTrue(playerName.hasSpecialChar(" "));
        assertFalse(playerName.hasSpecialChar("21"));
    }

    /**
     * Test to see if there is at least one character
     */
    @Test
    public void min_char(){
        assertTrue(playerName.numOfChar(""));
        assertFalse(playerName.numOfChar("2"));
    }

    /**
     * Test to see if there is less than 15 characters
     */
    @Test
    public void max_char(){
        assertTrue(playerName.numOfChar("bobbylovesswen261"));
        assertFalse(playerName.numOfChar("hi"));
    }
}
