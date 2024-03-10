package models;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {

    Map<Integer,Monomial> monomials;

    public Polynomial() {
        this.monomials = new HashMap<>();
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




}
