package pl.mateusznowakowski;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class IdealGas {

    protected String type;
    protected double temperature;
    protected double pressure;

    protected double volume;

    protected double mass;

    protected double molarQuantity;

    protected double molarNumber;

    protected double specHeatCap;

    protected double heatCapRatio;

    protected final double gasConstant = 8.3145;


    public double temperatureFromIdealGasEquation( ) {
        double temperature = (pressure*volume)/(molarQuantity*gasConstant);
        return temperature;
    }

    public double pressureFromIdealGasEquation( ) {
        double pressure = (molarQuantity*gasConstant*temperature)/volume;
        return pressure;
    }

    public double volumeFromIdealGasEquation( ) {
        double volume = (molarQuantity*gasConstant*temperature)/pressure;
        return volume;
    }

    public double molarQuantityFromIdealGasEquation( ) {
        double molarQuantity = (pressure * volume) / (gasConstant * temperature);
        return molarQuantity;
    }

    public double massFromMolarParameters() {
        double mass = (molarNumber * molarQuantity)/1000;
        return mass;
    }

    public abstract void displayParameters();

    public double pressureFromIdealGasEquation(double summaryVolume, double temperature) {
        double pressure;
        pressure = (molarQuantity * gasConstant * temperature)/ summaryVolume;
        return pressure;
    }

}
