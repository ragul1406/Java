import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {
    private TextField display = new TextField();
    private double num1 = 0;
    private String operator = "";
    private boolean start = true;

    @Override
    public void start(Stage stage) {
        display.setEditable(false);
        display.setPrefHeight(50);

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(display, 0, 0, 4, 1);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (int i = 0; i < buttons.length; i++) {
            String text = buttons[i];
            Button btn = new Button(text);
            btn.setPrefSize(50, 50);
            btn.setOnAction(e -> handleButton(text));
            pane.add(btn, i % 4, 1 + i / 4);
        }

        Scene scene = new Scene(pane, 220, 300);
        stage.setScene(scene);
        stage.setTitle("Easy Calculator");
        stage.show();
    }

    private void handleButton(String text) {
        if (text.matches("[0-9]")) {
            if (start) {
                display.clear();
                start = false;
            }
            display.appendText(text);
        } else if (text.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = text;
            start = true;
        } else if (text.equals("=")) {
            double num2 = Double.parseDouble(display.getText());
            double result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> (num2 == 0) ? 0 : num1 / num2;
                default -> 0;
            };
            display.setText(String.valueOf(result));
            start = true;
        } else if (text.equals("C")) {
            display.clear();
            num1 = 0;
            operator = "";
            start = true;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
