package com.webcheckers.model;

import java.util.UUID;

/**
 * Model that represents a Game
 */
public class Game {

    public static enum ActiveColor {RED, WHITE}
    public static enum ViewMode {PLAY, SPECTATE, REPLAY}

    private BoardView board;
    private ActiveColor currentColor = ActiveColor.RED;
    private UUID gameID = UUID.randomUUID();

    private String redPlayer;
    private String whitePlayer;

    public Game(String red, String white){
        this.redPlayer = red;
        this.whitePlayer = white;
    }

    public UUID getID() {
        return this.gameID;
    }

    public BoardView getBoard(boolean flipped) {
        return this.board = new BoardView(flipped);
    }

    public ActiveColor getActiveColor() {
        return this.currentColor;
    }
    /**
     * Get the player's opponents name
     * @param currentPlayer the current user to get the opponent of
     */
    public String getOpponentsName(String currentPlayer) {
        return currentPlayer.equals(redPlayer) ? redPlayer : whitePlayer;
    }

}