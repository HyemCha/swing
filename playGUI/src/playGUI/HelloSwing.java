package playGUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;


public class HelloSwing extends JFrame implements ActionListener { // JFrame은 BorderLayout 배치방식(동서남북)을 갖고 태어난 놈
    JTextField tf1, myName, myPhone, myEmail, myAge;
    JTable table;

    Scanner s = new Scanner(System.in);
    static Connection con = null;
    static PreparedStatement pstmt = null;
    DefaultTableModel dtm = null;
    static Object[][] obj;

    public HelloSwing() {
        this.setTitle("프레임으로 탄생한 HelloSwing 객체");
        this.setBounds(500, 300, 800, 500);
        this.setLayout(new FlowLayout()); // 기본 배치방식을 가진 패키지: awt


        JPanel p1 = new JPanel(); //FlowLayout성격을 가짐
        JPanel p2 = new JPanel();

        Object[] title = {"이름", "전화번호", "이메일", "나이"};
        Object[][] value = new Object[0][4];
        dtm = new DefaultTableModel(value, title);

        table = new JTable(dtm);

        p1.setBackground(Color.pink);
        p2.setBackground(Color.CYAN);
        this.add(p1);
        this.add(p2);
        JScrollPane p3 = new JScrollPane(table);
        this.add(p3);

        JButton b1 = new JButton("정보 조회");
        JButton b2 = new JButton("정보 추가");
        JButton b3 = new JButton("정보 검색");
        JButton b4 = new JButton("정보 삭제");
        JButton b5 = new JButton("정보 수정");
        JButton b6 = new JButton("선택 삭제");
        JButton b7 = new JButton("행 초기화");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
        p1.add(b6);
        p1.add(b7);

        tf1 = new JTextField(20);
        p2.add(tf1);

        myName = new JTextField(10);
        p2.add(myName);

        myPhone = new JTextField(10);
        p2.add(myName);

        myEmail = new JTextField(10);
        p2.add(myName);

        myAge = new JTextField(10);
        p2.add(myName);


        this.setVisible(true);
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new HelloSwing();

    }

    public static ResultSet select() throws SQLException {
        ResultSet rs = null;
        String sql = "select * from person";

        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        return rs;
    }

    public ResultSet search() throws SQLException {
        con = DBConnect.makeCon();
        ResultSet rs = null;

        String sql = "select * from person where name = ?";

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, myName.getText());
        rs = pstmt.executeQuery();
        return rs;
    }

    public void reSet(int key) throws SQLException {
        dtm.setRowCount(0);
        con = DBConnect.makeCon();
        ResultSet rs = null;
        if (key == 1) {
            rs = select();
        } else if (key == 2) {
            rs = search();
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

    public void insert() {
        con = DBConnect.makeCon();
        ResultSet rs = null;

        try{
            String sql = "select * from person where phone = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, myName.getText());
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                sql = "INSERT INTO person VALUES (?, ?, ?, ?)";
                pstmt = con.prepareStatement("INSERT INTO person VALUES (?, ?, ?, ?)");
                pstmt.setString(1, myName.getText());
                pstmt.setString(2, myPhone.getText());
                pstmt.setString(3, myEmail.getText());
                pstmt.setInt(4, Integer.parseInt(myAge.getText())); //숫자이기에 integer.parseint() 사용하기
                int result = pstmt.executeUpdate(); //업데이트는 정수형으로 반환
                if (result == 1) {
                    System.out.println("사용자의 정보를 추가하였습니다.");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        con = DBConnect.makeCon();
        String sql = "DELETE FROM person where name = ?";
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, myName.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ActionEvent(Class)가 발생하면
    // ActionListener(Interface)가 지켜보다가 actionPerformed()메서드가 실행됨
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String cmd = e.getActionCommand(); //눌린버튼의 문자열 가져옴

        if (cmd.equals("정보 조회")) {
            //tf1.setText("(•́ε•̀;ก)💦");
            con = DBConnect.makeCon();
            ResultSet rs;
            try {
                rs = select();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                rs = null;
            }

            try {
                String info[] = new String[4];
                while (rs.next()) {
                    info[0] = rs.getString(1);
                    info[1] = rs.getString(2);
                    info[2] = rs.getString(3);
                    info[3] = rs.getString(4);
                    dtm.addRow(info);
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (cmd.equals("정보 추가")) {
            if (myName.getText() != null && myPhone.getText() != null) { //이름과 전화번호는 not null 속성을 지닌다. (getText 확인하기)
                insert();
                try {
                    select(); //검색 후 - select 하는 이유 : select 함수를 실행해야 rs 저장
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    reSet(1); // 불필요한 행 삭제
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (cmd.equals("정보 검색")) {
            try {
                reSet(2);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (cmd.equals("정보 삭제")) {
            delete();
            try {
                reSet(1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (cmd.equals("행 초기화")) {
            dtm.setRowCount(0);
        }
    }

}
