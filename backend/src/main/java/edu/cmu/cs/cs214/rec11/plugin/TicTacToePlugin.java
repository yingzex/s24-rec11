package edu.cmu.cs.cs214.rec11.plugin;

import java.util.*;

import edu.cmu.cs.cs214.rec11.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec11.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec11.games.TicTacToe;
import edu.cmu.cs.cs214.rec11.games.TicTacToe.Player;

/**
 * An example Memory game plug-in.
 */
public class TicTacToePlugin implements GamePlugin<Player> {
    private static final String GAME_NAME = "Tiktactoe";

    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;

    private static final String UNKNOWN_SQUARE_STRING = "?";

    private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                framework.setSquare(i, j, UNKNOWN_SQUARE_STRING); // Initialize the board with a placeholder
            }
        }
        framework.setFooterText("Player X's turn");
    }

    @Override
    public void onNewMove() {

    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        // TicTacToe's move is over after a single play
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        // Update the board
        Player playerAtMove = game.getSquare(x, y);
        framework.setSquare(x, y, playerAtMove.toString());
        if (!game.isOver()) {
            framework.setFooterText("Player " + game.currentPlayer() + "'s turn");
        }
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        Player winner = game.winner();
        if (winner != null) {
            return winner + " won the game!";
        } else {
            return "The game ended in a tie.";
        }
    }

    @Override
    public void onGameClosed() {
    }

    @Override
    public Player currentPlayer() {
        return game.currentPlayer();
    }
}
