package minihomepage.view.home;

import javax.swing.*;
import java.awt.*;

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

}
