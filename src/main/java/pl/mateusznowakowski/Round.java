package pl.mateusznowakowski;

public class Round {

    public static double roundToThreeDecimal(double number){
        double roundedNumber = Math.round(number * 1000)/1000;
        return  roundedNumber;
    }

    public static double roundToFourDecimal(double number){
        double roundedNumber = Math.round(number * 10000)/10000;
        return  roundedNumber;
    }
    public static double roundToFiveDecimal(double number){
        double roundedNumber = Math.round(number * 100000)/100000;
        return  roundedNumber;
    }
    public static double roundToSixDecimal(double number){
        double roundedNumber = Math.round(number * 1000000)/1000000;
        return  roundedNumber;
    }
    public static double roundToSevenDecimal(double number){
        double roundedNumber = Math.round(number * 10000000)/10000000;
        return  roundedNumber;
    }
}
