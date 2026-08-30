import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    String name = "wef";
    double speed = 12.5;
    double distance = 11.0;


    @Test
    void ConstructorTestHorseNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    void ConstructorTestHorseBlank(String blank) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(blank, speed, distance));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    void ConstructorTestHorseSpeedNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -1.0, distance));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    void ConstructorTestHorseDistanseNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
       String actname = "misha";
        Horse horse = new Horse(actname, speed, distance);
        String name1 = horse.getName();
        assertEquals(actname, name1);
    }

    @Test
    void getSpeed() {
        double actspeed= 2.0;
        Horse horse = new Horse(name, actspeed, distance);
        double speed1 = horse.getSpeed();
        assertEquals(actspeed, speed1);
    }

    @Test
    void getDistanceThreeParam() {
        double actdistance= 2.0;
        Horse horse = new Horse(name, speed, actdistance);
        double distance1 = horse.getDistance();
        assertEquals(actdistance, distance1);
    }
    @Test
    void getDistanceTwooParam() {

        Horse horse = new Horse(name, speed);
        double distance1 = horse.getDistance();
        assertEquals(0, distance1);
    }

    @Test
    void move1() {
        Horse horse = new Horse(name, speed, distance);
       try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
           horse.move();
           mockedStatic.verify(
                   () -> Horse.getRandomDouble(0.2, 0.9)
           );
       }
    }
    @ParameterizedTest
    @CsvSource({"10.0, 5.0, 0.2, 7.0",    // 5.0 + 10.0 * 0.2 = 7.0
               "10.0, 5.0, 0.5, 10.0"     // 5.0 + 10.0 * 0.5 = 10.0)
    })
    void move2(
            double speed,
            double initialDistance,
            double randomValue,
            double expectedDistance
    ) {
        Horse horse = new Horse(name, speed, initialDistance);
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(randomValue);
           horse.move();
            assertEquals(expectedDistance, horse.getDistance(), 0.001);
        }
    }


}