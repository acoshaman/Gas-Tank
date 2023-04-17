package pl.mateusznowakowski;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IsolatedTankTest {

    @Test
    void temperatureFromIdealGasEquation() {


    }

//    @Test
//    void pressureFromIdealGasEquation() {
//        IsolatedTank argon = new IsolatedTank("argon", 320, 0.2, 3);
//        assertEquals(39909.6, argon.pressureFromIdealGasEquation());
//    }

//    @Test
//    void volumeFromIdealGasEquation() {
//        IsolatedTank argon = new IsolatedTank("argon", 320, 0.2, 3);
//        argon.pressureFromIdealGasEquation();
//        assertEquals(0.2, argon.volumeFromIdealGasEquation());
//    }
//
//    @Test
//    void molarQuantityFromIdealGasEquation() {
//        IsolatedTank argon = new IsolatedTank("argon", 320, 0.2, 3);
//        argon.pressureFromIdealGasEquation();
//        assertEquals(3, Math.round(argon.molarQuantityFromIdealGasEquation()));
//    }
//
//    @Test
//    void massFromMolarParameters() {
//        IsolatedTank tank = new IsolatedTank("argon", 320, 0.2, 3);
//
//        assertEquals(0.11805, Round.roundToFiveDecimal(tank.massFromMolarParameters()));
//
//    }

    @Test
    void totalMolarQuantityTest() {
        IsolatedTank isolatedTank = new IsolatedTank(1);
        isolatedTank.addGasToTank("hydrogen", 300, 0.1, 1);
        isolatedTank.addGasToTank("nitrogen", 200, 0.7, 6);
        isolatedTank.addGasToTank("oxygen", 400, 0.3, 6.5);
        assertEquals(13.5, Round.roundToThreeDecimal(isolatedTank.totalMolarQuantity()));
    }

    @Test
    void totalMolarQuantityTest2() {

        IsolatedTank tank = new IsolatedTank(0.1);
        tank.addGasToTank("carbon dioxide", 300, 0.1, 3);
        tank.addGasToTank("ammonia", 250, 0.1, 5);
        tank.addGasToTank("nitrogen", 250, 0.1, 4);
        assertEquals(12, tank.totalMolarQuantity());
    }

    @Test
    void totalMass() {

        IsolatedTank tank = new IsolatedTank(0.1);
        tank.addGasToTank("carbon dioxide", 300, 0.1, 3);
        tank.addGasToTank("ammonia", 250, 0.1, 5);
        tank.addGasToTank("nitrogen", 250, 0.1, 4);
        double result = Round.roundToThreeDecimal(tank.totalMass());
        assertEquals(0.329, result);
    }

    @Test
    void averageSpecHeatCapTest() {
        IsolatedTank isolatedTank = new IsolatedTank(1);
        isolatedTank.addGasToTank("hydrogen", 300, 0.1, 1);
        isolatedTank.addGasToTank("nitrogen", 200, 0.7, 6);
        isolatedTank.addGasToTank("oxygen", 400, 0.3, 6.5);
        assertEquals(20.837, Round.roundToThreeDecimal(isolatedTank.averageSpecHeatCap()));
    }

    @Test
    void averageSpecHeatCapTest2() {

        IsolatedTank tank = new IsolatedTank(0.1);
        tank.addGasToTank("carbon dioxide", 300, 0.1, 3);
        tank.addGasToTank("ammonia", 250, 0.1, 5);
        tank.addGasToTank("nitrogen", 250, 0.1, 4);
        double result = Round.roundToThreeDecimal(tank.averageSpecHeatCap());
        assertEquals(25.728, result);
    }

    @Test
    void averageHeatCapRatioTest() {
        IsolatedTank isolatedTank = new IsolatedTank(1);
        isolatedTank.addGasToTank("hydrogen", 300, 0.1, 1);
        isolatedTank.addGasToTank("nitrogen", 200, 0.7, 6);
        isolatedTank.addGasToTank("helium", 400, 0.3, 6.5);
        assertEquals(1.525, Round.roundToThreeDecimal(isolatedTank.averageHeatCapRatio()));
    }

    @Test
    void averageHeatCapRatioTest2() {

        IsolatedTank tank = new IsolatedTank(0.1);
        tank.addGasToTank("carbon dioxide", 300, 0.1, 3);
        tank.addGasToTank("ammonia", 250, 0.1, 5);
        tank.addGasToTank("nitrogen", 250, 0.1, 4);
        double result = Round.roundToThreeDecimal(tank.averageHeatCapRatio());
        assertEquals(1.342, result);
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

    @Test
    void evaluateTemperatureWhileMixingTest2() {

        IsolatedTank isolatedTank = new IsolatedTank(1);
        double newTemperature = isolatedTank.evaluateTemperatureWhileMixing(300,
                300, 20.7, 10.4);
        assertEquals(300, Round.roundToZeroDecimal(newTemperature));
    }

    @Test
    void evaluateTemperatureWhileMixingTest3() {

        IsolatedTank isolatedTank = new IsolatedTank(1);
        double newTemperature = isolatedTank.evaluateTemperatureWhileMixing(200,
                300, 28.76, 10.4);
        assertEquals(226.558, Round.roundToThreeDecimal(newTemperature));
    }

    @Test
    void evaluateTemperatureWhileMixingTest4() {

        IsolatedTank isolatedTank = new IsolatedTank(1);
        double newTemperature = isolatedTank.evaluateTemperatureWhileMixing(400,
                300, 28.76, 10.4);
        assertEquals(373.442, Round.roundToThreeDecimal(newTemperature));
    }

    @Test
    void finalPressureAdiabaticConversionTest() {

        IsolatedTank tank = new IsolatedTank(0.1);
        double result = tank.finalPressureAdiabaticConversion(0.2,
                0.1, 100000, 1.4);
        assertEquals(263901.58, Round.roundToTwoDecimal(result));
    }
    @Test
    void finalPressureAdiabaticConversionTest2() {

        IsolatedTank tank = new IsolatedTank(0.1);
        double result = tank.finalPressureAdiabaticConversion(2,
                0.1, 100000, 1.4);
        assertEquals(6628908.03, Round.roundToTwoDecimal(result));
    }

    @Test
    void heatCapTest() {

        IsolatedTank tank = new IsolatedTank(0.1);
        tank.addGasToTank("carbon dioxide", 300, 0.1, 3);
        Gas gas = (Gas) tank.getAllGases().get(0);
        double result = Round.roundToThreeDecimal(tank.heatCap(gas));
        assertEquals(85.38, result);
    }

    @Test
    void summaryHeatCap() {

        IsolatedTank tank = new IsolatedTank(0.1);
        tank.addGasToTank("carbon dioxide", 300, 0.1, 3);
        tank.addGasToTank("ammonia", 250, 0.1, 5);
        tank.addGasToTank("nitrogen", 250, 0.1, 4);
        double result = Round.roundToThreeDecimal(tank.summaryHeatCap(tank.getAllGases()));
        assertEquals(308.73, result);
    }

    @Test
    void heatOfAdiabaticConversionTest() {

        IsolatedTank tank = new IsolatedTank(0.1);
        double result = Round.roundToThreeDecimal(tank.heatOfAdiabaticConversion(2,
                200, 300, 20));
        assertEquals(4000, result);
    }

    @Test
    void averageMolarNumberTest() {

        IsolatedTank tank = new IsolatedTank(0.1);
        tank.addGasToTank("carbon dioxide", 300, 0.1, 3);
        tank.addGasToTank("ammonia", 250, 0.1, 5);
        tank.addGasToTank("nitrogen", 250, 0.1, 4);
        double result = Round.roundToFourDecimal(tank.averageMolarNumber());
        assertEquals(27.4325, result);
    }

    @Test
    void addGasToTankTest() {

        IsolatedTank tank = new IsolatedTank(0.1);
        tank.addGasToTank("carbon monoxide", 300, 0.05, 1);
        tank.addGasToTank("methane", 280, 0.1,2);
        assertEquals(0.06, Round.roundToTwoDecimal(tank.getMass()));
        assertEquals(3, Round.roundToZeroDecimal(tank.getMolarQuantity()));
        assertEquals(20.0033, Round.roundToFourDecimal(tank.getMolarNumber()));
        assertEquals(380.4997, Round.roundToFourDecimal(tank.getTemperature()));
        assertEquals(94909.94, Round.roundToTwoDecimal(tank.getPressure()));
        assertEquals(0.1, Round.roundToOneDecimal(tank.getVolume()));
        assertEquals(24.73, Round.roundToTwoDecimal(tank.getSpecHeatCap()));
        assertEquals(1.34, Round.roundToTwoDecimal(tank.getHeatCapRatio()));
    }
}




