package minihomepage.controller;

import minihomepage.model.ModelMain;
import minihomepage.model.dao.Diary;
import minihomepage.model.dao.GuestBook;
import minihomepage.model.dao.User;
import minihomepage.view.ViewMain;
import minihomepage.view.home.HomeMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController {
    public User user;
    public ResultSet diaryRs, guestBookRs = null;
    public Map<String, ResultSet> map;
    public List<Diary> diaryList;
    public List<GuestBook> guestBookList;
    public Diary diary;
    public GuestBook guestBook;
    ModelMain model;
    public HomeMain homeMain;

    public HomeController(ModelMain model, User user, ViewMain view) {
        this.model = model;
        this.user = user;
        this.homeMain = view.tabbedPane.home;
        setHome();
    }

    public void setHome() {
        map = model.selectHome(user.getId());
        diaryRs = map.get("diary");
        guestBookRs = map.get("guestBook");

        diaryList = new ArrayList<>();
        try {
            while (diaryRs.next()) {
                diary = new Diary();
                diary.setUserId(diaryRs.getInt(2));
                diary.setTitle(diaryRs.getString(3));
                diary.setImgUrl(diaryRs.getString(4));
                diary.setCreateAt(diaryRs.getTimestamp(5));

                diaryList.add(diary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        guestBookList = new ArrayList<>();
        try {
            while (guestBookRs.next()) {
                guestBook = new GuestBook();
                guestBook.setUserId(guestBookRs.getInt(2));
                guestBook.setContent(guestBookRs.getString(3));
                guestBook.setCreateAt(guestBookRs.getTimestamp(4));

                guestBookList.add(guestBook);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        homeMain.initHome(diaryList, guestBookList);
    }
}
