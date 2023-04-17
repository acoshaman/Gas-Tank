package pl.mateusznowakowski;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GasTest {
// First test, which check if method dbConnnection correctly read data from database.

    @Test
    void dbConnectorTest() {
        var fluorine = new Gas("helium");
        assertEquals(4.002,fluorine.getMolarNumber());
        assertEquals(12.47, fluorine.getSpecHeatCap());
        assertEquals(1.66, fluorine.getHeatCapRatio());
    }

    @Test
    void dbConnectorTest2() {
        var fluorine = new Gas("helium",10,300,0.1);
        assertEquals(4.002,fluorine.getMolarNumber());
        assertEquals(12.47, fluorine.getSpecHeatCap());
        assertEquals(1.66, fluorine.getHeatCapRatio());
    }

    @Test
    void dbConnectorTestSecondConstructor() {
        var ammonia = new Gas("ammonia", 350, 0.05, 3);
        assertEquals(17.03,ammonia.getMolarNumber());
        assertEquals(28.03, ammonia.getSpecHeatCap());
        assertEquals(1.32, ammonia.getHeatCapRatio());
        assertEquals(174604.5,ammonia.getPressure());
        assertEquals(0.05109, ammonia.getMass());
    }

    @Test
    void isDataCorrectTestNegativeMolarQuantity() {
        Gas gas = new Gas(" ", -1, 300, 0.1);
        assertThrows(NegativeNumberException.class, () -> {
            gas.isDataAreCorrect();});
        }

    @Test
    void isDataCorrectTestNegativeTemperature() {
        Gas gas = new Gas(" ", 1, -1, 0.1);
        assertThrows(NegativeNumberException.class, () -> {
            gas.isDataAreCorrect();});

    }

    @Test
    void isDataCorrectTestNegativeVolume() {
        Gas gas = new Gas(" ", 1, 300, -1);
        assertThrows(NegativeNumberException.class, () -> {
            gas.isDataAreCorrect();});
    }

    @Test
    void isDataCorrectTestNullType() {
        assertThrows(NullPointerException.class,()->
                new Gas(null, 1, -1, 0.1));
    }

    @Test
    void isDataCorrectTestZeroMolarQuantity() {
        Gas gas = new Gas(" ", 0, 300, 0.1);
        assertThrows(NegativeNumberException.class, () -> {
            gas.isDataAreCorrect();
        });
    }
    @Test
    void isDataCorrectTestZeroTemperature() {
        Gas gas = new Gas(" ", 1, 0, 0.1);
        assertThrows(NegativeNumberException.class, () -> {
            gas.isDataAreCorrect();
        });
    }
    @Test
    void isDataCorrectTestZeroVolume() {
        Gas gas = new Gas(" ", 1, 300, 0);
        assertThrows(NegativeNumberException.class, () -> {
            gas.isDataAreCorrect();});
    }


    @Test
    void pressureFromIdealGasEquation() {
        Gas argon = new Gas("argon", 320, 0.2, 3);
        assertEquals(39909.6, argon.pressureFromIdealGasEquation());
    }

    @Test
    void volumeFromIdealGasEquation() {
        Gas argon = new Gas("argon", 320, 0.2, 3);
        argon.pressureFromIdealGasEquation();
        assertEquals(0.2, argon.volumeFromIdealGasEquation());
    }

    @Test
    void molarQuantityFromIdealGasEquation() {
        Gas argon = new Gas("argon", 320, 0.2, 3);
        argon.pressureFromIdealGasEquation();
        assertEquals(3, Math.round(argon.molarQuantityFromIdealGasEquation()));
    }

    @Test
    void massFromMolarParameters() {
        Gas argon = new Gas("argon", 320, 0.2, 3);
        double mass = argon.massFromMolarParameters();
        assertEquals(0.11805, Round.roundToFiveDecimal(mass));

    }
}
