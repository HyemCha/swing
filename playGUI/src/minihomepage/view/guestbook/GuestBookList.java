package minihomepage.view.guestbook;

import minihomepage.model.dao.GuestBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;

public class GuestBookList extends JScrollPane {
    public DefaultTableModel model;
    public JTable table;
    private Vector<String> vector;

    public GuestBookList() {
        table = new JTable();
    }

    public void initComponents(Vector<GuestBook> guestBookList) {
        vector = new Vector<>();
        vector.addElement("닉네임");
        vector.addElement("내용");
        vector.addElement("날짜");
        model = new DefaultTableModel(vector, 0){
            public boolean isCellEditable(int r, int c) {
                return c != 0;
            }
        };

        table.setModel(model);
        getViewport().add(table);
        Vector<String> v;
        for (GuestBook g : guestBookList) {
            v = new Vector<>();
            v.add(g.getHostNickname());
            v.add(g.getContent());
            v.add(g.getCreateAt()!=null?g.getCreateAt().toString():null);
            model.addRow(v);
        }
    }

    public void addMouseListener(MouseListener listener) {
        table.addMouseListener(listener);
    }
}
