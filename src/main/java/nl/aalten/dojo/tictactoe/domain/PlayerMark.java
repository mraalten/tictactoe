package nl.aalten.dojo.tictactoe.domain;

public enum PlayerMark {
    X("X"),
    O("O");

    public final String value;

    PlayerMark(String value) {
        this.value = value;
    }
}
