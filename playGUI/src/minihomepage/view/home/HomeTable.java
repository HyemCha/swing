package minihomepage.view.home;

import minihomepage.model.dao.Diary;
import minihomepage.model.dao.GuestBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
import java.util.List;


// 로그아웃 상태일 때: 다이어리 사용범, 방명록 사용법 띄우기
public class HomeTable extends JPanel{
    private JTable table;
    private DefaultTableModel model;
    private Vector<String> vector;

    public HomeTable(String name) {
        setPreferredSize(new Dimension(230, 250));
        setBorder(BorderFactory.createTitledBorder(name));

    }

    public void initComponents(List<Diary> diaryList, List<GuestBook> guestBookList) {
        vector = new Vector<>();
        add(table);
        vector.addElement("제목");
        vector.addElement("날짜");
        model = new DefaultTableModel(vector, 0){
            public boolean isCellEditable(int r, int c) {
                return c != 0;
            }
        };

        table = new JTable(model);

        Vector<String> v = new Vector<>();
        v.add("1");
        v.add("1");
        model.addRow(v);
    }
}
