package pl.mateusznowakowski;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        IsolatedTank tank = new IsolatedTank(0.1);
        ReadWrite readWrite = new ReadWrite();
        readWrite.saveDataInitial(tank);
        tank.addGasToTank("carbon monoxide", 300, 0.05, 1);
        readWrite.saveDataProcess(tank);
        tank.addGasToTank("methane", 280, 0.1,2);
        readWrite.saveDataProcess(tank);
        tank.addGasToTank("oxygen", 273, 0.00333, 0.25);
        readWrite.saveDataProcess(tank);
        readWrite.readFile();



    }
}
