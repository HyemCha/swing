package memojang;

import javax.swing.*;
import java.awt.*;

public class MemoInfo extends JFrame {
    JTabbedPane t = new JTabbedPane(JTabbedPane.LEFT);

    public MemoInfo(){
        this.setTitle("메모장 정보");
        JPanel p1 = new JPanel(); p1.setBackground(Color.lightGray);
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        t.add("도움말", p1);
        t.add("메뉴 소개", p2);
        t.add("고객센터안내", p3);

        this.add(t);
        setSize(450, 350);
        setLocationRelativeTo(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
