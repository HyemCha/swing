package db;

import java.sql.*;

public class DBConnect {
	String DBName = "app";
	Connection con;
	public DBConnect() {
		con = makeCon();
	}

	public static Connection makeCon() {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/app?serverTimezone=Asia/Seoul";
		String user = "root";
		String pass = "1234";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			return con;
		}catch(Exception e){
			e.printStackTrace();
			return con;
		}
	}

	public ResultSet select() {
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;

		sql = "select distinct day_num from schedule";
		try {
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet select(String date) {
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;

		sql = "select * from schedule where day_num = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public void insert(String date, String memo) {
		PreparedStatement ps;
		String sql = "insert into schedule values(null, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, memo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		PreparedStatement ps;
		String sql = "delete from schedule shere id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
