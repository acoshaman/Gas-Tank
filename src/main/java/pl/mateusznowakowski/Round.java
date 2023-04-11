package pl.mateusznowakowski;

public class Round {
    public static double roundToZeroDecimal(double number){
        double roundedNumber = Math.round(number);
        return  roundedNumber;
    }

    public static double roundToOneDecimal(double number){
        double roundedNumber = Math.round(number * 10);
        roundedNumber = roundedNumber/10;
        return  roundedNumber;
    }
    public static double roundToTwoDecimal(double number){
        double roundedNumber = Math.round(number * 100);
        roundedNumber = roundedNumber/100;
        return  roundedNumber;
    }

    public static double roundToThreeDecimal(double number){
        double roundedNumber = Math.round(number * 1000);
        roundedNumber = roundedNumber/1000;
        return  roundedNumber;
    }

    public static double roundToFourDecimal(double number){
        double roundedNumber = Math.round(number * 10000);
        roundedNumber = roundedNumber/10000;
        return  roundedNumber;
    }
    public static double roundToFiveDecimal(double number){
        double roundedNumber = Math.round(number * 100000);
        roundedNumber = roundedNumber/100000;
        return  roundedNumber;
    }
    public static double roundToSixDecimal(double number){
        double roundedNumber = Math.round(number * 1000000);
        roundedNumber = roundedNumber/1000000;
        return  roundedNumber;
    }
    public static double roundToSevenDecimal(double number){
        double roundedNumber = Math.round(number * 10000000);
        roundedNumber = roundedNumber/10000000;
        return  roundedNumber;
    }

    public static void main(String[] args) {
        System.out.println(roundToThreeDecimal(0.123456));
        double number = Math.round((0.123456*100000));
        number = number/100000;
        System.out.println(number);
    }
}
