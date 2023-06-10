package minihomepage.model;

import db.DBConnect;
import minihomepage.db.DB;
import minihomepage.model.dao.Diary;
import minihomepage.model.dao.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ModelMain {
    private Timestamp ts;
    private Connection con = null;
    private DB db;
    public ModelMain() {
        this.con = DBConnect.makeCon(DBConnect.MINIHOMEPAGE);
        ts = new Timestamp(System.currentTimeMillis());
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

    public void insertUser(User user) {
        PreparedStatement ps;
        String sql;
        sql = "insert into user values (null, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getNickname());
            ps.setString(3, user.getPwd());
            ps.setString(4, user.getProfileImg());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        sql = "select * from diary where user_id = ? order by id desc " + lim;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public void saveDiary(Diary diary) {
        PreparedStatement ps;
        String sql;
        sql = "insert into diary values (null, ?, ?, ?, ?, ?, null)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, diary.getUserId());
            ps.setString(2, diary.getTitle());
            ps.setString(3, diary.getContent());
            ps.setString(4, diary.getImgUrl());
            ps.setTimestamp(5, ts);
            System.out.println("LOG::" + this.getClass().getSimpleName() + "-80::" + diary.getTitle()+diary.getContent());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDiary(int id) {
        PreparedStatement ps;
        String sql;
        sql = "delete from diary where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDiay(Diary diary) {
        PreparedStatement ps;
        String sql;
        sql = "update diary set title = ?, content = ?, updated_at = ? where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getContent());
            ps.setTimestamp(3, ts);
            ps.setInt(4, diary.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
