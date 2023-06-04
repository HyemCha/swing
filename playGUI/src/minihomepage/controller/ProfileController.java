package minihomepage.controller;

import minihomepage.model.dao.User;
import minihomepage.service.HomeService;
import minihomepage.view.home.Home;
import minihomepage.view.structure.ViewProfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController implements ActionListener {
    private HomeService service;
    private ViewProfile profile;
    public User user = new User();

    public ProfileController(HomeService service, ViewProfile profile) {
        this.service = service;
        this.profile = profile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "로그인" -> {
                System.out.println("LOG::ProfileController-27::" + profile.getId());
                user = service.login(profile.getId(), profile.getPwd());
                if (user != null)
                    profile.setUserName(user.getNickname());
                else
                    JOptionPane.showMessageDialog(null, "Login Failure.");

                System.out.println("LOG::ProfileController-34::로그인 test-" + user.getNickname());
            }
        }
    }
}
