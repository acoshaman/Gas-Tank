package pl.mateusznowakowski;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AuxiliaryMethodsForMain {
    Scanner scanner;
    public AuxiliaryMethodsForMain() {
        this.scanner =new Scanner(System.in);
    }
    public double insertVolume() {
        System.out.println("Enter tank's volume\n");
        double volume = Double.parseDouble(scanner.nextLine());
        return volume;
    }
    public double inserVolumeWithExceptionLoop() {
        while (true) {
            try {
                double volume = insertVolume();
                return volume;
            } catch (NumberFormatException nfe) {
                System.out.println("Expected double entrance, for example 2.71");
            }
        }
    }


}
