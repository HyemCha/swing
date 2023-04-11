package memojang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MemoModel2 implements MemoModelInterface {

    String fileName = "";

    private JTextArea ta;

    private JFileChooser jfc;

    int re;

    String lastTa = "";

    // textArea에 있는 글이 새로운 글인지
    boolean isNew = false;

    boolean change = false;

    public MemoModel2(MemoView view) {
        this.ta = view.getjTextArea();
        jfc = new JFileChooser();
    }

    public void createNew() {
        ta.setText("");
        isNew = false;
    }

    public void open(MemoView view) {
        re = jfc.showOpenDialog(view);
        if (ta.getText().equals("")) {
            openCommon(view);
        } else {
            String[] buttons = {"저장", "저장 안 함", "취소"};
            int result = JOptionPane.showOptionDialog(
                    null,
                    "변경 내용을 저장하시겠습니까?",
                    "Memojang",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    buttons,
                    "저장");

            switch (result) {
                case JOptionPane.YES_NO_OPTION -> {
                    save(view);
                }
                case JOptionPane.NO_OPTION -> {
                    openCommon(view);
                }
            }
        }

    }

    private void openCommon(MemoView view) {
        if (re == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();

            setFilename(file.getAbsolutePath(), view);

            FileReader fr = null;
            String data = "";

            int ch;

            try {
                fr = new FileReader(file);
                while ((ch = fr.read()) != -1) {
                    data += (char) ch;
                }
                ta.setText(data);
                fr.close();

                lastTa = ta.getText();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void save(MemoView view) {
        change = isChanged();
        System.out.println("save isNew: " + isNew);

        // 내용만 바뀌고 파일명 그대로인 경우
        if (!isNew && change) {
            saveText(view);
            isNew = false;
        } else {
            re = jfc.showSaveDialog(view);
            if (re == JFileChooser.APPROVE_OPTION) {
                saveText(view);
            }
        }

        lastTa = ta.getText();
    }


    public void saveAs(MemoView view) {
        re = jfc.showSaveDialog(view);
        if (re == JFileChooser.APPROVE_OPTION) {
            saveText(view);
        }

        isNew = false;
        lastTa = "";
    }

    public void quit(MemoView view) {

        System.exit(0);
    }

    public JFrame description(MemoView view) {
        JFrame jf = new JFrame();
        jf.setSize(640, 480);
        jf.setVisible(true);
        jf.setTitle("메모장 정보");
        jf.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("img/cat1.jpg");
        Image img = icon.getImage();
        Image updateImg = img.getScaledInstance(200, 130, Image.SCALE_SMOOTH);
        icon.setImage(updateImg);

        JLabel lbImg = new JLabel(icon);
        lbImg.setSize(200, 130);

        JLabel description = new JLabel();
        description.setBackground(Color.RED);
//        description.setSize(150, 200);
        description.setText("<html><body>안녕하세요. 저의 이름은 차혜민 입니다." +
                "<br>저는 백엔드 웹 개발자가 되는 것이 목표입니다." +
                "<br>돈을 모아 해외여행을 하는 것이 목표입니다.😋</body></html>");

        JButton bottomButton = new JButton();
        bottomButton.setText("확인");
        bottomButton.setSize(120, 20);
        bottomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });

        jf.add(lbImg, BorderLayout.WEST);
        jf.add(description, BorderLayout.CENTER);
        jf.add(bottomButton, BorderLayout.SOUTH);

        jf.setLocationRelativeTo(null);

        return jf;
    }


    private void saveText(MemoView view) {

        File file = jfc.getSelectedFile();
        System.out.println("saveText: " + file);

//        setFilename(file.getAbsolutePath(), view);

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(ta.getText());
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFilename(String fileName, MemoView view) {
        this.fileName = fileName;
        view.setTitle(fileName);
    }

    // 불러온 텍스트와 이전에 있던 텍스트가 같은 건지
    private boolean checkIfFilesAreSame(String loadedText) {
        if (loadedText == ta.getText()) {
            return false;
        } else {
            return true;
        }
    }

    // save 할 때
    // 이전에 저장한 텍스트 값과 같은지
    // open하고 텍스트 값과 현재 텍스트 값이 같은지
    private boolean isChanged() {
        if (lastTa.equals(ta.getText())) {
            return false;
        }
        return true;
    }
}
