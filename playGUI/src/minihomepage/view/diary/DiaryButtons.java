package minihomepage.view.diary;

import javax.swing.*;
import java.awt.*;

public class DiaryButtons extends JPanel {

    private JButton addBtn, editBtn;
    public DiaryButtons() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.pink);

        initComponents();
        addComponents();
    }

    void initComponents() {
        addBtn = new JButton("글쓰기");
        editBtn = new JButton("수정하기");
    }

    void addComponents() {
        add(addBtn);
        add(editBtn);
    }
}
