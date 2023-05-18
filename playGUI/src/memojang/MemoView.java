package memojang;

import calendar.MemoCalendar;
import memojang.format.FontStyleView;
import memojang.toolbar.ToolBar;
import playGUI.HelloSwing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
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

    private List<JMenuItem> etcItems = new ArrayList<>();


    private JScrollPane jScrollPane;

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    private JTextArea jTextArea;
    private JMenuBar mb;

    private JMenu m1, m2, m3, m4, m5, m6;

    private ActionListener listener;

    private FontStyleView fontStyleView;

    private JCheckBoxMenuItem checkBoxMenuItem;

    private JButton newBtn, openBtn, saveBtn, exitBtn, copyBtn, pasteBtn, cutBtn, fontBtn, colBtn;

    private ToolBar toolBar;

    private StatusBar statusBar;

    private List<JButton> btns = new ArrayList<>();

    public MemoView(){
        initWindow();
        initComponents();
        addComponents();
    }

    private void initWindow() {
        setTitle("Hyemin's Memojang");
        setBounds(800, 200, 800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        jTextArea = new JTextArea();
        jScrollPane = new JScrollPane(jTextArea);

        btns.add(newBtn);
        btns.add(openBtn);
        btns.add(saveBtn);
        btns.add(exitBtn);
        btns.add(copyBtn);
        btns.add(pasteBtn);
        btns.add(cutBtn);
        btns.add(fontBtn);
        btns.add(colBtn);

        ImageIcon newIcon;
        String fileName[] = {
                "image/save_icon.png",
                "image/copy_icon.png",
                "image/floppy.png",
                "image/save_icon.png",
                "image/save_icon.png",
                "image/copy_icon.png",
                "image/floppy.png",
                "image/save_icon.png",
                "image/save_icon.png"
        };
        String txt[] = {
                "새파일 작성", "문서 가져오기", "문서 저장", "문서 작성 종료",
                "복사", "붙여넣기", "자르기", "글꼴 바꾸기", "글자색 바꾸기"
        };

        toolBar = new ToolBar();

        int i = 0;
        for (JButton jBtn: btns) {
            newIcon = new ImageIcon(fileName[i]);
            jBtn = new JButton(newIcon);
            jBtn.setToolTipText(txt[i]);
            toolBar.add(jBtn);
            i++;
        }


        statusBar = new StatusBar(jTextArea);

        m1 = new JMenu("파일(F)");
        m2 = new JMenu("편집(E)");
        m3 = new JMenu("서식(O)");
        m4 = new JMenu("보기(V)");
        m5 = new JMenu("도움말(H)");
        m6 = new JMenu("잡동사니");

        mb = new JMenuBar();
    }

    private void addComponents() {
        this.add(jScrollPane);
        this.add(BorderLayout.SOUTH, statusBar);
        this.add(BorderLayout.NORTH, toolBar);

        this.setJMenuBar(mb);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        mb.add(m5);
        mb.add(m6);


        setShortcutKey();

        addMenuItems();
        addEditItems();
        addFormatItems();
        addShowItems();
        addHelpItems();
        addEtc();
    }

    public void update() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
        formatItems.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.yellow);
                if(setColor != null)
                    jTextArea.setBackground(setColor);
            }
        });
        m3.add(formatItems.get(0));

        formatItems.add(new JMenuItem("글자 색(E)"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        formatItems.get(1).setAccelerator(key);
        formatItems.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.BLUE);
                if(setColor != null)
                    jTextArea.setForeground(setColor);
            }
        });
        m3.add(formatItems.get(1));

        formatItems.add(new JMenuItem("글꼴(F)"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        formatItems.get(2).setAccelerator(key);
        formatItems.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontStyleView = new FontStyleView(jTextArea);
                fontStyleView.setBounds(500, 300, 400, 300);
                fontStyleView.setVisible(true);
            }
        });
        m3.add(formatItems.get(2));
    }

    private void addShowItems() {

        KeyStroke key;
        JMenu menu = new JMenu("확대하기/축소하기");
        showItems.add(menu);
        m4.add(showItems.get(0));

        menu.add(new JMenuItem("확대(I)")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font font = jTextArea.getFont();
                String fontName = font.getName();
                int fontStyle = font.getStyle();
                int fontSize = font.getSize() + 2;
                font = new Font(fontName, fontStyle, fontSize);
                jTextArea.setFont(font);
            }
        });
        menu.add(new JMenuItem("축소(O)")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                Font font = jTextArea.getFont();
                String fontName = font.getName();
                int fontStyle = font.getStyle();
                int fontSize = font.getSize() - 2;
                font = new Font(fontName, fontStyle, fontSize);
                jTextArea.setFont(font);
            }
        });

        menu.add(new JMenuItem("확대하기/축소하기 기본값 복원")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font font = jTextArea.getFont();
                String fontName = font.getName();
                int fontStyle = font.getStyle();
                int fontSize = 12;
                font = new Font(fontName, fontStyle, fontSize);
                jTextArea.setFont(font);
            }
        });

        m4.addSeparator();

        checkBoxMenuItem = new JCheckBoxMenuItem("상태 표시줄(S)");
        checkBoxMenuItem.setState(true);
        checkBoxMenuItem.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (checkBoxMenuItem.getState() == true) {
                    statusBar.setVisible(true);
                }else{
                    statusBar.setVisible(false);
                }
            }
        });
        m4.add(this.checkBoxMenuItem);
    }

    private void addHelpItems() {

        KeyStroke key;
        helpItems.add(new JMenuItem("도움말 보기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
        helpItems.get(0).setAccelerator(key);
        helpItems.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        m5.add(helpItems.get(0));

        helpItems.add(new JMenuItem("피드백 보내기"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
        helpItems.get(1).setAccelerator(key);
        helpItems.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        m5.add(helpItems.get(1));

        helpItems.add(new JMenuItem("메모장 정보"));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
        helpItems.get(2).setAccelerator(key);
        helpItems.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemoInfo();
            }
        });
        m5.add(helpItems.get(2));
    }

    private void addEtc() {
        etcItems.add(new JMenuItem("폰북"));
        etcItems.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelloSwing();
            }
        });
        m6.add(etcItems.get(0));

        etcItems.add(new JMenuItem("그 외"));
        etcItems.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        m6.add(etcItems.get(1));

        etcItems.add(new JMenuItem("캘린더"));
        etcItems.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemoCalendar();
            }
        });
        m6.add(etcItems.get(2));
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



    public JButton getNewBtn() {
        return newBtn;
    }
}
