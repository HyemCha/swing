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

    private List<Member> list = new ArrayList<>();
    private Member member = new Member();
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

        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            member.setName(rs.getString(1));
            member.setPh(rs.getString(2));
            member.setEmail(rs.getString(3));
            member.setAge(rs.getInt(4));
            list.add(member);

            System.out.println("model: " + member.getName());
        }

        return list;
    }

    public ResultSet search(String name) throws SQLException {
        String sql = "select * from person where name = ?";

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        return rs;
    }

    public void reSet(int key, String name) throws SQLException {
        dtm.setRowCount(0);
        ResultSet rs = null;
        if (key == 1) {
            list = select();
        } else if (key == 2) {
            rs = search(name);
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
                pstmt.setString(2, member.getName());
                pstmt.setString(3, member.getName());
                pstmt.setInt(4, Integer.parseInt(member.getName())); //숫자이기에 integer.parseint() 사용하기
                int result = pstmt.executeUpdate(); //업데이트는 정수형으로 반환
                if (result == 1) {
                    System.out.println("사용자의 정보를 추가하였습니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        String sql = "DELETE FROM person where name = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
