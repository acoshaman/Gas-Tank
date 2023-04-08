package pl.mateusznowakowski;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class IsolatedTank {
    private double volume ;

    private double temperature;

    private double pressure;

    private double molarQuantity;

    private double mass;

    private double specHeatCap;

    private double heatCapRatio;

    private final double gasConstant = 8.3145;

    private ArrayList<String> AllTypes = new ArrayList<String>();

    private ArrayList<Double> AllMolarNumbers = new ArrayList<Double>();

    private ArrayList<Gas> AllGases = new ArrayList<Gas>();

    // Primitive constructor
    public IsolatedTank(double volume) {
        this.volume = volume;
        this.temperature = 0;
        this.pressure = 0;
        this.molarQuantity = 0;
        this.mass =0;
        this.specHeatCap = 0;
        this.heatCapRatio = 0;

    }

    public IsolatedTank(String type, double molarQuantity, double temperature, double volume) {
        Gas firstGas = new Gas(type, molarQuantity, temperature, volume);
        AllGases.add(firstGas);
        AllTypes.add(firstGas.getType());
        AllMolarNumbers.add(firstGas.getMolarNumber());
        this.volume = firstGas.getVolume();
        this.temperature = firstGas.getTemperature();
        this.pressure = firstGas.getPressure();
        this.molarQuantity = firstGas.getMolarQuantity();
        this.mass = firstGas.getMass();
        this.specHeatCap = firstGas.getSpecHeatCap();
        this.heatCapRatio = firstGas.getHeatCapRatio();

    }



}
