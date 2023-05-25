package minihomepage.view.diary;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ImageRenderer extends DefaultListCellRenderer {
    private Map<String, ImageIcon> imageMap;

    public ImageRenderer(Map<String, ImageIcon> imageMap) {
        this.imageMap = imageMap;
    }

    Font font = new Font("helvetica", Font.ITALIC, 24);

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus
        );
        // 보여줄 이미지
        label.setIcon(imageMap.get((String) value));
//        label.set
        // text 위치
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }
}
