package playGUI;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloSwingModel {

    private List<Member> list = null;
    private Member member = null;
    Scanner s = new Scanner(System.in);
    static Connection con = null;
    static PreparedStatement pstmt = null;

    static ResultSet rs = null;
    DefaultTableModel dtm = null;
    static Object[][] obj;

    public HelloSwingModel() {
        this.con = DBConnect.makeCon();
    }

    public List<Member> select() throws SQLException {
        String sql = "select * from person";

        list = new ArrayList<>();

        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            member = new Member();
            member.setName(rs.getString(1));
            member.setPh(rs.getString(2));
            member.setEmail(rs.getString(3));
            member.setAge(rs.getInt(4));
            list.add(member);
        }

        return list;
    }

    public List<Member> search(String name) throws SQLException {
        String sql = "select * from person where name = ?";

        list = new ArrayList<>();

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            member = new Member();
            member.setName(rs.getString(1));
            member.setPh(rs.getString(2));
            member.setEmail(rs.getString(3));
            member.setAge(rs.getInt(4));
            list.add(member);
        }

        return list;
    }

    public void delete(String name) {
        String sql = "DELETE FROM person where name = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRow(String pn) throws SQLException {
        String sql = "delete from person where phone = ?";

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, pn);
        pstmt.executeUpdate();
    }

    public void update(int col, String uField, String hp) throws SQLException {
        String sql = "update person set ";
        if (col == 0){
            sql += "name = ?";
        } else if (col == 1) {
            sql += "ph = ?";
        } else if (col == 2) {
            sql += "email = ?";
        } else if (col == 3) {
            sql += "age = ?";
        }
        sql += " where phone = ?";

        System.out.println("model update: " + sql);

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, uField);
        pstmt.setString(2, hp);
        System.out.println(pstmt.executeUpdate());
    }

    public void reSet(int key, String name) throws SQLException {
        dtm.setRowCount(0);
        ResultSet rs = null;
        if (key == 1) {
            list = select();
        } else if (key == 2) {
            //rs = search(name);
        }

        try {
            while (rs.next()) {
                String info[] = new String[4];
                info[0] = rs.getString(1);
                info[1] = rs.getString(2);
                info[2] = rs.getString(3);
                info[3] = rs.getString(4);
                dtm.addRow(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Member member) {
        ResultSet rs = null;

        try {
            String sql = "select * from person where phone = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getName());
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                sql = "INSERT INTO person VALUES (?, ?, ?, ?)";
                pstmt = con.prepareStatement("INSERT INTO person VALUES (?, ?, ?, ?)");
                pstmt.setString(1, member.getName());
                pstmt.setString(2, member.getPh());
                pstmt.setString(3, member.getEmail());
                pstmt.setInt(4, member.getAge()); //숫자이기에 integer.parseint() 사용하기
                int result = pstmt.executeUpdate(); //업데이트는 정수형으로 반환
                if (result == 1) {
                    System.out.println("사용자의 정보를 추가하였습니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tableReset() {
        dtm.setRowCount(0);
    }

}
