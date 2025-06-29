import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {
    
    @Test
    public void testDifferentAssertions() {
        Calculator calculator = new Calculator();
        
        // Assert equals
        assertEquals("5 + 3 should equal 8", 8, calculator.add(5, 3));
        
        // Assert true
        assertTrue("6 should be even", calculator.isEven(6));
        
        // Assert false
        assertFalse("5 should not be even", calculator.isEven(5));
        
        // Assert null
        String nullString = null;
        assertNull("String should be null", nullString);
        
        // Assert not null
        Calculator notNullCalculator = new Calculator();
        assertNotNull("Calculator should not be null", notNullCalculator);
        
        // Assert same (same object reference)
        Calculator calc1 = calculator;
        assertSame("Should be same object", calculator, calc1);
        
        // Assert not same (different object references)
        Calculator calc2 = new Calculator();
        assertNotSame("Should be different objects", calculator, calc2);
        
        // Assert array equals
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals("Arrays should be equal", expected, actual);
    }
}