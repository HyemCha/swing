package memojang;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemoView extends JFrame {

    private List<JMenuItem> items = new ArrayList<>();

    private List<JMenuItem> editItems = new ArrayList<>();

    private List<JMenuItem> formatItems = new ArrayList<>();

    private List<JMenuItem> showItems = new ArrayList<>();

    private List<JMenuItem> helpItems = new ArrayList<>();

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
        addEditItems();
        addFormatItems();
        addShowItems();
        addHelpItems();
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

        m1.addSeparator();

        items.add(new JMenuItem("끝내기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
        items.get(5).setAccelerator(key);
        m1.add(items.get(5));
    }

    private void addEditItems() {

        KeyStroke key;
        editItems.add(new JMenuItem("실행 취소"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK);
        editItems.get(0).setAccelerator(key);
        editItems.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        m2.add(editItems.get(0));

        m2.addSeparator();

        editItems.add(new JMenuItem("잘라내기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
        editItems.get(1).setAccelerator(key);
        editItems.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.cut();
            }
        });
        m2.add(editItems.get(1));

        editItems.add(new JMenuItem("복사"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK);
        editItems.get(2).setAccelerator(key);
        editItems.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        m2.add(editItems.get(2));

        editItems.add(new JMenuItem("붙여넣기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK);
        editItems.get(3).setAccelerator(key);
        editItems.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.copy();
            }
        });
        m2.add(editItems.get(3));

        editItems.add(new JMenuItem("삭제"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK);
        editItems.get(4).setAccelerator(key);
        editItems.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.cut();
            }
        });
        m2.add(editItems.get(4));

        m2.addSeparator();

        editItems.add(new JMenuItem("찾기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK);
        editItems.get(5).setAccelerator(key);
        m2.add(editItems.get(5));

        editItems.add(new JMenuItem("찾아 바꾸기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK);
        editItems.get(6).setAccelerator(key);
        m2.add(editItems.get(6));

        editItems.add(new JMenuItem("이동"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK);
        editItems.get(7).setAccelerator(key);
        m2.add(editItems.get(7));

        m2.addSeparator();

        editItems.add(new JMenuItem("모두 선택"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK);
        editItems.get(8).setAccelerator(key);
        editItems.get(8).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.selectAll();
            }
        });
        m2.add(editItems.get(8));

        editItems.add(new JMenuItem("시간/날짜"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
        editItems.get(9).setAccelerator(key);
        editItems.get(9).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd aa HH:mm:ss");
                simpleDateFormat.format(date);
                jTextArea.append(simpleDateFormat.format(date));
            }
        });
        m2.add(editItems.get(9));
    }

    private void addFormatItems() {

        KeyStroke key;
        formatItems.add(new JMenuItem("배경 색(B)"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK);
        formatItems.get(0).setAccelerator(key);
        m3.add(formatItems.get(0));

        formatItems.add(new JMenuItem("글자 색(E)"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        formatItems.get(1).setAccelerator(key);
        m3.add(formatItems.get(1));

        formatItems.add(new JMenuItem("글꼴(F)"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        formatItems.get(2).setAccelerator(key);
        m3.add(formatItems.get(2));
    }

    private void addShowItems() {

        KeyStroke key;
        showItems.add(new JMenuItem("확대하기/축소하기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
        showItems.get(0).setAccelerator(key);
        m4.add(showItems.get(0));

        showItems.add(new JMenuItem("상태 표시줄"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        showItems.get(1).setAccelerator(key);
        m4.add(showItems.get(1));
    }

    private void addHelpItems() {

        KeyStroke key;
        helpItems.add(new JMenuItem("도움말 보기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
        helpItems.get(0).setAccelerator(key);
        m5.add(helpItems.get(0));

        helpItems.add(new JMenuItem("피드백 보내기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        helpItems.get(1).setAccelerator(key);
        m5.add(helpItems.get(1));

        helpItems.add(new JMenuItem("메모장 정보"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
        helpItems.get(2).setAccelerator(key);
        m5.add(helpItems.get(2));
    }


    public void addActionListener(ActionListener listener) {
        for (JMenuItem i : items) {
            i.addActionListener(listener);
        }
        for (JMenuItem i : editItems) {
            i.addActionListener(listener);
        }
        for (JMenuItem i : formatItems) {
            i.addActionListener(listener);
        }
        for (JMenuItem i : showItems) {
            i.addActionListener(listener);
        }
        for (JMenuItem i : helpItems) {
            i.addActionListener(listener);
        }
    }
}
