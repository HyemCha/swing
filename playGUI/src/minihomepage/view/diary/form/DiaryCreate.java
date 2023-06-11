package minihomepage.view.diary.form;

import minihomepage.controller.DiaryController;
import minihomepage.controller.DiaryDetailController;
import minihomepage.controller.DiarySubController;
import minihomepage.model.dao.Diary;
import minihomepage.model.dao.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DiaryCreate extends JFrame {
    public final static boolean EDIT = true;
    public JTextField title;
    public JPanel imgAndText, images;
    public JScrollPane scrollPane;
    public JTextPane textPane;
    public JPanel buttons;
    public JButton file, save, cancel;
    public JFileChooser fileChooser;
    private DiaryController controller;

    public DiaryCreate(User user, int cnt) {
        System.out.println("LOG::" + this.getClass().getSimpleName() + "-25::" + user.getId() + "/cnt:" + cnt);
        initComponents();
    }

    public DiaryCreate(DiaryDetailController listener) {
        initComponents();
        file.setEnabled(false);
        addListener(listener);
    }

    public void initComponents() {
        fileChooser = new JFileChooser();

        setVisible(true);
        setLayout(new BorderLayout());
        setBounds(0, 0, 500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setTitle(u);

        title = new JTextField();
        add(title, BorderLayout.NORTH);

        imgAndText = new JPanel(new BorderLayout());
        add(imgAndText);

        images = new JPanel(new FlowLayout());
        images.setPreferredSize(new Dimension(500, 80));
        imgAndText.add(images, BorderLayout.NORTH);

        textPane = new JTextPane();

        scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        imgAndText.add(scrollPane);

        buttons = new JPanel(new FlowLayout());
        add(buttons, BorderLayout.SOUTH);

        file = new JButton("파일");
        save = new JButton("저장");
        cancel = new JButton("취소");
        buttons.add(file);
        buttons.add(save);
        buttons.add(cancel);
    }

    public void addListener(ActionListener listener) {
        file.addActionListener(listener);
        save.addActionListener(listener);
        cancel.addActionListener(listener);
    }


}
