package pl.mateusznowakowski;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IsolatedTankTest {


    @Test
    void extensiveConstructorTest() {
        IsolatedTank neon = new IsolatedTank("neon", 1, 300, 0.1);
        Gas firstGas = neon.getAllGases().get(0);
        assertEquals(firstGas.getType(), neon.getAllTypes().get(0));
        assertEquals(firstGas.getMass(), neon.getTotalMass());
        assertEquals(firstGas.getPressure(), neon.getTotalPressure());
        assertEquals(firstGas.getTemperature(), neon.getTotalTemperature());
        assertEquals(firstGas.getMolarQuantity(), neon.getTotalMolarQuantity());
        assertEquals(firstGas.getVolume(), neon.getTotalVolume());
        assertEquals(firstGas.getMolarNumber(), neon.getAllMolarNumbers().get(0));
        assertEquals(firstGas.getSpecHeatCap(), neon.getTotalSpecHeatCap());
        assertEquals(firstGas.getHeatCapRatio(), neon.getTotalHeatCapRatio());

    }


    @Test
    void temperatureFromIdealGasEquation() {

    }

    @Test
    void pressureFromIdealGasEquation() {
    }

    @Test
    void volumeFromIdealGasEquation() {
    }

    @Test
    void molarQuantityFromIdealGasEquation() {
    }
}
