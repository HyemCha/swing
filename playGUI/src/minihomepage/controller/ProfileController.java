package minihomepage.controller;

import minihomepage.model.dao.User;
import minihomepage.service.HomeService;
import minihomepage.view.structure.ViewProfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController implements ActionListener {
    private HomeService service;
    private ViewProfile profile;
    public User user;

    public ProfileController(HomeService service, ViewProfile profile) {
        this.service = service;
        this.profile = profile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "로그인" -> {
                user = service.login(profile.getId(), profile.getPwd());

                if (user != null) {
                    System.out.println("LOG::ProfileController-31::" + user.getId());
                    profile.setNickname(user.getNickname());
                    profile.logedIn();
                }else
                    JOptionPane.showMessageDialog(null, "Login Failure.");

            }
            case "로그아웃" -> {
                user = null;
                profile.logedOut();
            }
        }
    }
}
