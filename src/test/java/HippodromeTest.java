import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {


    @Test
    void ConstructorTestHippodromeNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    void ConstructorTestHippodromeEmpty() {
        List<Horse> horses = new ArrayList<>();
        horses.isEmpty();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {

        List<Horse> originalHorses = new ArrayList<>();

         for (int i = 0; i < 30; i++) {
             originalHorses.add(new Horse(" " + i, i+1, i+2));
         }
        Hippodrome hippodrome = new Hippodrome(originalHorses);
        assertEquals(originalHorses, hippodrome.getHorses());

    }
    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();
                for(Horse horse: horses){
                    verify(horse).move();
                }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("1", 1, 1);
        Horse horse2 = new Horse("2", 5, 6);
        Horse horse3 = new Horse("3", 3, 5);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));

        assertSame(horse2, hippodrome.getWinner());
    }
}