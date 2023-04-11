package memojang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class MemoModel implements MemoModelInterface{

    String fileName = "";

    private JTextArea ta;

    private JFileChooser jfc;

    int re;

    String lastTa = "";

    // textArea에 있는 글이 새로운 글인지
    boolean isNew = false;

    boolean change = false;

    public MemoModel(MemoView view) {
        this.ta = view.getjTextArea();
        jfc = new JFileChooser();
    }

    public void createNew() {
        ta.setText("");
        isNew = false;
    }

    public void open(MemoView view){
        re = jfc.showOpenDialog(view);
        if (ta.getText().equals("")){
            openCommon(view);
        } else {
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
                while ((ch = fr.read()) != -1){
                    data += (char)ch;
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
        if (!isNew && change){
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

        switch (result) {
            case JOptionPane.YES_NO_OPTION -> {
                save(view);
                view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
            case JOptionPane.NO_OPTION -> {
//                view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        }
    }

    @Override
    public JFrame description(MemoView view) {
        return null;
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
        if (lastTa.equals(ta.getText())){
            return false;
        }
        return true;
    }
}
