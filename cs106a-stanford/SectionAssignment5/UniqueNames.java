import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniqueNames {
    private Scanner scanner;
    private List<String> uniqueNames;

    public static void main(String[] args) {
        UniqueNames uniqueNames = new UniqueNames();
        uniqueNames.run();
    }

    private void run() {
        getNamesFromUser();
        printUniqueNames();
    }

    private void getNamesFromUser() {
        uniqueNames = new ArrayList<String>();
        scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter name: ");
            String nameFromUser = scanner.nextLine();
            if (nameFromUser.equals("")) {
                break;
            }

            if (!uniqueNames.contains(nameFromUser)) {
                uniqueNames.add(nameFromUser);
            }
        }
    }

    private void printUniqueNames() {
        System.out.println("Unique name list contains:");
        for (String uniqueName : uniqueNames) {
            System.out.println(uniqueName);
        }
    }
}
