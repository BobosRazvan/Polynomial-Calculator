package com.example.pt2024_30425_bobos_razvanandrei_assigment_1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.*;


public class HelloController {

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
                System.out.println(labelText);

                String polynomialInput1 = textFirstPolynomial.getText();
                String polynomialInput2 = textSecondPolynomial.getText();

                Polynomial polynomial1 = parsePolynomial(polynomialInput1);
                Polynomial polynomial2 = parsePolynomial(polynomialInput2);

                polynomial1.showPolynom();
                polynomial2.showPolynom();

                Operations operations=new Operations();

                switch(labelText){

                    case "Add" : Polynomial result1 = operations.add(polynomial1,polynomial2);
                                 result1.showPolynom();
                                 textResult.setText(result1.toString());
                                 break;

                    case "Subtract" : Polynomial result2 = operations.subtract(polynomial1,polynomial2);
                                        result2.showPolynom();
                                        textResult.setText(result2.toString());
                                        break;

                    case "Divide" : DivisionResult result3 = operations.divide(polynomial1,polynomial2);
                                    result3.getQuotient().showPolynom();
                                    result3.getQuotient().showPolynom();
                                    textResult.setText("Q: "+result3.getQuotient().toString()+"    R: "+result3.getRemainder().toString());
                                    break;

                    case "Multiplicate" : Polynomial result5 = operations.multiply(polynomial1,polynomial2);
                                    result5.showPolynom();
                                    textResult.setText(result5.toString());
                                    break;


                    case "Integrate" : Polynomial result6 = operations.integrate(polynomial1);
                                    result6.showPolynom();
                                    textResult.setText(result6.toString());
                                    break;

                    case "Derivate" : Polynomial result7 = operations.differentiate(polynomial1);
                                     result7.showPolynom();
                                     textResult.setText(result7.toString());
                                     break;

                    default: break;



                }
        }

    }

    private Polynomial parsePolynomial(String polynomialInput) {
        Polynomial polynomial = new Polynomial();
        if (polynomialInput != null && !polynomialInput.trim().isEmpty()) {
            // Split the polynomial input string based on both '+' and '-'
            String[] monomialStrings = polynomialInput.split("(?=\\+|\\-)");
            for (String monomialString : monomialStrings) {
                String[] parts = monomialString.trim().split("x\\^");
                if (parts.length == 1) {
                    double coefficient;
                    int exponent;
                    if (parts[0].isEmpty()) {
                        coefficient = 0.0;
                        exponent = 0;
                    } else if (parts[0].equals("x")) {
                        coefficient = 1.0;
                        exponent = 1;
                    } else if (parts[0].endsWith("x")) {
                        coefficient = Double.parseDouble(parts[0].substring(0, parts[0].length() - 1));
                        exponent = 1;
                    } else {
                        coefficient = Double.parseDouble(parts[0]);
                        exponent = 0;
                    }
                    polynomial.addMonomial(new Monomial(exponent, coefficient)); // Add the monomial to the polynomial
                } else if (parts.length == 2) {
                    double coefficient = parts[0].isEmpty() ? 1.0 : Double.parseDouble(parts[0]);
                    int exponent = Integer.parseInt(parts[1]);
                    polynomial.addMonomial(new Monomial(exponent, coefficient));
                } else {
                    showAlert(Alert.AlertType.ERROR, "Invalid Monomial Format", "Invalid monomial format: " + monomialString);
                    System.err.println("Invalid monomial format: " + monomialString);

                }
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Empty Input", "Input string is empty");
            System.err.println("Input string is empty");

        }

        return polynomial;
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
