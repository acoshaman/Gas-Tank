package pl.mateusznowakowski;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        IsolatedTank tank = new IsolatedTank(0.02);
        ReadWrite readWrite = new ReadWrite();
        readWrite.saveDataInitial(tank);
        tank.addGasToTank("hydrogen", 273, 0.01, 0.5);
        readWrite.saveDataProcess(tank);
        tank.addGasToTank("oxygen", 273, 0.00333, 0.25);
        readWrite.saveDataProcess(tank);
        readWrite.readFile();



    }
}
