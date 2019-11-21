package com.webcheckers.appl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import com.webcheckers.model.*;
import com.webcheckers.model.Game.ActiveColor;
import com.webcheckers.model.Game.ViewMode;

public class GameCenter {

    private Map<UUID, Game> gamesInPlay = new HashMap<>();


    public GameCenter(){}

    public Game getCurrentGame(Player player) {
        try {
            UUID gameID = player.getCurrentGameID();
            return gamesInPlay.get(gameID);
        } catch(Exception e) {
            return null;
        }  
    }

    public Game createNewGame(Player startingPlayer, Player opponent) {
        Game newGame = new Game(startingPlayer.getName(), opponent.getName());
        UUID newGameID = newGame.getID();
        gamesInPlay.put(newGameID, newGame);

        startingPlayer.setCurrentGame(newGameID, ActiveColor.RED, ViewMode.PLAY);
        opponent.setCurrentGame(newGameID, ActiveColor.WHITE, ViewMode.PLAY);
        return newGame;
    }

    

}