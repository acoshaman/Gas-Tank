package pl.mateusznowakowski;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IsolatedTankTest {


    @Test
    void extensiveConstructorTest() {
        IsolatedTank neon = new IsolatedTank("neon", 300, 0.1, 2);
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
    @Test
    void temperatureFromIdealGasEquation() {


    }

    @Test
    void pressureFromIdealGasEquation() {
        IsolatedTank argon = new IsolatedTank("argon", 320, 0.2, 3);
        assertEquals(39909.6, argon.pressureFromIdealGasEquation());
    }

    @Test
    void volumeFromIdealGasEquation() {
        IsolatedTank argon = new IsolatedTank("argon", 320, 0.2, 3);
        argon.pressureFromIdealGasEquation();
        assertEquals(0.2, argon.volumeFromIdealGasEquation());
    }

    @Test
    void molarQuantityFromIdealGasEquation() {
        IsolatedTank argon = new IsolatedTank("argon", 320, 0.2, 3);
        argon.pressureFromIdealGasEquation();
        assertEquals(3, Math.round(argon.molarQuantityFromIdealGasEquation()));
    }

    @Test
    void massFromMolarParameters() {
        IsolatedTank tank = new IsolatedTank("argon", 320, 0.2, 3);

        assertEquals(0.11805, Round.roundToFiveDecimal(tank.massFromMolarParameters()));

    }

    @Test
    void totalMolarQuantityTest() {
        IsolatedTank isolatedTank = new IsolatedTank(1);
        isolatedTank.addGasToTank("hydrogen", 300, 0.1, 1 );
        isolatedTank.addGasToTank("nitrogen", 200, 0.7, 6);
        isolatedTank.addGasToTank("oxygen", 400, 0.3, 6.5);
        assertEquals(13.5, Round.roundToThreeDecimal(isolatedTank.totalMolarQuantity()));
    }

    @Test
    void averageSpecHeatCapTest() {
        IsolatedTank isolatedTank = new IsolatedTank(1);
        isolatedTank.addGasToTank("hydrogen", 300, 0.1, 1 );
        isolatedTank.addGasToTank("nitrogen", 200, 0.7, 6);
        isolatedTank.addGasToTank("oxygen", 400, 0.3, 6.5);
        assertEquals(20.837, Round.roundToThreeDecimal(isolatedTank.averageSpecHeatCap()));
    }

    @Test
    void averageHeatCapRatioTest() {
        IsolatedTank isolatedTank = new IsolatedTank(1);
        isolatedTank.addGasToTank("hydrogen", 300, 0.1, 1 );
        isolatedTank.addGasToTank("nitrogen", 200, 0.7, 6);
        isolatedTank.addGasToTank("helium", 400, 0.3, 6.5);
        assertEquals(1.525, Round.roundToThreeDecimal(isolatedTank.averageHeatCapRatio()));
    }

    @Test
    void evaluateTemperatureWhileMixingTest() {
        IsolatedTank isolatedTank = new IsolatedTank(1);
        double newTemperature = isolatedTank.evaluateTemperatureWhileMixing(273,
                300, 15.25, 10.4);
        assertEquals(283.95, Round.roundToTwoDecimal(newTemperature));
    }
    @Test
    void evaluateTemperatureWhileMixingTestWithEmptyTank() {
        IsolatedTank isolatedTank = new IsolatedTank(1);
        double newTemperature = isolatedTank.evaluateTemperatureWhileMixing(0,
                300, 0, 10.4);
        assertEquals(300, Round.roundToZeroDecimal(newTemperature));
    }
}

