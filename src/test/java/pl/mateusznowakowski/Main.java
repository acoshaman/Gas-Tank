package pl.mateusznowakowski;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        IsolatedTank tank = new IsolatedTank(0.02);
        tank.addGasToTank("hydrogen", 273, 0.01, 0.5);

        tank.addGasToTank("oxygen", 273, 0.00333, 0.25);
        tank.addGasToTank("nitrogen", 300, 0.01, 0.5);
        tank.displayParameters();
        tank.saveData("HistoryOfProcess");
    }
}
