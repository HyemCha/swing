package minihomepage.view.structure;

import minihomepage.view.diary.Diary;
import minihomepage.view.guestbook.GuestBook;
import minihomepage.view.home.Home;

import javax.swing.*;

public class Categories extends JTabbedPane{
    private JPanel panel1, panel2, panel3;

    public Categories() {
        setTabPlacement(JTabbedPane.RIGHT);

        panel1 = new Home();
        panel2 = new Diary();
        panel3 = new GuestBook();

        addTab("🏠홈", null, panel1, "Home");
        addTab("다이어리", null, panel2, "Diary");
        addTab("방명록", null, panel3, "Guest Book");
    }
}
