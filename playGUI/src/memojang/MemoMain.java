package memojang;

public class MemoMain {
    public MemoMain() {

    }

    public static void main(String[] args) {
        MemoView view = new MemoView();
        MemoModelInterface model = new MemoModel2(view);
        MemoController controller = new MemoController(view, model);
        view.setVisible(true);
    }
}
