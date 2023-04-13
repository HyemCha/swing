package memojang.format;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontStyleView extends JFrame implements ActionListener, ListSelectionListener {
    String[] fontName = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    String[] fontStyleName = {"PLAIN", "BOLD", "ITALIC"};
    String[] fontSize = {"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "20", "24", "30", "40"};

    JList fontNameList, fontStyleList, fontSizeList;
    JPanel listPanel, centerPanel, southPanel;
    JScrollPane listScrollPane;
    JLabel textLabel;
    JButton confirmBtn, cancelBtn;

    Font newFont = null;
    JTextArea textArea;

    Container container;

    public FontStyleView(JTextArea textArea) throws HeadlessException {
        this.textArea = textArea;

        deployManager();
        initFontName();
        initFontStyle();
        initFontSize();
        initTextEx();
        etc();
    }

    // 배치 관리자
    public void deployManager() {
        container = getContentPane();
        centerPanel = new JPanel();
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 3));
    }

    // 폰트 네임 부분
    public void initFontName() {
        fontNameList = new JList(fontName);
        fontNameList.addListSelectionListener(this);

        listScrollPane = new JScrollPane(fontNameList);
        listScrollPane.setBorder(new TitledBorder("Font Name"));
        
        listPanel.add(listScrollPane);

        fontNameList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fontNameList.setSelectedValue(textArea.getFont().getName(), false);
    }

    // 폰트 스타일 부분
    public void initFontStyle() {
        fontStyleList = new JList(fontStyleName);
        fontStyleList.addListSelectionListener(this);
        listScrollPane = new JScrollPane(fontStyleList);
        listScrollPane.setBorder(new TitledBorder("Font Style Name"));
        listPanel.add(listScrollPane);

        fontNameList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fontNameList.setSelectedIndex(textArea.getFont().getStyle());
    }

    // 폰트 사이즈 부분
    public void initFontSize() {
        fontSizeList = new JList(fontSize);
        fontSizeList.addListSelectionListener(this);
        listScrollPane = new JScrollPane(fontSizeList);
        listScrollPane.setBorder(new TitledBorder("Font Size"));
        listPanel.add(listScrollPane);

        fontSizeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fontSizeList.setSelectedValue(textArea.getFont().getSize(), false);
    }

    // 폰트 예문
    public void initTextEx() {
        textLabel = new JLabel("폰트 테스트 Font Test");
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        textLabel.setFont(new Font((String) fontNameList.getSelectedValue(), fontStyleList.getSelectedIndex(),
                Integer.parseInt((String) (fontSizeList.getSelectedValue()))));
    }

    // 그 외
    public void etc() {
        centerPanel.add(listPanel);
        centerPanel.add(textLabel);

        confirmBtn = new JButton("확인");
        cancelBtn = new JButton("취소");

        confirmBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        southPanel = new JPanel();
        southPanel.add(confirmBtn);
        southPanel.add(cancelBtn);

        container.add(centerPanel, "center");
        container.add(southPanel, "South");
        newFont = textLabel.getFont();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("확인")) {
            textArea.setFont(newFont);
        }
        this.dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            textLabel.setFont(new Font((String) (fontNameList.getSelectedValue()),
                    fontStyleList.getSelectedIndex(),
                    Integer.parseInt((String) (fontSizeList.getSelectedValue()))));
            newFont = textLabel.getFont();
        } catch (NullPointerException exception) {
            exception.getMessage();
        }
    }
}
