package minihomepage.view.guestbook;

import minihomepage.model.dao.GuestBook;

import javax.swing.*;
import java.util.Vector;

public class GuestBookMain extends JPanel {
    private GuestBookList scroll;
    public GuestBookMain() {
        scroll = new GuestBookList();
        add(scroll);
    }

    public void initGuestBook(Vector<GuestBook> vector) {
        scroll.initComponents(vector);
    }
}
