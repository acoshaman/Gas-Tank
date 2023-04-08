package pl.mateusznowakowski;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    void roundToThreeDecimal() {

        double number1 = 1.43267728734628;
        double number2 = 1.43267728734628;
        double firstDerivative = Math.sin(Math.sqrt(number1)/2);
        double secendDerivative = Math.sin(Math.sqrt(number2/2));
        assertNotEquals(firstDerivative, secendDerivative);
        double roundedFirstDerivative = Round.roundToThreeDecimal(firstDerivative);
        double roundedSecendDerivative = Round.roundToThreeDecimal(secendDerivative);
        assertEquals(roundedFirstDerivative, roundedSecendDerivative);

    }

    @Test
    void roundToFourDecimal() {

        double number1 = 1.43267728734628;
        double number2 = 1.43267728734628;
        double firstDerivative = Math.sin(Math.sqrt(number1)/2);
        double secendDerivative = Math.sin(Math.sqrt(number2/2));
        assertNotEquals(firstDerivative, secendDerivative);
        double roundedFirstDerivative = Round.roundToFourDecimal(firstDerivative);
        double roundedSecendDerivative = Round.roundToFourDecimal(secendDerivative);
        assertEquals(roundedFirstDerivative, roundedSecendDerivative);

    }

    @Test
    void roundToFiveDecimal() {
        double number1 = 1.43267728734628;
        double number2 = 1.43267728734628;
        double firstDerivative = Math.sin(Math.sqrt(number1)/2);
        double secendDerivative = Math.sin(Math.sqrt(number2/2));
        assertNotEquals(firstDerivative, secendDerivative);
        double roundedFirstDerivative = Round.roundToFiveDecimal(firstDerivative);
        double roundedSecendDerivative = Round.roundToFiveDecimal(secendDerivative);
        assertEquals(roundedFirstDerivative, roundedSecendDerivative);

    }

    @Test
    void roundToSixDecimal() {
        double number1 = 1.43267728734628;
        double number2 = 1.43267728734628;
        double firstDerivative = Math.sin(Math.sqrt(number1)/2);
        double secendDerivative = Math.sin(Math.sqrt(number2/2));
        assertNotEquals(firstDerivative, secendDerivative);
        double roundedFirstDerivative = Round.roundToSixDecimal(firstDerivative);
        double roundedSecendDerivative = Round.roundToSixDecimal(secendDerivative);
        assertEquals(roundedFirstDerivative, roundedSecendDerivative);

    }

    @Test
    void roundToSevenDecimal() {
        double number1 = 1.43267728734628;
        double number2 = 1.43267728734628;
        double firstDerivative = Math.sin(Math.sqrt(number1)/2);
        double secendDerivative = Math.sin(Math.sqrt(number2/2));
        assertNotEquals(firstDerivative, secendDerivative);
        double roundedFirstDerivative = Round.roundToSevenDecimal(firstDerivative);
        double roundedSecendDerivative = Round.roundToSevenDecimal(secendDerivative);
        assertEquals(roundedFirstDerivative, roundedSecendDerivative);

    }
}