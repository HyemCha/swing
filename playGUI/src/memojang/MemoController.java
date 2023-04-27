package memojang;

import memojang.toolbar.ToolBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoController implements ActionListener {
    private MemoView view;
    private MemoModelInterface model;

    public MemoController(MemoView view, MemoModelInterface model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String tool_cmd = null;
        tool_cmd = e.getSource().toString();
        if(tool_cmd != null) cmd = tool_cmd;

        switch (cmd){
            case "새로 만들기", "newBtn" -> model.createNew();
            case "열기", "openBtn" -> model.open(view);
            case "저장", "saveBtn" -> model.save(view);
            case "다른 이름으로 저장" -> model.saveAs(view);
            case "끝내기", "exitBtn" -> model.quit(view);
            case "찾기" -> model.find(view);
            case "찾아 바꾸기" -> model.findAndReplace(view);
            case "메모장 정보" -> model.description(view);
        }
    }
}
