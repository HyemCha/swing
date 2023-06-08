package minihomepage.view.home;

import minihomepage.model.dao.Diary;
import minihomepage.model.dao.GuestBook;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomeMain extends JPanel {
    HomeTable diary, guestBook;
    JPanel image;
    public HomeMain() {
        setPreferredSize(new Dimension(600, 500));
//        setBorder(BorderFactory.createTitledBorder("testtest"));
        image = new HomeImage();
        add(image, BorderLayout.NORTH);

        diary = new HomeTable("다이어리");
        add(diary, BorderLayout.WEST);

        guestBook = new HomeTable("방명록");
        add(guestBook, BorderLayout.EAST);
    }

    public void initHome(List<Diary> diaryList, List<GuestBook> guestBookList) {
        diary.initComponents(diaryList);
        guestBook.initComponents(guestBookList);
    }
}
