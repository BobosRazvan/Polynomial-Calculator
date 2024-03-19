package models;

import java.util.Map;

public class Operations {

    public static Polynomial add(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : polynomial1.monomials.entrySet()) {
            Monomial resultMonomial = entry.getValue();
            result.addMonomial(resultMonomial);
        }
        for (Map.Entry<Integer, Monomial> entry : polynomial2.monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial resultMonomial = entry.getValue();
            if (result.monomials.containsKey(degree)) {
                Monomial existingMonomial = result.monomials.get(degree);
                Monomial newMonomial = existingMonomial.add(resultMonomial);
                result.addMonomial(newMonomial);

            } else {
                result.addMonomial(resultMonomial);
            }
        }
        return result;
    }

    public static Polynomial subtract(Polynomial polynomial1,Polynomial polynomial2){
        Polynomial result= new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : polynomial1.monomials.entrySet()) {
            Monomial resultMonomial = entry.getValue();
            result.addMonomial(resultMonomial);
        }
        for (Map.Entry<Integer, Monomial> entry : polynomial2.monomials.entrySet()){
            Monomial monomial2= entry.getValue();
            int degree=entry.getKey();
            if(result.monomials.containsKey(degree)){
                Monomial newResult=result.monomials.get(degree).subtract(monomial2);
                result.addMonomial(newResult);
            }else{
                result.addMonomial(monomial2);
            }
        }
        return result;
    }


    public static Polynomial multiply(Polynomial polynomial1,Polynomial polynomial2){
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer,Monomial> entry1 : polynomial1.monomials.entrySet()){
            for(Map.Entry<Integer,Monomial> entry2 : polynomial2.monomials.entrySet()){
                int degree1=entry1.getKey();
                int degree2=entry2.getKey();
                Monomial monomial1=entry1.getValue();
                Monomial monomial2=entry2.getValue();
                Monomial resultMonomial=monomial1.multiply(monomial2);
                if(result.monomials.containsKey(resultMonomial.degree)){
                    Monomial newResult=result.monomials.get(resultMonomial.degree).add(resultMonomial);
                    result.addMonomial(newResult);
                }
                else result.addMonomial(resultMonomial);
            }
        }
        return result;
    }

    public static DivisionResult divide(Polynomial n, Polynomial d) {
        if (n.getLead().degree >= d.getLead().degree) {
            if (d.checkIfPolynomIsZero()) {
                throw new IllegalArgumentException("The divider is 0!.");
            } else {
                Polynomial q = new Polynomial();
                Polynomial rest = new Polynomial();
                Polynomial r = n;
                while (!r.checkIfPolynomIsZero() && r.getLead().degree >= d.getLead().degree) {
                    Polynomial t = new Polynomial();
                    Monomial tMonomial = r.getLead().divide(d.getLead());
                    t.addMonomial(tMonomial);
                    q = Operations.add(q, t);
                    Polynomial intermediary = Operations.multiply(t, d);
                    r = Operations.subtract(r, intermediary);
                }
                rest=r;
                return new DivisionResult(q, r);
            }
        } else throw new IllegalArgumentException("The first polynomial is smaller than the second.");
    }


    public static Polynomial differentiate(Polynomial polynomial1) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : polynomial1.monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial originalMonomial = entry.getValue();
            if(degree!=0) {
                Monomial resultMonomial = new Monomial(degree - 1, originalMonomial.coefficient); // Create a new Monomial object for the result
                resultMonomial.coefficient = originalMonomial.coefficient.doubleValue() * degree;
                result.addMonomial(resultMonomial);
            }

        }

        return result;
    }


    public static Polynomial integrate(Polynomial polynomial1) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : polynomial1.monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial originalMonomial = entry.getValue();
            Monomial resultMonomial = new Monomial(degree + 1,originalMonomial.coefficient); // Create a new Monomial object
            if (degree == -1) {
                throw new IllegalArgumentException("You cannot integrate a polynomial of form a/x.");
            } else {
                resultMonomial.coefficient = originalMonomial.coefficient.doubleValue() / (degree + 1); // Calculate the integrated coefficient
            }
            result.addMonomial(resultMonomial);
        }
        return result;
    }


}

