package playGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloSwingController implements ActionListener, MouseListener {
    private HelloSwingModel model;
    private HelloSwingView view;
    private List<Member> list = null;
    private DefaultTableModel dtm = null;

    private JTable table = null;

    private String[] info = new String[4];

    private int rowNum;

    private int colNum;

    private String uField;

    private String hp;

    public HelloSwingController(HelloSwingModel model, HelloSwingView view) {
        this.model = model;
        this.view = view;
        this.view.addActionListener(this);
        this.view.addMouseListener(this);
        this.dtm = view.getDtm();
        this.table = view.getTable();
    }

    ResultSet rs = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("정보 조회")) {
//            model.tableReset();
            if(dtm.getRowCount() > 0){
                dtm.setRowCount(0);
            }
            try {
                list = model.select();
                for (Member l : list) {

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
            if (view.getMyName().getText() != null && view.getMyPhone().getText() != null) {
                Member member = new Member();
                member.setName(view.getMyName().getText());
                member.setPh(view.getMyPhone().getText());
                member.setEmail(view.getMyEmail().getText());
                member.setAge(Integer.parseInt(view.getMyAge().getText()));
                model.insert(member);

                clearAndSelect();
            }

        } else if (cmd.equals("정보 검색")) {
            try {
                list = model.search(view.getMyName().getText());
                for (Member l : list) {
                    info[0] = l.getName();
                    info[1] = l.getPh();
                    info[2] = l.getEmail();
                    info[3] = Integer.toString(l.getAge());
                    dtm.addRow(info);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (cmd.equals("정보 삭제")) {
            model.delete(view.getMyName().getText());

            clearAndSelect();
        } else if (cmd.equals("정보 수정")) {
            if (colNum == 0) {
                uField = view.getMyName().getText();
            } else if (colNum == 1) {
                uField = view.getMyPhone().getText();
                return;
            } else if (colNum == 2) {
                uField = view.getMyEmail().getText();
            } else if (colNum == 3) {
                uField = view.getMyAge().getText();
            }
            System.out.println("cont: " + uField);
            try {
                model.update(colNum, uField, hp);
//                dtm.removeRow(rowNum);

                clearAndSelect();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (cmd.equals("선택 삭제")) {
            try {
                model.deleteRow(hp);
//                dtm.removeRow(rowNum);

                clearAndSelect();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (cmd.equals("행 초기화")) {

        }

        clearField();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e);
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        rowNum = row;
        colNum = col;
        hp = table.getModel().getValueAt(row, 1).toString();
        String field = table.getModel().getValueAt(row, col).toString();

        if (col == 0){
            view.getMyName().setText(field);
        } else if (col == 1) {
            field = "변경불가";
//            view.getMyPhone().setText(field);
        } else if (col == 2) {
            view.getMyEmail().setText(field);
        } else if (col == 3) {
            view.getMyAge().setText(field);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void clearField(){
        view.getMyName().setText("");
        view.getMyPhone().setText("");
        view.getMyEmail().setText("");
        view.getMyAge().setText("");
    }

    public void clearAndSelect() {
        dtm.setRowCount(0);
        try {
            model.select();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
