package calculator;

public class CalculatorMain {
    public CalculatorMain() {
        CalculatorView view = new CalculatorView();
        CalculatorModel model = new CalculatorModel();
        CalculatorController controller = new CalculatorController();
        view.setVisible(true);
    }
}
