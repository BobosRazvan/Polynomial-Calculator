package models;

import java.util.*;

public class Polynomial {

    Map<Integer,Monomial> monomials;

    public Polynomial() {
        this.monomials = new HashMap<>();
    }

    public Map<Integer, Monomial> getMonomials() {
        return monomials;
    }

    public void addMonomial(Monomial monomial){
        monomials.put(monomial.degree,new Monomial(monomial.degree,monomial.coefficient));
    }

    public Boolean checkIfPolynomIsZero(){
        Monomial degreeZeroMonomial = this.monomials.get(0);
        if (degreeZeroMonomial != null && degreeZeroMonomial.coefficient.doubleValue() == 0.0) {
            for(Map.Entry<Integer,Monomial> entry : this.monomials.entrySet()){
                if(entry.getValue()!=null && entry.getKey()!=0){
                    return false;
                }
            }
            return true;
        }
        else return false;
    }

    public Monomial getLead(){

        int maxDegree=0;

        for(Map.Entry<Integer,Monomial> entry: monomials.entrySet()){
            if(entry.getKey()>maxDegree && entry.getValue().coefficient.doubleValue()!=0.0){
                maxDegree= entry.getKey();
            }
        }
        return this.monomials.get(maxDegree);
    }



    public void showPolynom(){
        for(Map.Entry<Integer, Monomial> entry : monomials.entrySet()){
            int degree=entry.getKey();
            Monomial monomial =entry.getValue();
            System.out.println("Degree "+degree+" coeffiencient "+monomial.getCoefficient());

        }
        System.out.println();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean firstTerm = true; // to handle leading '+' sign
        List<Integer> degrees = new ArrayList<>(monomials.keySet());
        Collections.sort(degrees, Collections.reverseOrder()); // Sort degrees in descending order
        for (int degree : degrees) {
            Monomial monomial = monomials.get(degree);
            if (monomial != null) {
                double coefficient = monomial.getCoefficient().doubleValue();
                if (coefficient != 0.0) {
                    String coefficientString;
                    if (coefficient == (int) coefficient) {
                        coefficientString = Integer.toString((int) coefficient);
                    } else {
                        // Format coefficient to two decimal places
                        coefficientString = String.format("%.2f", coefficient);
                    }
                    if (!firstTerm) {
                        if (coefficient > 0) {
                            result.append(" + ");
                        } else {
                            result.append(" - ");
                            coefficientString = coefficientString.substring(1); // Remove the negative sign from coefficientString
                        }
                    }
                    if (Math.abs(coefficient) != 1 || degree == 0) { // Skip coefficient if it's 1 (except for constant term)
                        result.append(coefficientString);
                    }
                    if (degree > 0) {
                        result.append("x");
                        if (degree > 1) {
                            result.append("^").append(degree);
                        }
                    }
                    firstTerm = false;
                }
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Polynomial other = (Polynomial) obj;
        // Compare the content of the polynomials
        return this.toString().equals(other.toString());
    }






}
