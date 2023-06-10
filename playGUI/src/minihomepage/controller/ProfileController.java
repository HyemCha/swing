package minihomepage.controller;

import minihomepage.model.ModelMain;
import minihomepage.model.dao.User;
import minihomepage.service.HomeService;
import minihomepage.utility.ImageUtil;
import minihomepage.view.ViewMain;
import minihomepage.view.structure.SignUp;
import minihomepage.view.structure.ViewProfile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController implements ActionListener {
    public HomeService service;
    public ModelMain model;
    public ViewMain view;
    public ViewProfile profile;
    public User user, host;
    public DiaryController diaryController;
    public GuestBookController guestBookController;
    public HomeController homeController;
    public SignUp signUp;
    public String img, path;

    public ProfileController(HomeService service, ViewMain view, ModelMain model) {
        this.model = model;
        this.service = service;
        this.view = view;
        this.profile = view.viewProfile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "로그인" -> {
                user = service.login(profile.getId(), profile.getPwd());
                host = user; // 다이어리나 방명록에서 더블클릭하면 그 사람 정보로 바뀜

                if (user != null) {
                    profile.setNickname(user.getNickname());
                    logIn();
                    // main 함수에서 DiaryController 객체 생성하지 않은 이유:
                    // 로그인 후 user가 누군지 알아야 하는데 main에서 diaryController객체 생성하면
                    // 로그인 하고나서 유저 객체를 보낼 수 없으므로...에..
                    // 나중에 로그인 함수 만들어서 인자로 유저 객체를 보낼까..?(이건 일단 기능 구현하고)
                    // 근데 객체를 스프링 처럼 관리하려면 main에 생성해서 관리하는 게 맞다고 봄
                    diaryController = new DiaryController(this);
                    guestBookController = new GuestBookController(this);
                    homeController = new HomeController(this);
                    view.tabbedPane.addListener(this);
                    System.out.println("imgsrd::"+user.getProfileImg());
                    view.viewProfile.profileImage.getUserImage(user.getProfileImg());
                } else
                    JOptionPane.showMessageDialog(null, "Login Failure.");

            }
            case "로그아웃" -> {
                user = null;
                logOut(); // 로그아웃하면 다른 컨트롤러들에 user 객체 전달
            }
            case "프로필" -> {
                System.out.println("프로필::" + signUp.profile.getIcon());
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("images", ImageIO.getReaderFileSuffixes());
                fileChooser.addChoosableFileFilter(filter);
                if (fileChooser.showOpenDialog(signUp) == JFileChooser.APPROVE_OPTION) {
                    signUp.profile.setIcon(null);
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println("LOG::" + this.getClass().getSimpleName() + "-22::" + fileChooser.getSelectedFile());
                    img = ImageUtil.saveImage(path, "user");
                    ImageIcon icon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                    Image imgg = icon.getImage();
                    double width = 50;
                    double height = (int) (imgg.getHeight(null) * (width / imgg.getWidth(null)));
                    System.out.println("width height::" + width + "/" + height);
                    imgg = imgg.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
                    signUp.profile.setIcon(new ImageIcon(imgg));
                    System.out.println("cmd::87:" + cmd);
                }
            }
            case "회원가입" -> {
                signUp = new SignUp(this);
            }
            case "확인" -> {
                user = new User();
                user.setEmail(signUp.emailTF.getText());
                user.setNickname(signUp.nicknameTF.getText());
                user.setPwd(signUp.pwdTF.getText());
                user.setProfileImg(signUp.profile.getIcon() != null ? img :"image/minihomepage/default.jpg");
                System.out.println("useruser ::" + img + "/" + user.getEmail());
                model.insertUser(user);
                signUp.dispose();
                JOptionPane.showMessageDialog(null, user.getNickname() + "님의 회원가입이 완료되었습니다.");
            }
        }
    }

    private void logIn() {
        profile.logIn(user.getNickname());
        view.tabbedPane.guestBook.checkUserAndHost(user, host);
        view.tabbedPane.diary.logIn(user, host);
    }

    private void logOut() {
        profile.logOut();
    }
}
