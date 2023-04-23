package pl.mateusznowakowski;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter tank's volume\n");
        double v = Double.parseDouble(scanner.nextLine());
        IsolatedTank tank = new IsolatedTank(v);
        ReadWrite readWrite = new ReadWrite();
        readWrite.saveDataInitial(tank);

        while (true) {
            System.out.println("");
            System.out.println("1. Add gas to tank.");
            System.out.println("2. Remove gas from tank.");
            System.out.println("3. Display current parameters in tank.");
            System.out.println("4. Display history of all actions.");
            System.out.println("5. End the program.");

            // Variable needed to shut down program.
            int i = 0;

            int choice = scanner.nextInt();
            switch (choice){
                case 1 :{
                    while (true) {
                        try {
                            System.out.println("Enter type, temperature, volume, molar quantity" +
                                    " separated by a space");
                            System.out.println("(if type of gas is multiple, separate words with '_'");
                            double temperature;
                            double volume;
                            double molarQuantity;
                            Scanner scanner1 = new Scanner(System.in);
                            String parameters = scanner1.nextLine();
                            StringTokenizer token = new StringTokenizer(parameters);
                            String type = token.nextToken();
                            temperature = Double.parseDouble(token.nextToken());
                            volume = Double.parseDouble(token.nextToken());
                            molarQuantity = Double.parseDouble(token.nextToken());
                            tank.addGasToTank(type, temperature, volume, molarQuantity);
                            readWrite.saveDataProcess(tank);
                            break;
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid input. Please try again");
                            System.out.println("");
                        } catch (NoSuchElementException nsee) {
                            System.out.println("Invalid input. Please try again");
                            System.out.println("");
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter the mole fraction of the gas in tank which you want to remove: ");
                    String molarFractionString = scanner.nextLine();
                    double molarFractionDouble = Double.parseDouble(molarFractionString);
                    tank.removeGasFromTank(molarFractionDouble);
                    readWrite.saveDataProcess(tank);
                }
                case 3: {
                    tank.displayParameters();
                    break;
                }
                case 4: {
                    readWrite.readFile();
                    break;
                }
                case 5: {
                    i++;
                    break;
                }
            }
            if (i == 1) {
                break;
            }

        }

    }
}
