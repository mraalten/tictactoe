package nl.aalten.dojo.tictactoe.domain;

import static nl.aalten.dojo.tictactoe.domain.PlayerTurnSelector.PLAYER_O;
import static nl.aalten.dojo.tictactoe.domain.PlayerTurnSelector.PLAYER_X;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlayerTurnSelectorTest {

    private static final boolean EVEN = true;
    private static final boolean ODD = false;

    @Test
    void shouldReturnPlayerXWhenValueIsEven() {
        final Die mockedDie = whenDieIsThrownTheValueIs(EVEN);

        final PlayerTurnSelector playerTurnSelector = new PlayerTurnSelector(mockedDie);
        assertThat(playerTurnSelector.determinePlayerToStart()).isEqualTo(PLAYER_X);
    }

    @Test
    void shouldReturnPlayerOWhenValueIsOdd() {
        final Die mockedDie = whenDieIsThrownTheValueIs(ODD);

        final PlayerTurnSelector playerTurnSelector = new PlayerTurnSelector(mockedDie);
        assertThat(playerTurnSelector.determinePlayerToStart()).isEqualTo(PLAYER_O);
    }

    private static Die whenDieIsThrownTheValueIs(boolean isEven) {
        Die mockedDie = Mockito.mock(Die.class);
        when(mockedDie.isEven()).thenReturn(isEven);
        return mockedDie;
    }


}
