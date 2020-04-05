package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.mariuszgromada.math.mxparser.Expression;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller {
    @FXML
    private TextField calculateTextField;
    @FXML
    private Label calculateLabel;

    private StringBuilder result = new StringBuilder(); //string used to build mathematical operation

    /**
     * The method used to enter number and operation sign from keyboard
     *
     * @param keyEvent get text from keyboard
     */
    @FXML
    public void keyPressedMethod(KeyEvent keyEvent) {
        String numericFromKeyboard = keyEvent.getCharacter(); // text from keyboard

        Pattern numPattern = Pattern.compile("[0-9]"); // pattern for number
        Matcher numMatcher = numPattern.matcher(numericFromKeyboard); // matcher for number
        if (numMatcher.matches()) {
            result.append(numericFromKeyboard);
        }

        Pattern notNumPattern = Pattern.compile("[-+/*]"); //pattern for +-/*
        Matcher notNumMatcher = notNumPattern.matcher(numericFromKeyboard);
        if (notNumMatcher.matches()) {
            if (result.length() > 0 && isNumber(result.substring(result.length() - 1))) {
                result.append(" ")
                        .append(numericFromKeyboard)
                        .append(" ");
            } else if (result.length() > 0) {
                result.replace(result.length() - 2, result.length() - 1, numericFromKeyboard);
            } else {
                result.append("0 ")
                        .append(numericFromKeyboard)
                        .append(" ");
            }
        }
        calculateLabel.setText(result.toString());
    }

    /**
     * The method used to add numeric char to string
     *
     * @param e used to enter char from keyboard
     */
    @FXML
    public void setNumericButtonText(ActionEvent e) {
        result.append(((Button) e.getSource()).getText());
        calculateLabel.setText(result.toString());
    }

    /**
     * The method used to add not numeric char to string
     *
     * @param e used to enter char from keyboard
     */
    @FXML
    public void setNotNumericButtonText(ActionEvent e) {
        if (result.length() > 0 && isNumber(result.substring(result.length() - 1))) {
            result.append(" ")
                    .append(((Button) e.getSource()).getText())
                    .append(" ");
        } else if (result.length() > 0) {
            result.replace(result.length() - 2, result.length() - 1, ((Button) e.getSource()).getText());
        } else {
            result.append("0 ")
                    .append(((Button) e.getSource()).getText())
                    .append(" ");
        }
        calculateLabel.setText(result.toString());
    }

    /**
     * The method used to remove last index from string to calculate
     */
    @FXML
    public void removeLastIndex() {
        if (result.length() > 0) {
            if (isNumber(result.substring(result.length() - 1)) || result.substring(result.length() - 1).equals(".")) {
                result.deleteCharAt(result.length() - 1);
            } else {
                result.delete(result.length() - 3, result.length());
            }
            calculateLabel.setText(result.toString());
        }
    }

    /**
     * The method used to calculate the result of the mathematical operation
     */
    @FXML
    public void getResult() {
        String totalResult = calculateLabel.getText();
        Expression e = new Expression(totalResult);
        double total = e.calculate();
        calculateTextField.setText(String.valueOf(total));
        calculateLabel.setText("");
        result.delete(0, result.length());
    }

    /**
     * The method used to clear the text in the Label control.
     */
    @FXML
    public void clearAll() {
        if (result.length() > 0) {
            result.delete(0, result.length());
            calculateLabel.setText("");
            calculateTextField.setText("0");
        }
    }

    /**
     * Method check if the string is number
     *
     * @param input text to check
     * @return true if <code>input</code> is number
     */
    public boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
