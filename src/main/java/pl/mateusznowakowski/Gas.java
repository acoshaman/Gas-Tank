package pl.mateusznowakowski;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
@Getter
@Setter
public class Gas extends IdealGas {
    //Parameters of gas

    public Gas() {
    }

    public Gas(String type) {
        this.type = type;
        dbConnection();
    }

    public Gas(String type,  double temperature, double volume,double molarQuantity) {

        this.type = type;
        this.molarQuantity = molarQuantity;
        this.temperature = temperature;
        this.volume = volume;
        this.pressure = pressureFromIdealGasEquation();
        dbConnection();
        this.mass = massFromMolarParameters();

//        evaluatePressure();
//        evaluateMass();
        try {isDataAreCorrect();}
        catch (NegativeNumberException nnee) {
            System.out.println(nnee);

        }
    }




    protected ResultSet dbConnection( ) {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gas-tank", "root", "entropia");

            Statement statement = connection.createStatement();

            ResultSet readRecord = statement.executeQuery(
                    "SELECT * FROM `gas-tank`.gas_param WHERE `type` = '" + type + "'");
            if (readRecord.next()) {
                try {
                    this.molarNumber = readRecord.getDouble(3);
                } catch (NumberFormatException nfe) {
                    System.out.println("Unfortunately, we occur " + nfe + " in molar numbers column.");}

                try {
                    this.specHeatCap = readRecord.getDouble(4);
                } catch (NumberFormatException nfe) {
                    System.out.println("Unfortunately, we occur " + nfe + " in specific heat capacitys column.");}

                try {
                    this.heatCapRatio = readRecord.getDouble(5);
                } catch (NumberFormatException nfe) {
                    System.out.println("Unfortunately, we occur " + nfe + " in heat capcity ratios column.");
                }finally {
                    if(readRecord != null) {
                        readRecord.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if(connection != null) {
                        connection.close();
                    }
                }
            }
            return readRecord;
        }
        catch (SQLException sqle) {
            System.out.println("unfotunately we occur " + sqle + " , so problem with database.");
            return null;
        }



    }


    protected void evaluatePressure() {
        this.pressure = (molarQuantity*gasConstant*temperature)/volume;
    }

    protected void evaluateMass() {
        this.mass = (molarQuantity*molarNumber)/1000;
    }

    protected void isDataAreCorrect() throws NegativeNumberException {
        if (type.isEmpty()) {
            NullPointerException nullPointerException = new NullPointerException();
            throw nullPointerException;
        }
        if (molarQuantity <= 0) {
            NegativeNumberException negativeNumberException = new NegativeNumberException();
            throw negativeNumberException;
        } if (temperature <= 0) {
            NegativeNumberException negativeNumberException = new NegativeNumberException();
            throw negativeNumberException;
        } if (volume <= 0) {
            NegativeNumberException negativeNumberException = new NegativeNumberException();
            throw negativeNumberException;
        }

    }

    public void displayParameters() {
        System.out.println("Type of gas: " +type);
        System.out.println("Mass[kg}: " +mass);
        System.out.println("Molar quantity[mol]: " +molarQuantity);
        System.out.println("Molar number[g/mol]: " + molarNumber);
        System.out.println("Temperature[K]: " + temperature);
        System.out.println("Pressure[Pa]: " + pressure);
        System.out.println("Volume[m^3]: " + volume);
        System.out.println("Heat capacity[J/(mol*K)]: " + specHeatCap);
        System.out.println("Heat capacity ratio[1]: " + heatCapRatio);
    }

}
