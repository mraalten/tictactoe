package nl.aalten.dojo.tictactoe.domain;

import nl.aalten.dojo.tictactoe.domain.board.Player;
import nl.aalten.dojo.tictactoe.domain.board.PlayerMark;

public class PlayerTurnSelector {
    public static final Player playerX = new Player(PlayerMark.X);
    public static final Player playerO = new Player(PlayerMark.O);

    private final Die die = new Die();

    public Player determinePlayerToStart() {
        throw new IllegalStateException("Not implemented yet");
    }

}
