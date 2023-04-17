package pl.mateusznowakowski;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

@Getter
@Setter
public class IsolatedTank extends IdealGas {

    private double totalWork;
    private double temporaryVolume;
    private double temporaryTemperature;
    private double temporaryPressure;
    private double compresionWork;
    private ArrayList<String> allTypes = new ArrayList<String>();

    private ArrayList<Double> allMolarNumbers = new ArrayList<Double>();
    private ArrayList<Gas> allGases = new ArrayList<Gas>();

    // Constructor which determinate only of his volume.
    public IsolatedTank(double volume) {
        this.volume = volume;
        this.temperature = 0;
        this.pressure = 0;
        this.molarQuantity = 0;
        this.mass =0;
        this.specHeatCap = 0;
        this.heatCapRatio = 0;

    }
    // Method which compress gas from cylinder to tank, she is composed by 2 steps.
    public void addGasToTank(String type, double temperature, double volume, double molarQuantity) {

        // This state reperesent cylinder connected to tank, but with closed valve.

        Gas nextGas = new Gas(type, temperature, volume, molarQuantity);

        // Summary volume of filled cylinder and tank.

        temporaryVolume = nextGas.getVolume() + this.volume;

        // Parameters below represents state when we opening valve between cylinder and tank,
        // gas or gases diffuse freely and we have to evaluate temporary temperature and pressure.

        this.temporaryTemperature = evaluateTemperatureWhileMixing(this.temperature, nextGas.temperature,
                summaryHeatCap(allGases), heatCap(nextGas));
        this.molarQuantity = this.molarQuantity + nextGas.molarQuantity;
        this.temporaryPressure = pressureFromIdealGasEquation(this.molarQuantity, this.temporaryVolume, this.temporaryTemperature);

        // Now we added gas from cylinder to whole system.

        allGases.add(nextGas);
        allTypes.add(nextGas.getType());

        // Special heat capacity, heat capacity ratio of mixture and molar number,
        // as weighted average according to molar composition.

        this.specHeatCap = averageSpecHeatCap();
        this.heatCapRatio = averageHeatCapRatio();
        this.molarNumber = averageMolarNumber();

        // Tolar mass of gases compounds.

        this.mass = totalMass();

        // Parameters below represents state, when we compress gas from cylinder into tank,
        // all process occurs without heat exchange with the surroundings.

        this.pressure = finalPressureAdiabaticConversion(this.temporaryVolume, this.volume,
                this.temporaryPressure, this.heatCapRatio);
        this.temperature = temperatureFromIdealGasEquation();

        // Evaluating of work done during compression.
        this.compresionWork = heatOfAdiabaticConversion(this.molarQuantity,
                this.temporaryTemperature, this.temperature, this.specHeatCap );
        this.totalWork += this.compresionWork ;
    }

    public void displayParameters() {

        System.out.println("-- COMPOSITION OF MIXTURE --");
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            System.out.println("Compound: " + gas.getType() + " | Molar quantity: " + gas.getMolarQuantity() + " [mol]");
        }
        System.out.println("-- PARAMETERS OF MIXTURE --");
        System.out.println("Mass[kg}: " +mass);
        System.out.println("Molar quantity[mol]: " +molarQuantity);
        System.out.println("Molar number[kg/mol]: " + (molarNumber / 1000));
        System.out.println("Temperature[K]: " + temperature);
        System.out.println("Pressure[Pa]: " + pressure);
        System.out.println("Volume[m^3]: " + volume);
        System.out.println("Special heat capacity[J/(mol*K)]: " + specHeatCap);
        System.out.println("Heat capacity ratio[1]: " + heatCapRatio);
        System.out.println("Total work[j] : " + totalWork);
    }

    public double totalMolarQuantity() {

        double total = 0;
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            total += gas.getMolarQuantity();
        }
        return total;
    }

    public double totalMass() {

        double total = 0;
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            total += gas.getMolarQuantity() * gas.getMolarNumber();
        }
        return total/1000;
    }
    public double averageSpecHeatCap() {

        double average = 0;
        double totalMQ = totalMolarQuantity();
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            average += gas.getSpecHeatCap()*(gas.getMolarQuantity() /totalMQ);
        }
        return average;
    }
    public double averageMolarNumber() {

        double average = 0;
        double totalMQ = totalMolarQuantity();
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            average += gas.getMolarNumber()*(gas.getMolarQuantity() /totalMQ);
        }
        return average;
    }

    public double averageHeatCapRatio() {
        double average =0;
        double totalMQ = totalMolarQuantity();
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            average += gas.heatCapRatio*(gas.molarQuantity /totalMQ);
        }
        return average;

    }

    public double heatCap(Gas gas) {
        double heatCap = gas.specHeatCap * gas.molarQuantity;
        return heatCap;
    }

    public double summaryHeatCap( ArrayList<Gas> componentList) {
        double heat = 0;
        for( Iterator i = componentList.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            heat += heatCap(gas);
        }
        return heat;
    }
    public double evaluateTemperatureWhileMixing(double tankTemperature, double cylinderTemperature,
                                                 double tankSummaryHeatCap, double cylinderSummaryHeatCap) {
        double deltaTemp;
        double reduntantHeat;
        double newTemperature;
        double summaryHeat = tankSummaryHeatCap + cylinderSummaryHeatCap;

        if (tankTemperature < cylinderTemperature) {
            deltaTemp = cylinderTemperature - tankTemperature;
            reduntantHeat = deltaTemp * cylinderSummaryHeatCap;
            newTemperature = tankTemperature + reduntantHeat / summaryHeat;
            return newTemperature;
        } else if (tankTemperature > cylinderTemperature) {
            deltaTemp = tankTemperature - cylinderTemperature;
            reduntantHeat = deltaTemp * tankSummaryHeatCap;
            newTemperature = cylinderTemperature + reduntantHeat / summaryHeat;
            return newTemperature;
        } else {
            newTemperature = tankTemperature;
            return newTemperature;
        }
    }
    public double finalPressureAdiabaticConversion(double initialVolume, double finalVolume,
                                       double initialPressure, double heatCapRatio ) {
        double initialToFinalVolumeRatio = initialVolume/finalVolume;
        double finalPressure = initialPressure * Math.pow(initialToFinalVolumeRatio, heatCapRatio);
        return finalPressure;
    }
    public double heatOfAdiabaticConversion(double molarQuantity, double initialTemperature, double finalTemperature,
                                   double specHeatCap) {
        double heat = molarQuantity * specHeatCap*(finalTemperature - initialTemperature);
        return heat;
    }

}
