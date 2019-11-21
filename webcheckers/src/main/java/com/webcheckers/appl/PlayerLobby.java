package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Application for player
 *
 * @author Elana Aronson
 */
public class PlayerLobby {

    //list of players that is signed in
    private List<Player> listOfPlayers = new ArrayList<>();

    //constuctor for playerName
    public PlayerLobby(){}

    /**
     * Adding a player that is signed in into the list
     * @param player The player that is signing in
     */
    public void addPlayer(Player player){
        listOfPlayers.add(player);
    }

    /**
     * If the player's name exists
     * @param candidateName The player's name
     * @return If it does exist, return true otherwise false
     */
    public Player getPlayerFromName(String candidateName){
        for (Player player: listOfPlayers) {
            System.out.println("Player name "+ player.getName());
            if (player.getName().equals(candidateName)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Creating a new player
     * @param player The player that is signing in to be created in the game
     * @return The created player
     */
    public Player signInPlayer(String player){
        Player player1 = new Player(player);
        listOfPlayers.add(player1);
        return player1;
    }

    /**
     * The size of the list of players that is online
     * @return The number of players that is onlne
     */
    public int totalSignedInPlayers(){
        return this.listOfPlayers.size();
    }

    /**
     * The list of players
     * @param myPlayer The user
     * @return A list of players
     */
    public ArrayList<String> signedInPlayers(Player myPlayer) {

        ArrayList<String> names = new ArrayList<String>();
        for (Player player : listOfPlayers) {
            names.add(player.getName());
        }
        names.remove(myPlayer.getName());
        return names;
    }

    /**
     * Removes the player off the list once it signs out
     * @param player Player's name
     */
    public void removePlayer(Player player){
        listOfPlayers.remove(player);
    }

    /**
     * The list of players that is signed on is empty
     * @return True if the list is empty
     */
    public boolean emptyList() {
        return listOfPlayers.isEmpty();
    }
}
