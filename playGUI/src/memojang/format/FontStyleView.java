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

        arrangementManager();
        initFontName();
        initFontStyle();
        initFontSize();
        initTextEx();
        etc();
    }

    // 배치 관리자
    public void arrangementManager() {
        // 다른 AWT 컴포넌트를 포함할 수 있는 컨테이너 객체
        container = getContentPane();
        // 2행 1열의 레이아웃을 가진 패널 생성 (첫 행에 listPanel 등록, 두 번째 행에 버튼 등록)
        centerPanel = new JPanel(new GridLayout(2, 1));
        // 3열을 가진 그리드 레이아웃을 가진 패널 생성 (스크롤 패널들을 여기에 등록)
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 3));
    }

    // 폰트 네임 부분
    public void initFontName() {
        // fontName 배열을 보여주는 JList 타입의 fontNameList 초기화
        fontNameList = new JList(fontName);
        // list의 요소를 클릭하는 이벤트가 발생하면 jlist에서 이벤트를 처리하도록하는 이벤트 리스너 등록
        fontNameList.addListSelectionListener(this);

        // displays the contents of the specified component. 뷰의 크기보다 컴포넌트 내용이 더 크면 스크롤바 나타남
        // 스크롤패널에 폰트이름 리스트 등록
        listScrollPane = new JScrollPane(fontNameList);
        // 타이틀이 font name인 테두리 설정
        listScrollPane.setBorder(new TitledBorder("Font Name"));

        // listPanel에 Font Name 스크롤 패널 등록
        listPanel.add(listScrollPane);

        // 한 번에 하나의 인접한 간격을 선택할 수 있음
        fontNameList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // 명시된 객체를 리스트에서 선택.
        fontNameList.setSelectedValue(textArea.getFont().getName(), false);
    }

    // 폰트 스타일 부분
    public void initFontStyle() {
        // fontName 배열을 보여주는 JList 타입의 fontStyleNameList 초기화
        fontStyleList = new JList(fontStyleName);
        // list의 요소를 클릭하는 이벤트가 발생하면 jlist에서 이벤트를 처리하도록하는 이벤트 리스너 등록
        fontStyleList.addListSelectionListener(this);

        // 스크롤패널에 폰트스타일 리스트 등록
        listScrollPane = new JScrollPane(fontStyleList);
        // 타이틀이 font style name인 테두리 설정
        listScrollPane.setBorder(new TitledBorder("Font Style Name"));

        // listPanel에 Font Style Name 스크롤 패널 등록
        listPanel.add(listScrollPane);

        // 한 번에 하나의 인접한 간격을 선택할 수 있음
        fontNameList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // 명시된 객체를 리스트에서 선택.
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
        fontSizeList.setSelectedValue("" + textArea.getFont().getSize(), false);
    }

    // 폰트 예문
    public void initTextEx() {
        // 폰트 테스트할 텍스트를 라벨에 등록
        textLabel = new JLabel("Hello World! It's Font Test");
        // 컨텐츠를 가로방향으로 가운데정렬.
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        // 텍스트라벨에 폰트 적용
        textLabel.setFont(new Font((String) fontNameList.getSelectedValue(),
                fontStyleList.getSelectedIndex(),
                Integer.parseInt((String) (fontSizeList.getSelectedValue()))));
    }

    // 그 외
    public void etc() {
        // centerPanel에 listPanel과 textLabel 추가
        centerPanel.add(listPanel);
        centerPanel.add(textLabel);

        // 확인 취소 버튼 생성
        confirmBtn = new JButton("확인");
        cancelBtn = new JButton("취소");

        // 확인 취소 버튼에 액션 리스너 등록
        confirmBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        // southPanel에 버튼 두 개 등록
        southPanel = new JPanel();
        southPanel.add(confirmBtn);
        southPanel.add(cancelBtn);

        // 컨테이너에 centerPanel과 southPanel 등록
        container.add(centerPanel, "Center");
        container.add(southPanel, "South");

        // 적용할 폰트를 newFont에 담음
//        newFont = textLabel.getFont();
    }

    // 이벤트가 발생하면 이 함수 호출되고 이벤트 객체가 전달됨
    @Override
    public void actionPerformed(ActionEvent e) {
        // 이벤트 객체가 '확인'이면 메모장의 텍스트를 newFont로 설정
        if (e.getActionCommand().equals("확인")) {
            textArea.setFont(newFont);
        }
        this.dispose();
    }

    // 리스트 선택되면 textLabel의 폰트를 설정하고 적용할 폰트를 newFont에 담음
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
