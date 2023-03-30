package memojang;

import javax.swing.*;
import java.io.*;

public class MemoModel {
    private JTextArea ta;

    private JFileChooser jfc;

    boolean isNew = false;

    public MemoModel(MemoView view) {
        this.ta = view.getjTextArea();
        jfc = new JFileChooser();
    }

    public void createNew(JTextArea ta) {
        ta.setText("");
    }

    public void open(MemoView view){
        int re = jfc.showOpenDialog(view);
        if (re == JFileChooser.APPROVE_OPTION) {
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
        int re = jfc.showSaveDialog(view);
        if (re == JFileChooser.APPROVE_OPTION) {
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

    public void saveAs() {

    }

    public void quit() {

    }
}
