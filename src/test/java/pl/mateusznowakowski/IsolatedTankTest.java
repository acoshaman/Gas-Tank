package pl.mateusznowakowski;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IsolatedTankTest {


    @Test
    void dbConnectorTest() {
        IsolatedTank neon = new IsolatedTank("neon", 1, 300, 0.1);
        Gas firstGas = neon.getAllGases().get(0);
        assertEquals(firstGas.getType(), neon.getAllTypes().get(0));
        assertEquals(firstGas.getMass(), neon.getMass());
        assertEquals(firstGas.getPressure(), neon.getPressure());
        assertEquals(firstGas.getTemperature(), neon.getTemperature());
        assertEquals(firstGas.getMolarQuantity(), neon.getMolarQuantity());
        assertEquals(firstGas.getVolume(), neon.getVolume());
        assertEquals(firstGas.getMolarNumber(), neon.getAllMolarNumbers().get(0));
        assertEquals(firstGas.getSpecHeatCap(), neon.getSpecHeatCap());
        assertEquals(firstGas.getHeatCapRatio(), neon.getHeatCapRatio());

    }


}
