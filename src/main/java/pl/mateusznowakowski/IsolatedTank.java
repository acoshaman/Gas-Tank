package pl.mateusznowakowski;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;

@Getter
@Setter
public class IsolatedTank extends IdealGas {

    protected double totalWork;


    private ArrayList<String> allTypes = new ArrayList<String>();

    private ArrayList<Double> allMolarNumbers = new ArrayList<Double>();

    private ArrayList<Gas> allGases = new ArrayList<Gas>();

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

    public IsolatedTank(String type, double temperature, double volume, double molarQuantity) {
        Gas firstGas = new Gas(type, temperature, volume ,molarQuantity);
        ;
        allGases.add(firstGas);
        allTypes.add(firstGas.getType());
        allMolarNumbers.add(firstGas.getMolarNumber());
        this.volume = firstGas.getVolume();
        this.temperature = firstGas.getTemperature();
        this.pressure = firstGas.getPressure();
        this.molarQuantity = firstGas.molarQuantity;
        this.molarNumber = firstGas.molarNumber;
        this.mass = firstGas.getMass();
        this.specHeatCap = firstGas.getSpecHeatCap();
        this.heatCapRatio = firstGas.getHeatCapRatio();

    }

    public void addGasToTank(String type, double temperature, double volume, double molarQuantity) {
        Gas nextGas = new Gas(type, temperature, volume, molarQuantity);
        allGases.add(nextGas);
        double summaryVolume = nextGas.volume + this.volume;
        this.specHeatCap = averageSpecHeatCap();
        this.heatCapRatio = averageHeatCapRatio();
        this.molarQuantity = totalMolarQuantity();
        this.temperature = evaluateTemperatureWhileMixing(temperature, nextGas.temperature,
                summaryHeatCap(allGases), heatCap(nextGas));
        double initialTemperature = this.temperature;
        this.pressure = pressureFromIdealGasEquation(summaryVolume);
        this.pressure = finalPressureAdiabaticConversion(summaryVolume, this.volume,
                pressure, heatCapRatio);
        this.temperature = temperatureFromIdealGasEquation();
        this.totalWork += heatOfAdiabaticConversion(initialTemperature, this.temperature, this.specHeatCap );
        this.mass = totalMass();

    }

    public void displayComposition() {
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            System.out.println("Compound: " + gas.type + " | Molar quantity: " + gas.molarQuantity + " [mol]");
        }

    }
    public void displayParameters() {
        System.out.println("-- COMPOSITION OF MIXTURE --");
        displayComposition();
        System.out.println("-- PARAMETERS OF MIXTURE --");
        System.out.println("Mass[kg}: " +mass);
        System.out.println("Molar quantity[mol]: " +molarQuantity);
        System.out.println("Molar number[g/mol]: " + molarNumber);
        System.out.println("Temperature[K]: " + temperature);
        System.out.println("Pressure[Pa]: " + pressure);
        System.out.println("Volume[m^3]: " + volume);
        System.out.println("Heat capacity[J/(mol*K)]: " + specHeatCap);
        System.out.println("Heat capacity ratio[1]: " + heatCapRatio);
        System.out.println("Total work: " + totalWork);

    }




    public double totalMolarQuantity() {
        double total = 0;
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            total += gas.molarQuantity;
        }
        return total;
    }

    public double totalMass() {
        double total = 0;
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            total += gas.molarQuantity * gas.molarNumber;
        }
        return total/1000;

    }
    public double averageSpecHeatCap() {
        double average =0;
        double totalMQ = totalMolarQuantity();
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            average += gas.specHeatCap*(gas.molarQuantity /totalMQ);
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
    public double heatOfAdiabaticConversion(double initialTemperature, double finalTemperature,
                                   double specHeatCap) {
        double heat = specHeatCap*(finalTemperature - initialTemperature);
        return heat;
    }











}
