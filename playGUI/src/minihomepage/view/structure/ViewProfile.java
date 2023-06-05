package minihomepage.view.structure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewProfile extends JPanel {
    public ViewProfileImage profileImage;
    public JLabel description;
    public ViewProfileButtons buttons;

    private String userName = "user name";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ViewProfile() {
//        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder(userName));
        setPreferredSize(new Dimension(200, 100));

        profileImage = new ViewProfileImage();
        add(profileImage);

        description = new JLabel("user description");
        add(description);

        buttons = new ViewProfileButtons();
        add(buttons, BorderLayout.SOUTH);
    }

    public String getId() {
        return buttons.profileLogin.getId();
    }

    public String getPwd() {
        return buttons.profileLogin.getPwd();
    }

    public void setNickname(String nickname) {
        description.setText(nickname);
    }

    public void addActionListener(ActionListener listener) {
        buttons.addActionListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
