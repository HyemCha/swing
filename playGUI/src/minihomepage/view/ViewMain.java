package minihomepage.view;

import javax.swing.*;

public class ViewMain extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel mainPanel, panel1;

    public ViewMain() {
        setTitle("MiniHomePage");
        setBounds(500, 300, 1000, 700);
        setVisible(true);
    }

    public void initWindow() {
        tabbedPane = new JTabbedPane();
        mainPanel = new JPanel();
        panel1 = new JPanel();
    }

    public void addTab() {
        add(mainPanel);
        mainPanel.add(tabbedPane);
        tabbedPane.addTab("홈", panel1);
    }
}
