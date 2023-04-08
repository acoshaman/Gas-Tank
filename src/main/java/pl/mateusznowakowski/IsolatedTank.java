package pl.mateusznowakowski;

import java.util.ArrayList;
import java.util.List;

public class IsolatedTank {
    private double volume;

    private double temperature;

    private double pressure;

    private double molarQuantity;

    private double mass;

    private double specHeatCap;

    private double heatCapRatio;

    private final double gasConstant = 8.3145;

    private ArrayList<String> AllTypes = new ArrayList<String>();
    private ArrayList<Gas> AllGases = new ArrayList<Gas>();

    // Primitive constructor
    public IsolatedTank() {
    }

    public IsolatedTank(String type, double molarQuantity, double temperature, double volume) {
        Gas firstGas = new Gas(type, molarQuantity, temperature, volume);
        AllGases.add(firstGas);
        AllTypes.add(firstGas.getType());
        this.volume = firstGas.getVolume();
        this.temperature = firstGas.getTemperature();
        this.pressure = firstGas.getPressure();
        this.molarQuantity = firstGas.getMolarQuantity();
        this.mass = firstGas.getMass();
        this.specHeatCap = firstGas.getSpecHeatCap();
        this.heatCapRatio = firstGas.getHeatCapRatio();

    }



}
