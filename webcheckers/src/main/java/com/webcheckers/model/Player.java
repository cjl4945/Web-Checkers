package com.webcheckers.model;

import java.util.UUID;

import com.webcheckers.model.Game.ActiveColor;
import com.webcheckers.model.Game.ViewMode;

public class Player{

    private UUID currentGame;
    private ActiveColor currentColor;
    private ViewMode currentViewMode;

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public UUID getCurrentGameID() {
        return this.currentGame;
    }

    public void setCurrentGame(UUID gameID, ActiveColor color, ViewMode viewMode) {
        this.currentGame = gameID;
        this.currentColor = color;
        this.currentViewMode = viewMode;
    }

    public String getName() {
        return this.name;
    }

    public ActiveColor getColor() {
        return this.currentColor;
    }

    public ViewMode getViewMode() {
        return this.currentViewMode;
    }

    public boolean equals(Object p1, Object p2) {
        if ( !(p1 instanceof Player) || !(p2 instanceof Player) ) return false;
        Player p1Player = (Player)p1;
        Player p2Player = (Player)p2;
        return p1Player.getName().equals(p2Player.getName());
    }
}