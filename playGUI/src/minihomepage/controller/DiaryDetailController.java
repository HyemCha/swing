package minihomepage.controller;

import minihomepage.model.ModelMain;
import minihomepage.model.dao.Diary;
import minihomepage.view.diary.DiaryDatas;
import minihomepage.view.diary.form.DiaryCreate;
import minihomepage.view.diary.form.DiaryDetail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiaryDetailController implements ActionListener {
    private DiaryDetail diaryDetail;
    private ModelMain model;
    private DiaryController diaryController;
    private Diary diary;
    private DiaryCreate updateDiary;
    String cmd;

    public DiaryDetailController(DiaryController diaryController) {
        this.diaryController = diaryController;
        this.model = diaryController.model;
        this.diaryDetail = diaryController.diaryDetail;
        this.diary = diaryController.diaryDetail.diary;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cmd = e.getActionCommand();
        switch (cmd) {
            case "수정" : {
                updateDiary = new DiaryCreate(this);
                updateDiary.title.setText(diary.getTitle());
                updateDiary.textPane.setText(diary.getContent());
                diaryDetail.dispose();
                break;
            }

            case "삭제" : {
                System.out.println("cmd::48:" + cmd);
                diaryDetail.dispose();
                int id = diaryDetail.diary.getId();
                System.out.println("LOG::" + this.getClass().getSimpleName() + "-76::" + id);
                model.deleteDiary(id);
                diaryController.resetDiary();
                break;
            }

            case "저장" : {
                diary.setTitle(updateDiary.title.getText());
                diary.setContent(updateDiary.textPane.getText());
                model.updateDiay(diary);
                updateDiary.dispose();
                DiaryDetail detail = new DiaryDetail(diary);
                detail.addListener(this);
                break;
            }

            case "취소" : {
                updateDiary.dispose();
                System.out.println("cmd::54:" + cmd);
                break;
            }
        }
    }
}
