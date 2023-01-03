import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTest {
    @Test
    public void checkAValidTenDigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("1419756516");
        assertTrue(result);
        result = validator.checkISBN("3836581175");
        assertTrue(result);
    }

    @Test
    public void ChackAValid13DigitISBNNumber() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781529044195");
        assertTrue(result);
        result = validator.checkISBN("9780552137034");
        assertTrue(result);
    }
    @Test
    public void TenDigitsISBNNumbersEndingInAnXAreValid(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("852861705X");
        assertTrue(result);
    }

    @Test
    public void checkAnInValid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("1419756517");
        assertFalse(result);
    }
    @Test
    public void checkAnInValid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781529044194");
        assertFalse(result);
    }
    @Test
    public void nineDigitsISBNAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,
                () -> validator.checkISBN("123456789"),
                "ISBN numbers must be at least 10 or 13 digits long");
    }

    @Test
    public void nonNumericISBNsAreNotAllowed() {

        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,
                () -> validator.checkISBN("helloworld"),
                "ISBN number must contain only numeric digits");
    }
}
