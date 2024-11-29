import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagement {

    private static ArrayList<Borrowers> borrowers = new ArrayList<>();
    private static userPrompt userPrompt = new userPrompt();
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Material> library = new ArrayList<>();

    public static void mainMenu() {
        boolean continueRunning = true;

        while (continueRunning) {
            ClearScreen();
            System.out.println("\n********************************************************");
            System.out.println("\t\tCodeX Library Management Database");
            System.out.println("********************************************************");
            System.out.println("[1] Borrowers Management");
            System.out.println("[2] Assets Management");
            System.out.println("[3] Borrow");
            System.out.println("[4] Return");
            System.out.println("[5] Borrowers History");
            System.out.println("[6] Assets History");
            System.out.println("[7] Exit");

            int mainChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (mainChoice) {
                case 1:
                    BorrowersManagement.manageBorrowers();
                    break;
                case 2:
                    AssetsManagement.manageAssets();
                    break;
                case 3:
                    BorrowMaterial();
                    break;
                case 4:
                    // Call Return
                    break;
                case 5:
                    // Call Borrowers History
                    break;
                case 6:
                    // Call Assets History
                    break;
                case 7:
                    continueRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    break;
            }
        }
    }

    private static void ClearScreen() {
        // Implementation of ClearScreen
    }

    private static void BorrowMaterial() {
        // Implementation of BorrowMaterial
    }

    // Other methods like loadBorrowersFromFile, loadAssets, etc.
}
