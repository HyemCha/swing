package minihomepage.view.structure;


import javax.swing.*;
import java.awt.*;

public class ViewProfileImage extends JPanel {
    private Image image;

    public ViewProfileImage() {
        setPreferredSize(new Dimension(100, 100));

        image = new ImageIcon("image/minihomepage/default.jpg").getImage();
        setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        setPreferredSize(new Dimension(180, 210));
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 그려지는 이미지 사이즈 조정 가능
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    // 사용자 프로필 사진 가져오는 메서드
    void getUserImage() {

    }
}
