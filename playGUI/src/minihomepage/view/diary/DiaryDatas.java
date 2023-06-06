package minihomepage.view.diary;

import minihomepage.model.dao.Diary;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

// 1. 날짜와 나머지 데이터를 따로 가져와 표시
// 2. 어색하지만 다같이 표시

public class DiaryDatas extends JPanel{
    private Map<String, ImageIcon> imageMap;
    public String[] title;
    public DiaryDatas() {

    }

    void initComponents(Vector<String> diaryTitle, Vector<Diary> diaryList) {
        imageMap = createImageMap(diaryList);
        JList list = new JList<>(diaryTitle);
        list.setCellRenderer(new ImageRenderer(imageMap));

        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(500, 400));

        add(scroll);
    }

    private Map<String, ImageIcon> createImageMap(Vector<Diary> diaryList) {
        Map<String, ImageIcon> map = new HashMap<>();
        try {
            for (Diary diary : diaryList) {
                map.put(diary.getTitle(), new ImageIcon(diary.getImgUrl()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

//    public class ImageRenderer extends DefaultListCellRenderer {
//
//        public ImageRenderer() {
//        }
//
//        Font font = new Font("helvetica", Font.ITALIC, 24);
//
//        @Override
//        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//            JLabel label = (JLabel) super.getListCellRendererComponent(
//                    list, value, index, isSelected, cellHasFocus
//            );
//            // 보여줄 이미지
//            ImageIcon imgIcon = imageMap.get((String) value);
//            if (imageMap.get((String) value).getImage() != null) {
//                Image img = imageMap.get((String) value).getImage().getScaledInstance(80, 80, Image.SCALE_FAST);
//                imgIcon = new ImageIcon(img);
//            }
//            System.out.println("LOG::DiaryDatas-63::" + imageMap.get(value));
//            label.setIcon(imgIcon);
//            // text 위치
//            label.setHorizontalTextPosition(JLabel.RIGHT);
//            label.setFont(font);
//            return label;
//        }
//    }
}
