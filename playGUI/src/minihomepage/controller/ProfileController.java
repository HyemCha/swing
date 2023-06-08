package minihomepage.controller;

import minihomepage.model.ModelMain;
import minihomepage.model.dao.User;
import minihomepage.service.HomeService;
import minihomepage.view.ViewMain;
import minihomepage.view.structure.ViewProfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController implements ActionListener {
    private HomeService service;
    private ModelMain model;
    private ViewMain view;
    private ViewProfile profile;
    public User user;
    DiaryController diaryController;
    GuestBookController guestBookController;
    HomeController homeController;

    public ProfileController(HomeService service, ViewMain view, ModelMain model) {
        this.model = model;
        this.service = service;
        this.view = view;
        this.profile = view.viewProfile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "로그인" -> {
                user = service.login(profile.getId(), profile.getPwd());

                if (user != null) {
                    profile.setNickname(user.getNickname());
                    logIn();
                    // main 함수에서 DiaryController 객체 생성하지 않은 이유:
                    // 로그인 후 user가 누군지 알아야 하는데 main에서 diaryController객체 생성하면
                    // 로그인 하고나서 유저 객체를 보낼 수 없으므로...에..
                    // 나중에 로그인 함수 만들어서 인자로 유저 객체를 보낼까..?(이건 일단 기능 구현하고)
                    // 근데 객체를 스프링 처럼 관리하려면 main에 생성해서 관리하는 게 맞다고 봄
                    diaryController = new DiaryController(model, user, view);
                    guestBookController = new GuestBookController(model, user, view);
                    homeController = new HomeController(model, user, view);
                }else
                    JOptionPane.showMessageDialog(null, "Login Failure.");

            }
            case "로그아웃" -> {
                user = null;
                logOut();
            }
        }
    }

    private void logIn() {
        profile.logIn();
    }

    private void logOut() {
        profile.logOut();
    }
}
