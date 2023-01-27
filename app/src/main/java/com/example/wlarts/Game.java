package com.example.wlarts;

import java.util.ArrayList;

public class Game {

    public static ArrayList<Player> players = new ArrayList<>();
    public static Player current_player;

    public Game() {
    }

    public void enterScore(int points) {
        current_player.enterScore(points);
        nextPlayer();
        displayCurrentPlayer();

    }

    public void revert() {
        Player prev = getPreviousPlayer();
        if (prev.getNumberOfThrows() > 0) {
            selectPreviousPlayer(prev);
            Game.current_player.deleteLastScore();

            displayCurrentPlayer();
        }
    }

    private void displayCurrentPlayer() {
        for (int i = 0; i < Game.players.size(); i++) {

            if (current_player == Game.players.get(i)) {
                Game.players.get(i).setPlayersTurn();
            } else
                Game.players.get(i).setNotPlayersTurn();
        }
    }

    private void selectPreviousPlayer(Player p) {
        Game.current_player = p;
    }

    private Player getPreviousPlayer() {
        int current_id = current_player.getId();
        for (int i = 0; i < players.size(); i++) {

            // Player found in list
            if (players.get(i).getId() == current_id) {
                // last one -> next one is first in list
                if (i == 0)
                    return players.get(players.size() - 1);
                else
                    return players.get(i - 1);

            }
        }

        return null;
    }

    private void nextPlayer() {
        int current_id = current_player.getId();
        for (int i = 0; i < players.size(); i++) {

            // Player found in list
            if (players.get(i).getId() == current_id) {
                // last one -> next one is first in list
                if (i == players.size() - 1)
                    current_player = players.get(0);
                else
                    current_player = players.get(i + 1);
                break;
            }
        }

    }
}
