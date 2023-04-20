package memojang;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusBar extends JLabel {

    private JPanel leftBar, midBar, rightBar;

    private JLabel left, mid, right;

    JTextArea jTextArea;

    public StatusBar(JTextArea jTextArea) {
        this.jTextArea = jTextArea;

        initComponents();
        addComponents();
    }

    void initComponents() {
        leftBar = new JPanel();
        leftBar.setBackground(new Color(173, 216, 230));
        midBar = new JPanel();
        midBar.setBackground(new Color(229, 228, 226));
        rightBar = new JPanel();
        rightBar.setBackground(new Color(255, 192, 203));

        left = new JLabel();
        mid = new JLabel();
        right = new JLabel();
    }

    void addComponents() {
        this.add( leftBar);
        this.add(midBar);
        this.add(rightBar);

        leftBar.add(left);
        midBar.add(mid);
        rightBar.add(right);
    }

    protected void paintComponent(Graphics g){
        super.paintComponents(g);

        // date
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss");
        left.setText(simpleDateFormat.format(date));

        // 커서 위치 가져와서 mid 레이블에 표시
        int cursorY = 0;
        int cursorX = 0;
        try{
            cursorY = jTextArea.getCaretPosition();
            cursorX = jTextArea.getLineOfOffset(cursorY);
        } catch (BadLocationException e){
            e.printStackTrace();
        }

        mid.setText("row" + cursorY + " column" + cursorX);

        // 글자 크기 비율 계산
        Font font = jTextArea.getFont();
        double fontSizeRate = font.getSize() / 12.0 * 100;
        right.setText(String.format("%.0f", fontSizeRate) + "%");
    }
}
