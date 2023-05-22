package minihomepage.view.diary;

import javax.swing.*;
import java.awt.*;

public class Diary extends JPanel {
    private DiaryTable datas;
    private DiaryDatas dataTest;
    private DiaryButtons buttons;
    public Diary() {
        setBackground(Color.WHITE);

//        datas = new DiaryTable();
//        add(datas, BorderLayout.CENTER);

        dataTest = new DiaryDatas();
        add(dataTest);

        buttons = new DiaryButtons();
        add(buttons, BorderLayout.SOUTH);
    }
}
