package com.example.pt2024_30425_bobos_razvanandrei_assigment_1;

import javafx.fxml.FXML;
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
    private Pane btnCalculate;

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

                    case "Add" : Polynomial result = operations.add(polynomial1,polynomial2);
                                 result.showPolynom();
                                 textResult.setText(result.toString());
                                 break;

                    default:
                        System.out.println("Select an operation.");


                }
        }

    }

    private Polynomial parsePolynomial(String polynomialInput) {
        Polynomial polynomial = new Polynomial();
        if (polynomialInput != null && !polynomialInput.trim().isEmpty()) {
            String[] monomialStrings = polynomialInput.split("\\+");
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
                    System.err.println("Invalid monomial format: " + monomialString);
                }
            }
        } else {
            System.err.println("Input string is empty");
        }

        return polynomial;
    }

}
