package playGUI;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloSwingController implements ActionListener {
    private HelloSwingModel model;
    private HelloSwingView view;
    private List<Member> list = new ArrayList<>();
    private DefaultTableModel dtm = null;
    private String[] info = new String[4];

    public HelloSwingController(HelloSwingModel model, HelloSwingView view) {
        this.model = model;
        this.view = view;
        this.view.addActionListener(this);
        this.dtm = view.getDtm();
    }

    ResultSet rs = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("정보 조회")) {
            try {
                list = model.select();
                for (Member l : list) {
                    System.out.println("controller-" + l);

                    info[0] = l.getName();
                    info[1] = l.getPh();
                    info[2] = l.getEmail();
                    info[3] = Integer.toString(l.getAge());
                    dtm.addRow(info);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (cmd.equals("정보 추가")) {

        } else if (cmd.equals("정보 검색")) {

        } else if (cmd.equals("정보 삭제")) {

        } else if (cmd.equals("정보 수정")) {

        } else if (cmd.equals("선택 삭제")) {

        } else if (cmd.equals("행 초기화")) {

        }
    }
}
