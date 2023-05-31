package minihomepage.view.structure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewProfileButtons extends JPanel {
    private JButton logIn, logOut, signUp;
    JPanel profileLogin;
    public ViewProfileButtons() {
        setLayout(new GridLayout(0, 1));
        initComponents();
        // 로그인 안 한 경우: 로그인 버튼과 회원가입 버튼이 뜸(로그인 버튼 없애고 로그인 버튼, 회원가입 버튼 추가)
        notLogedIn();
        // 로그인 한 경우: 로그아웃 버튼만 뜸(로그인 버튼과 회원가입 버튼 없애고 로그인버튼 추가)
    }

    void initComponents(){
        logIn = new JButton("로그인");
        logIn.setBackground(new Color(230, 231, 255));
        logOut = new JButton("로그아웃");
        signUp = new JButton("회원가입");
        profileLogin = new ProfileLogin();
    }

    void notLogedIn() {
        add(profileLogin);
        add(logIn);
        add(signUp);
//        add(logOut);

        profileLogin.setVisible(false);
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testAdd();
            }
        });
    }

    void testAdd() {
        profileLogin.setVisible(true);
    }
}
