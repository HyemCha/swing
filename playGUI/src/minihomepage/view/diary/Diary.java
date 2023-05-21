package minihomepage.view.diary;

import javax.swing.*;

public class Diary extends JPanel {
    private JScrollPane datas;
    private JScrollPane scrollPane;
    public Diary() {
        datas = new DiaryTable();
        add(datas);
    }
}
