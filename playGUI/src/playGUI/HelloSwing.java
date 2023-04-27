package playGUI;

public class HelloSwing {
    public HelloSwing() {
        HelloSwingView view = new HelloSwingView();
        HelloSwingModel model = new HelloSwingModel();
        HelloSwingController controller = new HelloSwingController(model, view);
        view.setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new HelloSwing();
    }
}
