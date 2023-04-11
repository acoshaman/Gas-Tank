package pl.mateusznowakowski;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;

@Getter
@Setter
public class IsolatedTank extends IdealGas {



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



    }
    public double totalMolarQuantity() {
        double total = 0;
        for(Iterator i = allGases.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            total += gas.molarQuantity;
        }
        return total;
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

    public double heatCap(double specHeatCap, double molarQuantity) {
        double heatCap = specHeatCap * molarQuantity;
        return heatCap;
    }

    public double summaryHeatCap( ArrayList<Gas> componentList) {
        double heat = 0;
        for( Iterator i = componentList.iterator(); i.hasNext();) {
            Gas gas = (Gas) i.next();
            heat += heatCap(gas.specHeatCap, gas.molarQuantity);
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
    public double heatOfAdiabaticC(double initialTemperature, double finalTemperature,
                                   double specHeatCap) {
        double heat = specHeatCap*(finalTemperature - initialTemperature);
        return heat;
    }

    public static void main(String[] args) {
        IsolatedTank tank = new IsolatedTank("argon", 320, 0.2,
                                                3);
        double mol = tank.molarNumber;
        double mo = tank.molarQuantity;
        System.out.println(mol);
        System.out.println(mo);
    }








}
