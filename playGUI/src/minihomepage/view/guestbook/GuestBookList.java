package minihomepage.view.guestbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class GuestBookList extends JScrollPane {
    private DefaultTableModel model;
    private JTable table;
    private Vector<String> vector;

    public GuestBookList() {
        initComponents();
        addTableHeader();
        getViewport().add(table);
        addDataTest();
    }

    public void initComponents() {
        vector = new Vector<>();
    }

    public void addTableHeader() {
        vector.addElement("닉네임");
        vector.addElement("내용");
        vector.addElement("날짜");
        model = new DefaultTableModel(vector, 0){
            public boolean isCellEditable(int r, int c) {
                return c != 0;
            }
        };

        table = new JTable(model);
    }

    public void addDataTest() {
        Vector<String> v = new Vector<>();
        v.add("yunjin");
        v.add("하이루 방가방가");
        v.add("2023-01-01");
        model.addRow(v);
    }
}
