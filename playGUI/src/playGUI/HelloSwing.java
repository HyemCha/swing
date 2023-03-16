package playGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;


public class HelloSwing extends JFrame implements ActionListener { // JFrame은 BorderLayout 배치방식(동서남북)을 갖고 태어난 놈
	JTextField tf1;
	JTable table;
	
	Scanner s = new Scanner(System.in);
	static Connection con = null;
	static PreparedStatement pstmt = null;
	DefaultTableModel model = null;
	static Object[][] obj;
	
	public HelloSwing() {
		this.setTitle("프레임으로 탄생한 HelloSwing 객체");
//		this.setSize(800, 500);
//		this.setLocation(600, 300);
//		Container c = this.getContentPane();
//		c.setBackground(Color.green);
		this.setBounds(500, 300, 800, 500);
		this.setLayout(new FlowLayout()); // 기본 배치방식을 가진 패키지: awt
		
		
		JPanel p1 = new JPanel(); //FlowLayout성격을 가짐
		JPanel p2 = new JPanel(); 
		
		Object[] title = {"이름", "전화번호", "이메일", "나이"};
		Object[][] value = new Object[0][4];
		model= new DefaultTableModel(value, title);
		
		table = new JTable(model);
		
		p1.setBackground(Color.pink);
		p2.setBackground(Color.CYAN);
		this.add(p1);
		this.add(p2);
		JScrollPane p3 = new JScrollPane(table); 
		this.add(p3);
		
		JButton b1 = new JButton("button1");
		JButton b2 = new JButton("button2");
		JButton b3 = new JButton("button3");
		JButton b4 = new JButton("button4");
		JButton b5 = new JButton("button5");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
//		this.add(b1, BorderLayout.NORTH);
//		this.add(b2);
//		this.add(b3, BorderLayout.EAST);
//		this.add(b4, BorderLayout.SOUTH);
//		this.add(b5, BorderLayout.WEST);
		// 버튼 같은 것(컴포넌트)들을 담는 애들 => 컨테이너
		
		tf1 = new JTextField(20);
		p2.add(tf1);
				
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HelloSwing();
		
	}
	
	public static ResultSet select() throws SQLException
	{
		ResultSet rs = null;
		String sql = "select * from person";
		
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}

	// ActionEvent(Class)가 발생하면 
	// ActionListener(Interface)가 지켜보다가 actionPerformed()메서드가 실행됨
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand(); //눌린버튼의 문자열 가져옴
		
		if (cmd.equals("button1")) {
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
				while(rs.next()) {
					info[0] = rs.getString(1);
					info[1] = rs.getString(2);
					info[2] = rs.getString(3);
					info[3] = rs.getString(4);
					model.addRow(info);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (cmd.equals("button2")) {
			tf1.setText("(｡・ω・｡)?");
		} else if (cmd.equals("button3")) {
			tf1.setText("———–[]=¤ԅ༼ ･ 〜 ･ ༽╯");						
		} else if (cmd.equals("button4")) {
			tf1.setText("(´ཫ`)‬");			
		} else if (cmd.equals("button5")) {
			tf1.setText("八(＾□＾*)");			
		}
	}

}
