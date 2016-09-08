public class Fibonacci {
    private static final int MAX_TERM_VALUE = 10000;

    public static void main(String[] args) {
        System.out.println("This program lists the Fibonacci sequence.");

        int term1 = 0;
        int term2 = 1;

        while (term1 < MAX_TERM_VALUE) {
            System.out.println(term1);

            term2 = term1 + term2;
            term1 = term2 - term1;
        }
    }
}
