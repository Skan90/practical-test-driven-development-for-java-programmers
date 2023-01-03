public class ValidateISBN {

    public static final int LONG_ISBN_LENGHT = 13;
    public static final int SHORT_ISBN_LENGHT = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {

        if (isbn.length() == LONG_ISBN_LENGHT) {
            return isThisAValidLongISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGHT) {
            return isThisAValidShortISBN(isbn);
        }
        throw new NumberFormatException("ISBN numbers must be at least 10 or 13 digits long");
    }


    private static boolean isThisAValidShortISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGHT; i++) {
            int number = 0;
            boolean characterIsNotANumber = !Character.isDigit(isbn.charAt(i));

            if (characterIsNotANumber) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN number must contain only numeric digits");
                }
            } else {

                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGHT - i);
            }
        }
        return (total % SHORT_ISBN_MULTIPLIER == 0);
    }

    private static boolean isThisAValidLongISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < LONG_ISBN_LENGHT; i++) {
            if (i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
        return (total % LONG_ISBN_MULTIPLIER == 0);
    }
}
