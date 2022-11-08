import com.example.junitrestapiapplication.Calculator;
import com.jayway.jsonpath.spi.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    public void setUp(){
        calculator  = new Calculator();
    }

    @Test
    public void testMultiply(){
        assertEquals(20,calculator.multiply(4,5));
        assertEquals(25,calculator.multiply(5,5));
    }

    @Test
    public void testDevide(){
        assertEquals(2,calculator.devide(4,2));
    }

    @Test
    public void testAdd(){
        assertEquals(4,calculator.add(3,1));
    }
}
