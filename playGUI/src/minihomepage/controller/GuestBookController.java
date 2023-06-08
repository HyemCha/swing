package minihomepage.controller;

import minihomepage.model.ModelMain;
import minihomepage.model.dao.GuestBook;
import minihomepage.model.dao.User;
import minihomepage.view.ViewMain;
import minihomepage.view.guestbook.GuestBookMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GuestBookController {
    public User user;
    public ResultSet rs = null;
    ModelMain model;
    private Vector<GuestBook> guestBookList = new Vector<>();
    private GuestBook guestBook;
    public GuestBookMain guestBookMain;

    public GuestBookController(ModelMain model, User user, ViewMain view) {
        this.model = model;
        this.user = user;
        this.guestBookMain = view.tabbedPane.guestBook;
        setGuestBook();
    }

    public void setGuestBook() {
        rs = model.selectGuestBook(user.getId(), false);
        try {
            while (rs.next()) {
                guestBook = new GuestBook();
//                guestBook.setId(rs.getInt(1));
                guestBook.setHostNickname(rs.getString(6));
                guestBook.setContent(rs.getString(3));
                guestBook.setCreateAt(rs.getTimestamp(4));
//                guestBook.setHostId(rs.getInt(6));

                guestBookList.add(guestBook);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        guestBookMain.initGuestBook(guestBookList);
    }
}
