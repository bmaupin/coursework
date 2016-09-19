import java.util.Scanner;

public class AddCommas {
    private static final String NUMBER_GROUP_SEPARATOR = ",";
    private static final int NUMBER_GROUP_SIZE = 3;

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a number: ");
            String digits = scanner.nextLine();

            if (digits.length() == 0) {
                break;
            }

            System.out.println(addCommasToNumericString(digits));
        }
    }

    private static String addCommasToNumericString(String digits) {
        String formattedString = "";

        for (int i = 0; i < digits.length(); i++) {
            if (i > 0 && i % NUMBER_GROUP_SIZE == 0) {
                formattedString = NUMBER_GROUP_SEPARATOR + formattedString;
            }

            formattedString = digits.charAt(digits.length() - 1 - i) + formattedString;
        }

        return formattedString;
    }
}
