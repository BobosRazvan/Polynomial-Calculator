package models;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class OperationsTest {


    @Test
    public void addTest() {
        Operations operations = new Operations();

        Monomial monomial1 = new MonomialWithIntegerCoefficient(2, 3);
        Monomial monomial2 = new MonomialWithIntegerCoefficient(1, 2);
        Monomial monomial3 = new MonomialWithIntegerCoefficient(2, 4);
        Monomial monomial4 = new MonomialWithIntegerCoefficient(0, 2);

        Polynomial polynomial1 = new Polynomial();
        polynomial1.addMonomial(monomial1);
        polynomial1.addMonomial(monomial2);

        Polynomial polynomial2 = new Polynomial();
        polynomial2.addMonomial(monomial3);
        polynomial2.addMonomial(monomial4);

        polynomial1.showPolynom();


        polynomial2.showPolynom();

        // Call the non-static method on the instance of Operations
        Polynomial result = operations.add(polynomial1, polynomial2);


        Polynomial expected = new Polynomial();
        expected.addMonomial(new MonomialWithIntegerCoefficient(2, 7));
        expected.addMonomial(new MonomialWithIntegerCoefficient(1, 2));
        expected.addMonomial(new MonomialWithIntegerCoefficient(0, 2));


        assertEquals(result,expected);
    }

    @Test
    public void subtractTest(){
        Operations operations = new Operations();

        Monomial monomial1 = new MonomialWithIntegerCoefficient(2, 5);
        Monomial monomial2 = new MonomialWithIntegerCoefficient(1, 2);
        Monomial monomial3 = new MonomialWithIntegerCoefficient(2, 4);
        Monomial monomial4 = new MonomialWithIntegerCoefficient(0, 2);

        Polynomial polynomial1 = new Polynomial();
        polynomial1.addMonomial(monomial1);
        polynomial1.addMonomial(monomial2);

        Polynomial polynomial2 = new Polynomial();
        polynomial2.addMonomial(monomial3);
        polynomial2.addMonomial(monomial4);

        polynomial1.showPolynom();


        polynomial2.showPolynom();

        // Call the non-static method on the instance of Operations
        Polynomial result = operations.subtract(polynomial1, polynomial2);



        Polynomial expected = new Polynomial();
        expected.addMonomial(new MonomialWithIntegerCoefficient(2, 1));
        expected.addMonomial(new MonomialWithIntegerCoefficient(1, 2));
        expected.addMonomial(new MonomialWithIntegerCoefficient(0, 2));

        assertEquals(result,expected);

    }




}
