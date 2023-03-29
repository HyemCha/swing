package playGUI;

public class HelloSwing {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HelloSwingView view = new HelloSwingView();
        HelloSwingModel model = new HelloSwingModel();
        HelloSwingController controller = new HelloSwingController(model, view);
        view.setVisible(true);
    }
}
