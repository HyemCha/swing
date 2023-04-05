package memojang;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class MemoModel {
    private JTextArea ta;

    private JFileChooser jfc;

    int re;

    boolean isNew = false;

    public MemoModel(MemoView view) {
        this.ta = view.getjTextArea();
        jfc = new JFileChooser();
    }

    public void createNew() {
        ta.setText("");
    }

    public void open(MemoView view){
        re = jfc.showOpenDialog(view);
        System.out.println("re when open: " + re);
        if (re == JFileChooser.APPROVE_OPTION) {

            isNew = true;

            File file = jfc.getSelectedFile();
            FileReader fr = null;
            String data = "";

            int ch;

            try {
                fr = new FileReader(file);
                while ((ch = fr.read()) != -1){
                    data += (char)ch;
                }
                ta.setText(data);
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save(MemoView view) {
        if (isNew){
            saveText(view);
        } else {
            re = jfc.showSaveDialog(view);
            if (re == JFileChooser.APPROVE_OPTION) {
                saveText(view);
            }
        }


    }


    public void saveAs(MemoView view) {
        re = jfc.showSaveDialog(view);
        if (re == JFileChooser.APPROVE_OPTION) {
            saveText(view);
        }
    }

    public void quit(MemoView view) {
        String[] buttons = {"저장","저장 안 함", "취소"};
        int result = JOptionPane.showOptionDialog(
                null,
                "변경 내용을 저장하시겠습니까?",
                "Memojang",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                buttons,
                "저장");

        System.out.println("model quit Result: " + result);
        switch (result) {
            case JOptionPane.YES_NO_OPTION -> {
                save(view);
                view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
            case JOptionPane.NO_OPTION -> view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }


    private void saveText(MemoView view) {
        File file = jfc.getSelectedFile();
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(ta.getText());
            fw.close();

            JOptionPane.showMessageDialog(view, "파일을 저장했습니다.");
            isNew = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
