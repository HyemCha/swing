package minihomepage.view.diary;

import minihomepage.model.dao.Diary;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class DiaryMain extends JPanel {
    public DiaryTable datas;
    public DiaryDatas dataTest;
    public DiaryButtons buttons;
    public DiaryMain() {
        dataTest = new DiaryDatas();
        add(dataTest);

        buttons = new DiaryButtons();
        add(buttons, BorderLayout.SOUTH);
    }

    public void initDiaryDatas(Vector<String> title, Vector<Diary> diaryList) {
        dataTest.initComponents(title, diaryList);
    }
}
