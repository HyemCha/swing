package minihomepage.view.home;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

// 로그아웃 상태일 때: 다이어리 사용범, 방명록 사용법 띄우기
public class HomeTable extends JPanel{
    private JTable table;
    private DefaultTableModel model;
    private Vector<String> vector;

    public HomeTable(String name) {
        setPreferredSize(new Dimension(230, 250));
        setBorder(BorderFactory.createTitledBorder(name));

        initComponents();
        addTableHeader();
        addDataTest();
        addComponents();
    }

    void initComponents() {
        vector = new Vector<>();
    }

    void addComponents() {
        add(table);
    }

    void addTableHeader() {
        vector.addElement("제목");
        vector.addElement("날짜");
        model = new DefaultTableModel(vector, 0){
            public boolean isCellEditable(int r, int c) {
                return c != 0;
            }
        };

        table = new JTable(model);
    }

    void addDataTest() {
        Vector<String> v = new Vector<>();
        v.add("1");
        v.add("1");
        model.addRow(v);
    }
}
