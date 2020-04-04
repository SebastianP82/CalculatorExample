package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Controller {
    @FXML private TextField calculateTextField;
    @FXML private Label calculateLabel;

    private StringBuilder result = new StringBuilder();

    @FXML
    public void setNumericButtonText(ActionEvent e) {
        result.append(((Button) e.getSource()).getText());
        calculateLabel.setText(result.toString());
    }

    @FXML
    public void setNotNumericButtonText(ActionEvent e) {
        result.append(" ")
                .append(((Button) e.getSource()).getText())
                .append(" ");
        calculateLabel.setText(result.toString());
    }

    @FXML
    public void removeLastIndex() {
        if(result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
            calculateLabel.setText(result.toString());
        }
    }

    @FXML
    public void getResult() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String totalResult = calculateLabel.getText();
        try {
            Object resultEngine = engine.eval(totalResult);
            calculateTextField.setText(resultEngine.toString());
            calculateLabel.setText("");
            result.delete(0, result.length());
        } catch (Exception e) {
            calculateTextField.setText("NaN");
        }
    }

    @FXML
    public void clearAll() {
        if(result.length() > 0) {
            result.delete(0, result.length());
            calculateLabel.setText("");
            calculateTextField.setText("0");
        }
    }
}
