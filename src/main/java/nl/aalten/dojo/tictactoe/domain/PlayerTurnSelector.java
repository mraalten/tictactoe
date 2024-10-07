package nl.aalten.dojo.tictactoe.domain;

import nl.aalten.dojo.tictactoe.domain.board.Player;
import nl.aalten.dojo.tictactoe.domain.board.PlayerMark;

public class PlayerTurnSelector {
    public static final Player PLAYER_X = new Player(PlayerMark.X);
    public static final Player PLAYER_O = new Player(PlayerMark.O);

    private final Die die;
    public PlayerTurnSelector(Die die) {
        this.die = die;
    }

    public Player determinePlayerToStart() {
        die.throwDie();
        if (die.isEven()) {
            return PLAYER_X;
        }
        return PLAYER_O;
    }

}
