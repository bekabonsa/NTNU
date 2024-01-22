import org.junit.Test;
import resources.CalculatorResource;

import static org.junit.Assert.assertEquals;

public class CalculatorResourceTest{

    @Test
    public void testCalculate(){
        CalculatorResource calculatorResource = new CalculatorResource();

        String expression = "100+300+1";
        assertEquals("401", calculatorResource.calculate(expression));

        expression = " 300 - 99 - 1 ";
        assertEquals("200", calculatorResource.calculate(expression));

        expression = "2*2*2";
        assertEquals("8", calculatorResource.calculate(expression));
    }

    @Test
    public void testSum(){
        CalculatorResource calculatorResource = new CalculatorResource();

        String expression = "100+300";
        assertEquals("400", String.valueOf(calculatorResource.sum(expression)));

        expression = "300+99";
        assertEquals("399", String.valueOf(calculatorResource.sum(expression)));

        expression = "10*20";
        assertEquals("200", String.valueOf(calculatorResource.multiplication(expression)));
    }

    @Test
    public void testSubtraction(){
        CalculatorResource calculatorResource = new CalculatorResource();

        String expression = "999-100";
        assertEquals("899", String.valueOf(calculatorResource.subtraction(expression)));

        expression = "20-2";
        assertEquals("18", String.valueOf(calculatorResource.subtraction(expression)));
    }

    @Test
    public void testMultiplication() {
        CalculatorResource calculatorResource = new CalculatorResource();

        String expression = "10*20";
        assertEquals("200", String.valueOf(calculatorResource.multiplication(expression)));

        expression = "2*2";
        assertEquals("4", String.valueOf(calculatorResource.multiplication(expression)));
    }

    @Test
    public void testDivision() {
        CalculatorResource calculatorResource = new CalculatorResource();

        String expression = "10/2";
        assertEquals("5", String.valueOf(calculatorResource.division(expression)));

        expression = "2/2";
        assertEquals("1", String.valueOf(calculatorResource.division(expression)));
    }
}
