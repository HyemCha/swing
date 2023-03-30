package playGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HelloSwingView extends JFrame{
    private JTextField  myName, myPhone, myEmail, myAge;
    private JTable table;
    private JButton b1, b2, b3, b4, b5, b6, b7;
    private JPanel p1, p2;
    private JScrollPane p3;

    public JTextField getMyName() {
        return myName;
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getMyPhone() {
        return myPhone;
    }

    public JTextField getMyEmail() {
        return myEmail;
    }

    public JTextField getMyAge() {
        return myAge;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    private DefaultTableModel dtm = null;

    public HelloSwingView() {
        initWindow();
        initComponents();
        addComponents();
    }

    private void initWindow() {
        setTitle("프레임으로 탄생한 HelloSwing 객체");
        setBounds(500, 300, 800, 700);
        setLayout(new FlowLayout());
    }

    private void initComponents() {
        Object[] title = {"이름", "전화번호", "이메일", "나이"};
        Object[][] value = new Object[0][4];
        dtm = new DefaultTableModel(value, title);
        table = new JTable(dtm);

        p1 = new JPanel(new GridLayout(6,2));
        p2 = new JPanel();
        p3 = new JScrollPane(table);
        p1.setBackground(Color.pink);
        p2.setBackground(Color.CYAN);

        b1 = new JButton("정보 조회");
        b2 = new JButton("정보 추가");
        b3 = new JButton("정보 검색");
        b4 = new JButton("정보 삭제");
        b5 = new JButton("정보 수정");
        b6 = new JButton("선택 삭제");
//        b7 = new JButton("행 초기화");

        myName = new JTextField(10);
        myPhone = new JTextField(10);
        myEmail = new JTextField(10);
        myAge = new JTextField(10);
    }

    public void addComponents() {
        this.add(p1);
        this.add(p2);
        this.add(p3);

        p1.add(b1);
        p1.add(new JLabel("모든 정보 조회"));
        p1.add(b2);
        p1.add(new JLabel("정보 추가"));
        p1.add(b3);
        p1.add(new JLabel("이름으로 정보 조회"));
        p1.add(b4);
        p1.add(new JLabel("번호로 정보 삭제"));
        p1.add(b5);
        p1.add(new JLabel("셀 눌러서 해당 셀 정보 수정"));
        p1.add(b6);
        p1.add(new JLabel("선택된 행 삭제"));
//        p1.add(b7);

        p2.add(myName);
        p2.add(myPhone);
        p2.add(myEmail);
        p2.add(myAge);
    }

    public void addActionListener(ActionListener listener) {
        b1.addActionListener(listener);
        b2.addActionListener(listener);
        b3.addActionListener(listener);
        b4.addActionListener(listener);
        b5.addActionListener(listener);
        b6.addActionListener(listener);
//        b7.addActionListener(listener);
    }

    public void addMouseListener(MouseListener listener) {
        table.addMouseListener(listener);
    }

    public void addField(){

    }

    public void removeField() {

    }
}
