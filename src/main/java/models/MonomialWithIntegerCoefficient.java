package models;

public class MonomialWithIntegerCoefficient extends Monomial {

    public MonomialWithIntegerCoefficient(int degree,Integer coefficient) {
        super(degree,coefficient);

    }

    @Override
    public Integer getCoefficient() {
        return (Integer) coefficient;
    }
}
