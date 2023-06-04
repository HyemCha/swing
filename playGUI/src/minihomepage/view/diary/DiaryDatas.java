package minihomepage.view.diary;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// 1. 날짜와 나머지 데이터를 따로 가져와 표시
// 2. 어색하지만 다같이 표시

public class DiaryDatas extends JPanel{
    private Map<String, ImageIcon> imageMap;
    public DiaryDatas() {
        initComponents();
    }

    void initComponents() {
        String[] title = {"Mario", "Luigi", "Bowser", "Koopa", "Princess"};
        imageMap = createImageMap(title);
        JList list = new JList<>(title);
        list.setCellRenderer(new ImageRenderer(imageMap));

//        System.out.println("LOG::imageMap - " + imageMap);

        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(500, 400));

        add(scroll);
    }

    private Map<String, ImageIcon> createImageMap(String[] list) {
        Map<String, ImageIcon> map = new HashMap<>();
        try {
            map.put("Mario", new ImageIcon(new URL("http://i.stack.imgur.com/NCsHu.png")));
            map.put("Luigi", new ImageIcon(new URL("http://i.stack.imgur.com/UvHN4.png")));
            map.put("Bowser", new ImageIcon(new URL("http://i.stack.imgur.com/s89ON.png")));
            map.put("Koopa", new ImageIcon(new URL("http://i.stack.imgur.com/QEK2o.png")));
            map.put("Princess", new ImageIcon(new URL("http://i.stack.imgur.com/f4T4l.png")));
            map.put("Princess", new ImageIcon(new URL("http://i.stack.imgur.com/f4T4l.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }
}
