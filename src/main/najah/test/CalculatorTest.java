package main.najah.test;

import main.najah.code.Calculator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)  // Enable parallel execution for this test class
@DisplayName("Calculator Test Class")
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
        System.out.println("Before each test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test...");
    }

    @BeforeAll
    static void setupAll() {
        System.out.println("Before all tests...");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After all tests...");
    }

    @Test
    @Order(1)
    @DisplayName("Addition Test")
    void testAdd() {
        assertEquals(9, calculator.add(4, 5), "4 + 5 = 9");
    }

    @Test
    @Order(2)
    @DisplayName("Division Test")
    void testDivide() {
        assertEquals(4, calculator.divide(8, 2), "8 / 2 = 4");
    }

    @Test
    @Order(3)
    @DisplayName("Factorial Test")
    void testFactorial() {
        assertEquals(24, calculator.factorial(4), "4! = 24");
    }


}
