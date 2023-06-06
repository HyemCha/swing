package minihomepage.controller;

import minihomepage.model.ModelMain;
import minihomepage.model.dao.Diary;
import minihomepage.model.dao.User;
import minihomepage.view.ViewMain;
import minihomepage.view.diary.DiaryMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DiaryController {
    ModelMain model;
    public ResultSet rs = null;
    private User user;
    String[] title;
    private Diary diary;
    private Vector<Diary> diaryList = new Vector<>();
    private Vector<String> diaryTitle = new Vector<>();
    private DiaryMain diaryMain;


    public DiaryController(ModelMain model, User user, ViewMain view) {
        this.model = model;
        this.user = user;
        this.diaryMain = view.tabbedPane.diary;
        setDiary();
    }

    public void setDiary() {
        rs = model.selectDiary(user.getId());
        try {
            while (rs.next()) {
                System.out.println("LOG:DiaryController-25::title-" + rs.getString(3));
                diary = new Diary();
                diary.setId(rs.getInt(1));
                diary.setUserId(rs.getInt(2));
                diary.setTitle(rs.getString(3));
                diary.setContent(rs.getString(4));
                diary.setImgUrl(rs.getString(5));
                diary.setCreateAt(rs.getTimestamp(6));

                diaryTitle.add(diary.getTitle());
                diaryList.addElement(diary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        diaryMain.initDiaryDatas(diaryTitle, diaryList);
    }

}
