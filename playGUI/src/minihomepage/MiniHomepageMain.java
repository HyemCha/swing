package minihomepage;

import minihomepage.controller.DiaryController;
import minihomepage.controller.ProfileController;
import minihomepage.model.ModelMain;
import minihomepage.service.HomeService;
import minihomepage.view.ViewMain;

import javax.swing.*;

public class MiniHomepageMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                ViewMain view = new ViewMain();
                ModelMain model = new ModelMain();
                HomeService service = new HomeService(model);
                ProfileController controller = new ProfileController(service, view, model);
                view.addActionListener(controller);
            }
        });
    }
}
