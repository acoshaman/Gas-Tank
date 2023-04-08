package pl.mateusznowakowski;

import java.sql.SQLException;

public class Help {

    public static void main(String[] args) {

        Gas oxygen = new Gas("hydrogen chloride", 3, 350, 0.0224);

        oxygen.displayParameters();


    }

}
