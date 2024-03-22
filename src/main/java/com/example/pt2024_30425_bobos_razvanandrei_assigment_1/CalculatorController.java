package com.example.pt2024_30425_bobos_razvanandrei_assigment_1;
import models.DivisionResult;
import models.Monomial;
import models.Operations;
import models.Polynomial;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;



public class CalculatorController {

    @FXML
    private TextField textFirstPolynomial;

    @FXML
    private Pane btn0;

    @FXML
    private Pane btn1;

    @FXML
    private Pane btn2;

    @FXML
    private Pane btn3;

    @FXML
    private Pane btn4;

    @FXML
    private Pane btn5;

    @FXML
    private Pane btn6;

    @FXML
    private Pane btn7;

    @FXML
    private Pane btn8;

    @FXML
    private Pane btn9;

    @FXML
    private Pane btnAdd;


    @FXML
    private Pane btnDel;

    @FXML
    private Pane btnDerivate;

    @FXML
    private Pane btnDivide;

    @FXML
    private Pane btnIntegrate;

    @FXML
    private Pane btnMinus;

    @FXML
    private Pane btnMultiplicate;

    @FXML
    private Pane btnMultiply;

    @FXML
    private Pane btnPlus;

    @FXML
    private Pane btnPoint;

    @FXML
    private Pane btnSubtract;

    @FXML
    private Pane btnToPower;

    @FXML
    private Pane btnX;

    @FXML
    private TextField textResult;

    @FXML
    private TextField textSecondPolynomial;

    @FXML
    private Label welcomeText;


    @FXML
    void onOperationClicked(MouseEvent event) {
        if (event.getSource() instanceof Pane) {
            Pane clickedPane = (Pane) event.getSource();
            Label clickedLabel = (Label) clickedPane.getChildren().get(0);
            String labelText = clickedLabel.getText();
            String polynomialInput1 = textFirstPolynomial.getText();
            String polynomialInput2 = textSecondPolynomial.getText();
            Polynomial polynomial1 = parsePolynomial(polynomialInput1);
            Polynomial polynomial2 = parsePolynomial(polynomialInput2);
            handleOperation(labelText, polynomial1, polynomial2);
        }
    }

    private void handleOperation(String operation, Polynomial polynomial1, Polynomial polynomial2) {
        switch (operation) {
            case "Add":
                handleAddition(polynomial1, polynomial2);
                break;
            case "Subtract":
                handleSubtraction(polynomial1, polynomial2);
                break;
            case "Divide":
                handleDivision(polynomial1, polynomial2);
                break;
            case "Multiplicate":
                handleMultiplication(polynomial1, polynomial2);
                break;
            case "Integrate":
                handleIntegration(polynomial1);
                break;
            case "Derivate":
                handleDifferentiation(polynomial1);
                break;
            default:
                break;
        }
    }

    private void handleAddition(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = Operations.add(polynomial1, polynomial2);
        result.showPolynom();
        textResult.setText(result.toString());
    }

    private void handleSubtraction(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = Operations.subtract(polynomial1, polynomial2);
        result.showPolynom();
        textResult.setText(result.toString());
    }

    private void handleDivision(Polynomial polynomial1, Polynomial polynomial2) {
        DivisionResult result = Operations.divide(polynomial1, polynomial2);
        result.getQuotient().showPolynom();
        result.getQuotient().showPolynom();
        textResult.setText("Q: " + result.getQuotient().toString() + "    R: " + result.getRemainder().toString());
    }

    private void handleMultiplication(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = Operations.multiply(polynomial1, polynomial2);
        result.showPolynom();
        textResult.setText(result.toString());
    }

    private void handleIntegration(Polynomial polynomial1) {
        Polynomial result = Operations.integrate(polynomial1);
        result.showPolynom();
        textResult.setText(result.toString());
    }

    private void handleDifferentiation(Polynomial polynomial1) {
        Polynomial result = Operations.differentiate(polynomial1);
        result.showPolynom();
        textResult.setText(result.toString());
    }

    private Polynomial parsePolynomial(String polynomialInput) {
        Polynomial polynomial = new Polynomial();
        if (polynomialInput != null && !polynomialInput.trim().isEmpty()) {
            String[] monomialStrings = splitPolynomialInput(polynomialInput);
            for (String monomialString : monomialStrings) {
                createMonomial(polynomial, monomialString);
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Empty Input", "Input string is empty");
            System.err.println("Input string is empty");
        }
        return polynomial;
    }

    private String[] splitPolynomialInput(String polynomialInput) {
        return polynomialInput.split("(?=\\+|\\-)");
    }

    private void createMonomial(Polynomial polynomial, String monomialString) {
        String[] parts = monomialString.trim().split("x\\^");
        if (parts.length == 1) { //no exponent
            double coefficient = parseCoefficient(parts[0]);
            int exponent = parseExponent(parts[0]);
            polynomial.addMonomial(new Monomial(exponent, coefficient));
        } else if (parts.length == 2) {
            double coefficient = parts[0].isEmpty() ? 1.0 : Double.parseDouble(parts[0]);//if it is empty it is 1 else parse double
            int exponent = Integer.parseInt(parts[1]);
            polynomial.addMonomial(new Monomial(exponent, coefficient));
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid Monomial Format", "Invalid monomial format: " + monomialString);
            System.err.println("Invalid monomial format: " + monomialString);
        }
    }

    private double parseCoefficient(String coefficientString) {
        if (coefficientString.isEmpty()) {
            return 0.0;
        } else if (coefficientString.equals("x")) {
            return 1.0;
        } else if (coefficientString.endsWith("x")) {
            return Double.parseDouble(coefficientString.substring(0, coefficientString.length() - 1));
        } else {
            return Double.parseDouble(coefficientString);
        }
    }

    private int parseExponent(String monomialString) {
        //System.out.println(monomialString);
        if (monomialString.isEmpty()) {
            return 0;
        } else if (monomialString.contains("x") && !monomialString.contains("^")) {
            return 1;
        } else if (monomialString.contains("^")) {
            String[] parts = monomialString.split("\\^");
            return Integer.parseInt(parts[1]);
        } else {
            return 0; // Exponent is 0 if no exponent is specified
        }
    }

    @FXML
    void onNumberClicked(MouseEvent event) {

        if (event.getSource() instanceof Pane) {
            Pane clickedPane = (Pane) event.getSource();
            Label clickedLabel = (Label) clickedPane.getChildren().get(0);
            String labelText = clickedLabel.getText();
            //System.out.println(labelText);
            TextField focusedTextField = getFocusedTextField();

            if (focusedTextField != null) {
                focusedTextField.appendText(labelText);
            }


        }

    }

    @FXML
    void onDeleteClicked(MouseEvent event){
        if (event.getSource() instanceof Pane) {
            Pane clickedPane = (Pane) event.getSource();
            Label clickedLabel = (Label) clickedPane.getChildren().get(0);
            String labelText = clickedLabel.getText();
            //System.out.println(labelText);
            TextField focusedTextField = getFocusedTextField();

            if (focusedTextField != null) {
                focusedTextField.deleteText(focusedTextField.getLength()-1,focusedTextField.getLength());
            }

        }
    }

    // Helper method to get the currently focused text field
    private TextField getFocusedTextField() {
        if (textFirstPolynomial.isFocused()) {
            return textFirstPolynomial;
        } else if (textSecondPolynomial.isFocused()) {
            return textSecondPolynomial;
        } else {
            return null; // No text field is focused
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
