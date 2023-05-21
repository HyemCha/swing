package minihomepage.view.home;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {
    InnerHome diary, guestBook;
    public Home() {
        setPreferredSize(new Dimension(600, 500));
//        setBorder(BorderFactory.createTitledBorder("testtest"));

        diary = new InnerHome("다이어리");
        add(diary, BorderLayout.WEST);

        guestBook = new InnerHome("방명록");
        add(guestBook, BorderLayout.EAST);
    }

}
