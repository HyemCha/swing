package memojang.edit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Find extends JDialog implements ActionListener {

    private JLabel findStringLabel = new JLabel("찾을 문자열: ");
    private JLabel replaceStringLabel = new JLabel("바꿀 문자열: ");
    private JTextField findStringTF = new JTextField(20);
    private JTextField replaceTF = new JTextField(20);
    private JButton findBtn = new JButton("찾기");
    private JButton replaceBtn = new JButton("바꾸기");

    private JTextArea textArea;

    public Find(JFrame parent, JTextArea textArea) {
        super(parent, "찾기", true); // -> jdialog의 생성자
        // parent: dialog가 종속된 프레임 / title: 이름 / modal: true면 상위 창의 다른 옵션 누르기 불가능
        this.textArea = textArea;
        setLayout(null);
        findStringLabel.setBounds(10, 30, 80, 30);
        replaceStringLabel.setBounds(10, 90, 80, 30);
        findStringTF.setBounds(90, 30, 150, 30);
        replaceTF.setBounds(90, 90, 150, 30);
        findBtn.setBounds(250, 30, 80, 30);
        replaceBtn.setBounds(250, 90, 80, 30);

        add(findStringLabel);
        add(replaceStringLabel);
        add(findStringTF);
        add(replaceTF);
        add(findBtn);
        add(replaceBtn);

        setResizable(false);

        findBtn.addActionListener(this);
        replaceBtn.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Find.this.dispose();
            }
        });

    }

    public void showFind() {
        setTitle("찾기");
//        setLocation(300, 300);
        setSize(400, 120);
        setVisible(true);
    }

    public void showReplace() {
        setTitle("찾아 바꾸기");
        setSize(400, 200);
        setVisible(true);
    }

    private void find() {
        String text = textArea.getText();
        // 중요!!! ""쉼표별로 읽은 파일을 나눠줌.
        text = text.replaceAll("\\r", "");
        // 찾을 문자열 가져오기
        String str = findStringTF.getText();
        // textArea 글의 글자수
        int end = text.length();
        // 찾을 문자열의 글자수
        int len = str.length();
        // 현재 커서의 위치
        int start = textArea.getSelectionEnd();

        System.out.println("start: " + start + "\nend: " + end + "\nlen: " + len);

        // textArea의 마지막 인덱스와 textArea에서 \\r을 없앤 글자수가 같으면 start = 0으로 초기화
        if (start == end) {
            start = 0;
        }

        for (; start <= end - len; start++) {
            if (text.substring(start, start + len).equals(str)) {
                textArea.setText(text);
                textArea.setSelectionStart(start);
                textArea.setSelectionEnd(start + len);
                // Focus를 설정함으로써 선택된 영역이 나타남
                textArea.requestFocus();
                return;
            }
        }

        textArea.setSelectionStart(end);
        textArea.setSelectionEnd(end);
        textArea.requestFocus();
    }

    private void replace() {
        String str = replaceTF.getText();

        if (textArea.getSelectedText().equals(findStringTF.getText())) {
            textArea.replaceRange(str, textArea.getSelectionStart(), textArea.getSelectionEnd());
        } else {
            find();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findBtn) {
            find();
        } else if (e.getSource() == replaceBtn) {
            replace();
        }
    }
}
