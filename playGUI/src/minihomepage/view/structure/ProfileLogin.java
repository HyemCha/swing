package minihomepage.view.structure;

import javax.swing.*;
import java.awt.*;

public class ProfileLogin extends JPanel {
    public JLabel idL, pwL;
    public JTextField id;
    public JPasswordField pwd;
    public ProfileLogin() {
        setLayout(new GridLayout(0, 2));
        idL = new JLabel("ID : ");
        id = new JTextField();
        pwL = new JLabel("PASSWORD : ");
        pwd = new JPasswordField();

        add(idL);
        add(id);
        add(pwL);
        add(pwd);
    }

    public String getId() {
        return id.getText().toString();
    }

    public String getPwd() {
        return pwd.getText();
    }
}
