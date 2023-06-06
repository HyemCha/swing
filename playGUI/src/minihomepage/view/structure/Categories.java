package minihomepage.view.structure;

import minihomepage.view.diary.DiaryMain;
import minihomepage.view.guestbook.GuestBook;
import minihomepage.view.home.HomeMain;

import javax.swing.*;

public class Categories extends JTabbedPane{
    public HomeMain home;
    public DiaryMain diary;
    public GuestBook guestBook;

    public Categories() {
        setTabPlacement(JTabbedPane.RIGHT);

        home = new HomeMain();
        diary = new DiaryMain();
        guestBook = new GuestBook();

        addTab("🏠홈", null, home, "Home");
        addTab("다이어리", null, diary, "Diary");
        addTab("방명록", null, guestBook, "Guest Book");
    }
}
