package pl.mateusznowakowski;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWrite {
    private int countSaves = 1;
    public String fileName;


    public ReadWrite() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of file which will contains history of whole process:\n" + "");
        this.fileName = scanner.nextLine();
    }
    protected String createTypeList(ArrayList arrayList) {
        String stringList = "(";
        for (int i = 0; i <arrayList.size() ; i++) {
            stringList += arrayList.get(i) + ", ";
        }
        stringList += ")";
        return stringList;
    }
    public void saveDataInitial(IsolatedTank tank) throws IOException {
        PrintWriter file = null;
        try {
            // Gas parameters in tank
            ArrayList<String> listOfTypes = tank.getAllTypes();
            String compounds = createTypeList(listOfTypes);

            file = new PrintWriter(new FileWriter(fileName, true));
            file.println("***  Initial tank state  ***\n\nGas parameters in tank:\n  - compounds: " +
                    compounds + "\n  - mass[kg]: " + tank.getMass() + "\n  - molar quantity[mol]: " +
                    "" + tank.getMolarQuantity() + "\n  - molar number[kg/mol]: " + (tank.getMolarNumber() / 1000) +
                    "\n  - temperature[K]: " + tank.getTemperature() + "\n  - pressure[Pa}: " + tank.getPressure() + "\n" +
                    "  - volume[m^3]: " + tank.getVolume() + "\n  - heat capacity[J/(mol*K)]: " + tank.getSpecHeatCap() + "\n" +
                    "  - heat capacity ratio[1]: " + tank.getHeatCapRatio() + "\n\n" +
                    "----------------------------------------------------\n----------------------------------------------------\n");

        } finally {
            if (file != null) {
                file.close();
            }
        }
    }
    public void saveDataProcess(IsolatedTank tank) throws IOException {
        PrintWriter file = null;
        try {
            // Gas parameters in cylinder
            ArrayList<Gas> listOfGases = tank.getAllGases();
            ArrayList<String> listOfTypes = tank.getAllTypes();
            Gas gas = listOfGases.get(listOfGases.size() - 1);
            String compounds = createTypeList(listOfTypes);

            file = new PrintWriter(new FileWriter(fileName, true));
            file.println("***  Process No. "+ countSaves +"  ***\n\n" +"Gas parameters in cylinder:\n" +
                    "  - type: " + gas.getType() + "\n  - mass[kg]: " + gas.getMass() + "\n  - molar quantity[mol]: " +
                    "" + gas.getMolarQuantity() + "\n  - molar number[kg/mol]: " + (gas.getMolarNumber() / 1000) +
                    "\n  - temperature[K]: " + gas.getTemperature() + "\n  - pressure[Pa}: " + gas.getPressure() + "\n" +
                    "  - volume[m^3]: " + gas.getVolume() + "\n  - heat capacity[J/(mol*K)]: " + gas.getSpecHeatCap() + "\n" +
                    "  - heat capacity ratio[1]: " + gas.getHeatCapRatio() + "\n\nGas parameters in cylinder-tank system after diffusion:" +
                    "\n  - compounds: " + compounds + "\n  - mass[kg]: " + tank.getMass() + "\n  - molar quantity[mol]: " +
                    "" + tank.getMolarQuantity() + "\n  - molar number[kg/mol]: " + (tank.getMolarNumber() / 1000) +
                    "\n  - temperature[K]: " + tank.getTemporaryTemperature() + "\n  - pressure[Pa}: " + tank.getTemporaryPressure() + "\n" +
                    "  - volume[m^3]: " + tank.getTemporaryVolume() + "\n  - heat capacity[J/(mol*K)]: " + tank.getSpecHeatCap() + "\n" +
                    "  - heat capacity ratio[1]: " + tank.getHeatCapRatio()
                    + "\n\nGas parameters in tank after compression:\n  - compounds: " +
                    compounds + "\n  - mass[kg]: " + tank.getMass() + "\n  - molar quantity[mol]: " +
                    "" + tank.getMolarQuantity() + "\n  - molar number[kg/mol]: " + (tank.getMolarNumber() / 1000) +
                    "\n  - temperature[K]: " + tank.getTemperature() + "\n  - pressure[Pa}: " + tank.getPressure() + "\n" +
                    "  - volume[m^3]: " + tank.getVolume() + "\n  - heat capacity[J/(mol*K)]: " + tank.getSpecHeatCap() + "\n" +
                    "  - heat capacity ratio[1]: " + tank.getHeatCapRatio() + "\n\n" + "COMPRESION WORK[J}: " +tank.getCompresionWork()
                    + "\n\n" + "TOTAL WORK[J}: " +tank.getTotalWork() + "\n\n" +
                    "----------------------------------------------------\n----------------------------------------------------\n");
            countSaves +=1;

        } finally {
            if (file != null) {
                file.close();
            }
        }
    }
    public void readFile() throws IOException {

        // odczyt wiersz po wierszu
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(fileName));
            System.out.println("\n\n--- ***  Whole process history  ***---\n");
            String l = file.readLine();
            while (l != null) {
                System.out.println(l);
                l = file.readLine();
            }
        } finally {
            if (file != null) {
                file.close();
            }
        }
    }
}
