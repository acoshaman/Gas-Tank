package pl.mateusznowakowski;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class IsolatedTank {
    private double totalVolume;

    private double totalTemperature;

    private double totalPressure;

    private double totalMolarQuantity;

    private double totalMass;

    private double totalSpecHeatCap;

    private double totalHeatCapRatio;

    private final double gasConstant = 8.3145;

    private ArrayList<String> AllTypes = new ArrayList<String>();

    private ArrayList<Double> AllMolarNumbers = new ArrayList<Double>();

    private ArrayList<Gas> AllGases = new ArrayList<Gas>();

    // Primitive constructor
    public IsolatedTank(double volume) {
        this.totalVolume = volume;
        this.totalTemperature = 0;
        this.totalPressure = 0;
        this.totalMolarQuantity = 0;
        this.totalMass =0;
        this.totalSpecHeatCap = 0;
        this.totalHeatCapRatio = 0;

    }

    public IsolatedTank(String type, double molarQuantity, double temperature, double volume) {
        Gas firstGas = new Gas(type, molarQuantity, temperature, volume);
        AllGases.add(firstGas);
        AllTypes.add(firstGas.getType());
        AllMolarNumbers.add(firstGas.getMolarNumber());
        this.totalVolume = firstGas.getVolume();
        this.totalTemperature = firstGas.getTemperature();
        this.totalPressure = firstGas.getPressure();
        this.totalMolarQuantity = firstGas.getMolarQuantity();
        this.totalMass = firstGas.getMass();
        this.totalSpecHeatCap = firstGas.getSpecHeatCap();
        this.totalHeatCapRatio = firstGas.getHeatCapRatio();

    }
    public double temperatureFromIdealGasEquation( double pressure, double volume ,
                                                   double molarQuantity) {
        double temperature = (pressure*volume)/(molarQuantity*gasConstant);
        return temperature;
    }

    public double pressureFromIdealGasEquation( double temperature, double volume,
                                                double molarQuantity) {
        double pressure = (molarQuantity*gasConstant*temperature)/volume;
        return pressure;
    }

    public double volumeFromIdealGasEquation( double pressure, double temperature,
                                              double molarQuantity) {
        double volume = (molarQuantity*gasConstant*temperature)/pressure;
        return volume;
    }

    public double molarQuantityFromIdealGasEquation( double pressure, double temperature,
                                                      double volume) {
        double molarQuantity = (pressure * volume) / (gasConstant * temperature);
        return molarQuantity;
    }





}
