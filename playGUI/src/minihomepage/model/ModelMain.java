package minihomepage.model;

import db.DBConnect;
import minihomepage.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, ResultSet> selectHome(int userId) {
        PreparedStatement ps;
        Map<String, ResultSet> map = new HashMap<>();
        ResultSet rs = null;
        String sql;

        rs = selectDiary(userId, true);
        map.put("diary", rs);
        rs = selectGuestBook(userId, true);
        map.put("guestBook", rs);

        return map;
    }

    // diary
    public ResultSet selectDiary(int id, boolean limit) {
        String lim = limit ? "limit 5" : "";

        PreparedStatement ps;
        ResultSet rs = null;
        String sql;

        sql = "select * from diary where user_id = ? order by create_at desc " + lim;
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
    public ResultSet selectGuestBook(int userId, boolean limit) {
        String lim = limit ? "limit 5" : "";

        PreparedStatement ps;
        ResultSet rs = null;
        String sql;

        sql = "select * from guest_book where user_id = ? order by create_at desc " + lim;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

}
