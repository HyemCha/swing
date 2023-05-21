package minihomepage.view.structure;

import javax.swing.*;
import java.awt.*;

public class ViewProfile extends JPanel {
    private ViewProfileImage profileImage;
    private JLabel description;

    public ViewProfile() {
        setBorder(BorderFactory.createTitledBorder("This is Profile"));
        setPreferredSize(new Dimension(200, 100));

        profileImage = new ViewProfileImage();
        add(profileImage);

        description = new JLabel("user description");
        add(description);
    }

}
