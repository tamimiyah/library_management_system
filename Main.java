import java.io.*;
import java.util.*;

public class Main {

    private static ArrayList<Borrowers> borrowers = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Material> library = new ArrayList<>();

    FileReader fr = null;
    FileWriter fw = null;

    public static void main(String[] args) {
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
                    ClearScreen();
                    BorrowerManagement();

                    break;

                case 2:
                    ClearScreen();
                    AssetManagement();
                    break;
                case 3:
                    ClearScreen();
                    Borrow();
                    break;
                case 4:
                    ClearScreen();
                    Return();
                    break;

                case 5:
                    ClearScreen();
                    System.out.println("Borrowers History");
                    // viewBorrowerHistory();
                    break;
                case 6:
                    ClearScreen();
                    System.out.println("Assets History");
                    // viewAssetHistory();
                    break;

                case 7:
                    ClearScreen();
                    continueRunning = false;
                    exitProgram();

                default:
                    System.out.println("Invalid Choice!!! Please Try Again!!!");
            }
        }

        userPrompt.closeScanner();
    }

  private static void exitProgram() {
        System.out.println("****************************************************************");
        System.out.println("  Thank You for Visiting CodeX Library! Come Again Next Time!!");
        System.out.println("****************************************************************");

        System.out.println("LIBRARIAN:\t Valera, Tamiyah Gale ");
        System.out.println("RECORD KEEPERS:\t Conde, Kiesha ");
        System.out.println("\t\t Masiglat, Mikaella Ann ");
        System.out.println("ASSISTANT:\t Vinoya, Davee Kendra");

        System.exit(0);
    }

  private static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
  
