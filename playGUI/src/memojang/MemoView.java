package memojang;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MemoView extends JFrame {

    private List<JMenuItem> items = new ArrayList<>();

    private JScrollPane jScrollPane;

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    private JTextArea jTextArea;
    private JMenuBar mb;

    private JMenu m1, m2, m3, m4, m5;

    private ActionListener listener;


    public MemoView(){
        initWindow();
        initComponents();
        addComponents();
    }

    private void initWindow() {
        setTitle("Hyemin's Memojang");
        setBounds(800, 200, 500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        jTextArea = new JTextArea();
        jScrollPane = new JScrollPane(jTextArea);

        m1 = new JMenu("파일(F)");
        m2 = new JMenu("편집(E)");
        m3 = new JMenu("서식(O)");
        m4 = new JMenu("보기(V)");
        m5 = new JMenu("도움말(H)");

        mb = new JMenuBar();
    }

    private void addComponents() {
        this.add(jScrollPane);

        this.setJMenuBar(mb);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        mb.add(m5);

        setShortcutKey();
        addMenuItems();
    }

    private void setShortcutKey() {
        m1.setMnemonic(KeyEvent.VK_F);
        m2.setMnemonic(KeyEvent.VK_E);
        m3.setMnemonic(KeyEvent.VK_O);
        m4.setMnemonic(KeyEvent.VK_V);
        m5.setMnemonic(KeyEvent.VK_H);
    }

    private void addMenuItems() {

        KeyStroke key;
        items.add(new JMenuItem("새로 만들기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
        items.get(0).setAccelerator(key);
        m1.add(items.get(0));

        items.add(new JMenuItem("새 창"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        items.get(1).setAccelerator(key);
        m1.add(items.get(1));

        items.add(new JMenuItem("열기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
        items.get(2).setAccelerator(key);
        m1.add(items.get(2));

        items.add(new JMenuItem("저장"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
        items.get(3).setAccelerator(key);
        m1.add(items.get(3));

        items.add(new JMenuItem("다른 이름으로 저장"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        items.get(4).setAccelerator(key);
        m1.add(items.get(4));

        items.add(new JMenuItem("끝내기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
        items.get(5).setAccelerator(key);
        m1.add(items.get(5));
    }

    public void addActionListener(ActionListener listener) {
        for (JMenuItem i : items) {
            i.addActionListener(listener);
        }
    }
}
