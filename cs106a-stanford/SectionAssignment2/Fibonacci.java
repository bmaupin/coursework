public class Fibonacci {
    private static final int MAX_TERM_VALUE = 10000;

    public static void main(String[] args) {
        System.out.println("This program lists the Fibonacci sequence.");

        int prevTerm = 0;
        int term = 0;

        while (term < MAX_TERM_VALUE) {
            System.out.println(term);

            if (term == 0) {
                term++;

            } else {
                term = prevTerm + term;
                prevTerm = term - prevTerm;
            }
        }
    }
}
