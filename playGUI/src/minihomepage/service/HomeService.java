package minihomepage.service;

import minihomepage.model.ModelMain;
import minihomepage.model.dao.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeService {
    private User user = null;
    private ModelMain model;

    public HomeService(ModelMain model) {
        this.model = model;
    }

    // 로그인
    public User login(String id, String pwd) {
        System.out.println("LOG::HomeService-19::" + id + "/" + pwd);
        ResultSet rs = null;
        rs = model.selectUser();
        try {
            while (rs.next()) {
                System.out.println("LOG::HomeService-24::" + rs.getString(2) + "/" + rs.getString(4));
                if (id.equals(rs.getString(2)) && pwd.equals(rs.getString(4))) {
                    user = new User();
                    user.setId(rs.getInt(1));
                    user.setEmail(rs.getString(2));
                    user.setNickname(rs.getString(3));
                    user.setPwd(rs.getString(4));
                    System.out.println("LOG::HomeService-30::" + user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
