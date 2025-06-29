import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class CalculatorTest {
    
    private Calculator calculator;
    
    // Setup method - runs before each test
    @Before
    public void setUp() {
        System.out.println("Setting up test...");
        calculator = new Calculator();
    }
    
    // Teardown method - runs after each test
    @After
    public void tearDown() {
        System.out.println("Cleaning up after test...");
        calculator = null;
    }
    
    @Test
    public void testAdd() {
        // Arrange
        int a = 5;
        int b = 3;
        int expected = 8;
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        assertEquals("Addition should work correctly", expected, result);
    }

    @Test
    public void testAddNegativeNumbers() {
        int a = -2;
        int b = -3;
        int expected = -5;
        int result = calculator.add(a, b);
        assertEquals("Addition with negatives should work", expected, result);
    }

    @Test
    public void testAddWithZero() {
        int a = 0;
        int b = 7;
        int expected = 7;
        int result = calculator.add(a, b);
        assertEquals("Addition with zero should work", expected, result);
    }
    
    @Test
    public void testSubtract() {
        // Arrange
        int a = 10;
        int b = 4;
        int expected = 6;
        
        // Act
        int result = calculator.subtract(a, b);
        
        // Assert
        assertEquals("Subtraction should work correctly", expected, result);
    }

    @Test
    public void testSubtractNegativeResult() {
        int a = 3;
        int b = 7;
        int expected = -4;
        int result = calculator.subtract(a, b);
        assertEquals("Subtraction resulting in negative should work", expected, result);
    }

    @Test
    public void testSubtractWithZero() {
        int a = 5;
        int b = 0;
        int expected = 5;
        int result = calculator.subtract(a, b);
        assertEquals("Subtraction with zero should work", expected, result);
    }
    
    @Test
    public void testMultiply() {
        // Arrange
        int a = 4;
        int b = 5;
        int expected = 20;
        
        // Act
        int result = calculator.multiply(a, b);
        
        // Assert
        assertEquals("Multiplication should work correctly", expected, result);
    }

    @Test
    public void testMultiplyByZero() {
        int a = 0;
        int b = 10;
        int expected = 0;
        int result = calculator.multiply(a, b);
        assertEquals("Multiplication by zero should be zero", expected, result);
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        int a = -3;
        int b = 6;
        int expected = -18;
        int result = calculator.multiply(a, b);
        assertEquals("Multiplication with negative should work", expected, result);
    }
    
    @Test
    public void testDivide() {
        // Arrange
        int a = 15;
        int b = 3;
        double expected = 5.0;
        
        // Act
        double result = calculator.divide(a, b);
        
        // Assert
        assertEquals("Division should work correctly", expected, result, 0.001);
    }

    @Test
    public void testDivideNegativeNumbers() {
        int a = -12;
        int b = 4;
        double expected = -3.0;
        double result = calculator.divide(a, b);
        assertEquals("Division with negative should work", expected, result, 0.001);
    }

    @Test
    public void testDivideResultFraction() {
        int a = 7;
        int b = 2;
        double expected = 3.5;
        double result = calculator.divide(a, b);
        assertEquals("Division resulting in fraction should work", expected, result, 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        // Arrange
        int a = 10;
        int b = 0;
        
        // Act & Assert
        calculator.divide(a, b); // Should throw exception
    }
}
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        assertEquals("Addition should work correctly", expected, result);
    }
    
    @Test
    public void testSubtract() {
        // Arrange
        int a = 10;
        int b = 4;
        int expected = 6;
        
        // Act
        int result = calculator.subtract(a, b);
        
        // Assert
        assertEquals("Subtraction should work correctly", expected, result);
    }
    
    @Test
    public void testMultiply() {
        // Arrange
        int a = 4;
        int b = 5;
        int expected = 20;
        
        // Act
        int result = calculator.multiply(a, b);
        
        // Assert
        assertEquals("Multiplication should work correctly", expected, result);
    }
    
    @Test
    public void testDivide() {
        // Arrange
        int a = 15;
        int b = 3;
        double expected = 5.0;
        
        // Act
        double result = calculator.divide(a, b);
        
        // Assert
        assertEquals("Division should work correctly", expected, result, 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        // Arrange
        int a = 10;
        int b = 0;
        
        // Act & Assert
        calculator.divide(a, b); // Should throw exception
    }
}
