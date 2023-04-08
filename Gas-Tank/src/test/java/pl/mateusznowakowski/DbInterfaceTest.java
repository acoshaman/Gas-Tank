package pl.mateusznowakowski;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DbInterfaceTest {

    @Test
    void readRecordTest() {
        DbInterface dbInterface = new DbInterface();
        ResultSet readRecord = dbInterface.readRecordUsingName("hydrogen chloride");

        try {
            if (readRecord.next()) {
            assertEquals(36.46, readRecord.getDouble(3));
            assertEquals(20.16, readRecord.getDouble(4));
            assertEquals(1.41, readRecord.getDouble(5));

            }
        } catch (SQLException sqle) {
        System.out.println(sqle);
}



    }
}