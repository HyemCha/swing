package minihomepage.model;

import db.DBConnect;

import java.sql.Connection;

public class ModelMain {
    private Connection con;
    public ModelMain() {
        makeConnection();
    }

    void makeConnection() {
        con = DBConnect.makeCon("minihomepage");
    }


}
