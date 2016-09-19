public class DeleteChars {
    public static void main(String[] args) {
        System.out.println(removeAllOccurrences("This is a test", 't'));
        System.out.println(removeAllOccurrences("Summer is here!", 'e'));
        System.out.println(removeAllOccurrences("---0---", '-'));
    }

    public static String removeAllOccurrences(String str, char ch) {
        return str.replaceAll(Character.toString(ch), "");
    }
}
