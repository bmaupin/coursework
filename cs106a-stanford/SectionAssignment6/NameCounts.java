import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NameCounts {
    private Map<String, Integer> nameCounts;
    private Scanner scanner;

    public static void main(String[] args) {
        NameCounts nameCountsApp = new NameCounts();
        nameCountsApp.run();
    }

    private void run() {
        getNamesFromUser();
        printNameCounts();
    }

    private void getNamesFromUser() {
        nameCounts = new HashMap<String, Integer>();
        scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter name: ");
            String nameFromUser = scanner.nextLine();
            if (nameFromUser.equals("")) {
                break;
            }

            int newNameCount = nameCounts.getOrDefault(nameFromUser, 0) + 1;
            nameCounts.put(nameFromUser, newNameCount);
        }
    }

    private void printNameCounts() {
        nameCounts.forEach((name, count) -> {
            System.out.printf("Entry [%s] has count %d\n", name, count);
        });
    }
}
