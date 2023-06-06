package minihomepage.model;

import db.DBConnect;
import minihomepage.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelMain {
    private Connection con = null;
    private DB db;
    public ModelMain() {
        this.con = DBConnect.makeCon(DBConnect.MINIHOMEPAGE);
//        this.db = new DB("minihomepage", con);
    }

    // get user profile
    public ResultSet selectUser() {
        PreparedStatement ps;
        ResultSet rs = null;
        String sql;

        sql = "select * from user";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    // home
    public ResultSet selectHome() {
        PreparedStatement ps;
        ResultSet rs = null;
        String sql;

        sql = "select * from user";
        try {
            ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    // diary
    public ResultSet selectDiary(int id) {
        PreparedStatement ps;
        ResultSet rs = null;
        String sql;

        sql = "select * from diary where user_id = ? order by create_at desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    // guest book
    public ResultSet selectGuestBook() {
        PreparedStatement ps;
        ResultSet rs = null;
        String sql;

        sql = "select * from guest_book";
        try {
            ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

}
