package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)  // Enable parallel execution for this test class
@DisplayName("Product Test Class")
public class ProductTest {

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product("Test Product", 100);
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
    @DisplayName("Test valid price")
    void testValidPrice() {
        assertEquals(100, product.getPrice(), "Price should be 100");
    }

    @Test
    @Order(2)
    @DisplayName("Test valid discount application")
    void testApplyDiscount() {
        product.applyDiscount(10);
        assertEquals(90, product.getFinalPrice(), "Final price after 10% discount should be 90");
    }

    @Test
    @Order(3)
    @DisplayName("Test invalid discount (greater than 50%)")
    void testInvalidDiscountGreaterThan50() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60));
        assertEquals("Invalid discount", exception.getMessage(), "Discount greater than 50% should throw IllegalArgumentException");
    }

    @Test
    @Order(4)
    @DisplayName("Test invalid discount (negative value)")
    void testInvalidDiscountNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-10));
        assertEquals("Invalid discount", exception.getMessage(), "Negative discount should throw IllegalArgumentException");
    }

    @Test
    @Order(5)
    @DisplayName("Test final price without discount")
    void testFinalPriceWithoutDiscount() {
        assertEquals(100, product.getFinalPrice(), "Final price should be 100 when no discount is applied");
    }

    @Test
    @Order(6)
    @DisplayName("Test price must be non-negative")
    void testPriceCannotBeNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Product("Invalid Product", -1));
        assertEquals("Price must be non-negative", exception.getMessage(), "Price less than zero should throw IllegalArgumentException");
    }
}
