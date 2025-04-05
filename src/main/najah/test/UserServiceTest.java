package main.najah.test;

import org.junit.jupiter.api.*;
import main.najah.code.UserService;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

@DisplayName("UserService Test Class")
class UserServiceTest {

    private UserService userService;

    @BeforeAll
    static void setupAll() {
        System.out.println("Before all tests...");
    }
    
    @BeforeEach
    void setup() {
        System.out.println("Before each test...");
        userService = new UserService();
    }

    @Test
    @DisplayName("Test valid email")
    void testValidEmail() {
        assertTrue(userService.isValidEmail("user@example.com"), "Email should be valid");
    }

    @Test
    @DisplayName("Test invalid email")
    void testInvalidEmail() {
        assertFalse(userService.isValidEmail("user@com"), "Email should be invalid");
    }

    @Test
    @DisplayName("Test valid authentication")
    void testValidAuthentication() {
        assertTrue(userService.authenticate("admin", "1234"), "Authentication should succeed");
    }

    @Test
    @DisplayName("Test invalid authentication")
    void testInvalidAuthentication() {
        assertFalse(userService.authenticate("admin", "wrongpassword"), "Authentication should fail");
    }

    @Test
    @DisplayName("Test timeout for email validation")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(500), () -> userService.isValidEmail("user@example.com"));
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After all tests...");
    }
}
