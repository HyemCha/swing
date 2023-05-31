package minihomepage.view.structure;

import javax.swing.*;
import java.awt.*;

public class ViewProfile extends JPanel {
    private ViewProfileImage profileImage;
    private JLabel description;
    private JPanel buttons;

    public ViewProfile() {
//        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("This is Profile"));
        setPreferredSize(new Dimension(200, 100));

        profileImage = new ViewProfileImage();
        add(profileImage);

        description = new JLabel("user description");
        add(description);

        buttons = new ViewProfileButtons();
        add(buttons, BorderLayout.SOUTH);
    }

}
