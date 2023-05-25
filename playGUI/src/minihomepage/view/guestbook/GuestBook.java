package minihomepage.view.guestbook;

import javax.swing.*;

public class GuestBook extends JPanel {
    private JScrollPane scroll;
    public GuestBook() {
        scroll = new GuestBookList();
        add(scroll);
    }
}
