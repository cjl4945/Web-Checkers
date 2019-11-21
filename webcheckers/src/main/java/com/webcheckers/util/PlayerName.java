package com.webcheckers.util;

import java.util.regex.Pattern;

public class PlayerName {


    //Maximum number of characters for the username
    public static final int MAX_CHARACTERS=15;

    //Minimum number of characters for the username
    public static final int MIN_CHARACTERS=1;

    /**
     * Checks to see if it has special characters
     * @param playerName The player's name
     * @return true if there are special characters
     */
    public static boolean hasSpecialChar(String playerName) {
        Pattern specialChar = Pattern.compile("[^a-zA-Z0-9]");
        return specialChar.matcher(playerName).find();
    }

    /**
     * If the player's name is greater than or less than the number of characters that is required
     * @param playerName Player's name
     * @return True if the player's name is greater than or less than the number of characters that is required
     */
    public static boolean numOfChar(String playerName){
        return playerName.length()<MIN_CHARACTERS || playerName.length()>MAX_CHARACTERS;
    }


}