package pl.mateusznowakowski;

import java.sql.*;



public class DbInterface {


    protected static ResultSet readRecordUsingName(String type) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gas-tank", "root", "entropia");

            Statement statement = connection.createStatement();

            ResultSet readRecord = statement.executeQuery(
                    "SELECT * FROM `gas-tank`.gas_param WHERE `type` = '" + type + "'");
            if (readRecord.next()) {
                try {
                    double a = readRecord.getDouble(3);
                } catch (NumberFormatException nfe) {
                    System.out.println("Unfortunately, we occur " + nfe + " in molar numbers column.");}

                try {
                    System.out.println(readRecord.getDouble(4));
                } catch (NumberFormatException nfe) {
                    System.out.println("Unfortunately, we occur " + nfe + " in specific heat capacitys column.");}

                try {
                    System.out.println(readRecord.getDouble(5));
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


    protected void addRecord(
            String type, double molarNumber, double specHeatCap, double heatCapRatio) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gas-tank", "root", "entropia");

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM gas_param");


            int max = 0;

            while(rs.next()){
                max = rs.getInt("MAX(id)");
            }
            int new_id = max + 1;

           statement.executeUpdate("INSERT INTO gas_param VALUES ('" + new_id + "', '" + type + "'" +
           ", '" + molarNumber + "', '" + specHeatCap + "', '" + heatCapRatio + "')");

            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }
}
