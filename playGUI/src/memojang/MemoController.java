package memojang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoController implements ActionListener {
    private MemoView view;
    private MemoModel model;

    public MemoController(MemoView view, MemoModel model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case "새로 만들기" -> model.createNew(view.getjTextArea());
            case "열기" -> model.open(view);
            case "저장" -> model.save(view);
        }
    }
}
