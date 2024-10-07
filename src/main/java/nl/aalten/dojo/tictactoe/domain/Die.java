package nl.aalten.dojo.tictactoe.domain;

import java.util.Random;

public class Die {
    private static final Random random = new Random();
    private int value;

    public void throwDie() {
        this.value = random.nextInt(6) + 1;
    }

    public boolean isEven() {
        return this.value % 2 == 0;
    }

}
