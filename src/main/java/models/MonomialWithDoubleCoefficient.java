package models;

public class MonomialWithDoubleCoefficient extends Monomial {

    public MonomialWithDoubleCoefficient(int degree,Double coefficient) {
        super(degree,coefficient);
        this.coefficient=coefficient;
    }

    @Override
    public Double getCoefficient() {
        return (Double) coefficient;
    }
}
