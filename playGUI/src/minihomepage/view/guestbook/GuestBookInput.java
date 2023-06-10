package minihomepage.view.guestbook;

import minihomepage.controller.GuestBookController;

import javax.swing.*;
import java.awt.*;

public class GuestBookInput extends JPanel {
    public JTextField content;
    public JButton writeBtn;
    public GuestBookInput() {
        setLayout(new BorderLayout());
        initComponents();
    }

    public void initComponents() {
        content = new JTextField();
        content.setColumns(35);
        add(content);

        writeBtn = new JButton("글쓰기");
        add(writeBtn, BorderLayout.EAST);
    }

    public void addListener(GuestBookController listener) {
        writeBtn.addActionListener(listener);
    }
}
