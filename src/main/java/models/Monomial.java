package models;

public class Monomial {

    int degree;
    Number coefficient;

    public Monomial(int degree,Number coefficient) {
        this.degree = degree;
        this.coefficient=coefficient;

    }

    public Number getCoefficient(){
        return coefficient;
    }

    public Monomial add(Monomial monomial2) {
        if (this.degree == monomial2.degree) {
            double sum = this.coefficient.doubleValue() + monomial2.coefficient.doubleValue();
            return new Monomial(this.degree, sum);
        } else {
            throw new IllegalArgumentException("Degrees of monomials must be the same for addition.");
        }
    }




    public Monomial subtract(Monomial monomial2) {
        if (this.degree == monomial2.degree) {
            double subtraction = this.coefficient.doubleValue() - monomial2.coefficient.doubleValue();
            return new Monomial(this.degree, subtraction);
        } else {
            throw new IllegalArgumentException("Degrees of monomials must be the same for subtraction.");
        }
    }


    public Monomial multiply(Monomial monomial2){


        int resultDegree=this.degree+ monomial2.degree;

        double resultCoefficient=this.coefficient.doubleValue()*monomial2.coefficient.doubleValue();

        return new Monomial(resultDegree,resultCoefficient);

    }

    public Monomial divide(Monomial monomial2){
        int resultDegree=this.degree- monomial2.degree;
        double resultCoefficient=this.coefficient.doubleValue()/ monomial2.coefficient.doubleValue();

        return new Monomial(resultDegree,resultCoefficient);
    }


}
