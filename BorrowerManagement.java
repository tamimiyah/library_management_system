import java.util.ArrayList;
import java.util.Scanner;

public class BorrowersManagement {

    private static ArrayList<Borrowers> borrowers = new ArrayList<>();
    private static userPrompt userPrompt = new userPrompt();
    private static Scanner input = new Scanner(System.in);

    public static void manageBorrowers() {
        boolean continueManaging = true;

        while (continueManaging) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Borrowers Management");
            System.out.println("********************************************************");
            System.out.println("[1] Add Borrower");
            System.out.println("[2] Edit Borrower");
            System.out.println("[3] Delete Borrower");
            System.out.println("[4] View Borrowers");
            System.out.println("[5] Back to Main Menu");

            int choice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (choice) {
                case 1:
                    AddBorrower();
                    break;
                case 2:
                    EditBorrower();
                    break;
                case 3:
                    DeleteBorrower();
                    break;
                case 4:
                    ViewBorrowers();
                    break;
                case 5:
                    continueManaging = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    break;
            }
        }
    }

private static void AddBorrower() {
        loadBorrowersFromFile();

        boolean continueAdding = true;
        while (continueAdding) {

            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("           Borrowers' Information Management");
            System.out.println("********************************************************");

            Borrowers borrower = new Borrowers(0, "", "", "", "", "", "", "", "");

            while (true) {
                int borrowerId = userPrompt.promptForValidBorrowerId("Enter Borrower ID: ", borrowers);
                if (borrowerId > 0 && findBorrowersById(borrowerId) == null) {
                    borrower.setId(borrowerId);
                    break;
                } else {
                    System.out.println("Invalid ID!!! It must be a positive integer and unique.");
                }
            }

            String lastName = userPrompt.promptForValidString("Enter Last Name: ");
            borrower.setLastName(lastName);

            String firstName = userPrompt.promptForValidString("Enter First Name: ");
            borrower.setFirstName(firstName);

            String middleName = userPrompt.promptForValidMidName("Enter Middle Name (Press Space/Enter if None): ");
            borrower.setMiddleName(middleName);

            String gender = userPrompt.promptForValidGender("Enter Gender (Male/Female): ");
            borrower.setGender(gender);

            String birthday = userPrompt.promptForValidDate("Enter Birthday (YYYY-MM-DD): ");
            borrower.setBirthday(birthday);

            String contactNum = userPrompt.promptForValidContactNum("Enter Contact Number: ");
            borrower.setContactNumber(contactNum);

            String email = userPrompt.promptForValidEmail("Enter Email: ");
            borrower.setEmail(email);

            String address = userPrompt.promptForValidString("Enter Address: ");
            borrower.setAddress(address);

            borrowers.add(borrower);
            System.out.println("Borrower's Information Added Successfully!!!");

            saveBorrowersToFile();

            continueAdding = userPrompt.confirmContinue("Adding Borrower's Information");
        }

    }

private static void EditBorrower() {

        boolean continueEditing = true;

        loadBorrowersFromFile();
        int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID to edit: ");
        Borrowers borrower = findBorrowersById(borrowerId);

        if (borrower == null) {
            System.out.println("Borrower ID not found.");

            continueEditing = userPrompt.confirmContinue("Editing Borrower's Information");
            return;
        }

        while (continueEditing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Edit Borrower's Information");
            System.out.println("********************************************************");
            System.out.println("Choose What to Edit:");
            System.out.println("[1] Last Name");
            System.out.println("[2] First Name");
            System.out.println("[3] Middle Name");
            System.out.println("[4] Gender");
            System.out.println("[5] Birthday");
            System.out.println("[6] Contact Number");
            System.out.println("[7] Email");
            System.out.println("[8] Address");
            System.out.println("[9] Back to Previous Menu");

            int editChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (editChoice) {
                case 1:
                    String lastName = userPrompt.promptForValidString("Enter New Last Name: ");
                    borrower.setLastName(lastName);
                    break;
                case 2:
                    String firstName = userPrompt.promptForValidString("Enter New First Name: ");
                    borrower.setFirstName(firstName);
                    break;
                case 3:
                    String middleName = userPrompt
                            .promptForValidMidName("Enter New Middle Name (Press Space/Enter if None): ");
                    borrower.setMiddleName(middleName);
                    break;
                case 4:
                    String gender = userPrompt.promptForValidGender("Enter New Gender (Male/Female): ");
                    borrower.setGender(gender);
                    break;
                case 5:
                    String birthday = userPrompt.promptForValidDate("Enter New Birthday (YYYY-MM-DD): ");
                    borrower.setBirthday(birthday);
                    break;
                case 6:
                    String contactNum = userPrompt.promptForValidContactNum("Enter New Contact Number: ");
                    borrower.setContactNumber(contactNum);
                    break;
                case 7:
                    String email = userPrompt.promptForValidEmail("Enter New Email: ");
                    borrower.setEmail(email);
                    break;
                case 8:
                    String address = userPrompt.promptForValidString("Enter New Address: ");
                    borrower.setAddress(address);
                    break;
                case 9:
                    continueEditing = false;
                    break;
                default:
                    System.out.println("Invalid Choice!!! Please Try Again!!!");
            }
        }
        System.out.println("Borrower's Information Edited Successfully!!!");
        saveBorrowersToFile();

        continueEditing = userPrompt.confirmContinue("Editing Borrower's Information");
    }

private static void DeleteBorrower() {
        loadBorrowersFromFile();

        boolean continueDeleting = true;
        while (continueDeleting) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Delete Borrower's Information");
            System.out.println("********************************************************");

            int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID to delete: ");
            Borrowers borrower = findBorrowersById(borrowerId);

            if (borrower == null) {
                System.out.println("Borrower ID not found.");
                break;
            } else {
                borrowers.remove(borrower);
                System.out.println("Borrower's Information Deleted Successfully!!!");
                break;
            }
        }

        saveBorrowersToFile();
        continueDeleting = userPrompt.confirmContinue("Deleting Borrower's Information");
    }

private static void ViewBorrower() {
        loadBorrowersFromFile();

        boolean continueViewing = true;
        while (continueViewing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              View Borrower's Information");
            System.out.println("********************************************************");

            int borrowerId = userPrompt.getValidIntegerInput("ENTER BORROWER ID: ");
            Borrowers borrower = findBorrowersById(borrowerId);

            if (borrower == null) {
                System.out.println("Borrower ID not found.");
            } else {
                System.out.println("   BORROWER:\t" + borrower.getId());
                System.out.println("  LAST NAME:\t" + borrower.getLastName().toUpperCase());
                System.out.println(" FIRST NAME:\t" + borrower.getFirstName().toUpperCase());
                System.out.println("MIDDLE NAME:\t" + borrower.getMiddleName().toUpperCase());
                System.out.println("     GENDER:\t" + borrower.getGender().toUpperCase());
                System.out.println("   BIRTHDAY:\t" + borrower.getBirthday());
                System.out.println("  CONTACT #:\t" + borrower.getContactNumber());
                System.out.println("      EMAIL:\t" + borrower.getEmail());
                System.out.println("    ADDRESS:\t" + borrower.getAddress().toUpperCase());
                System.out.println(" VIOLATIONS:\t" + borrower.getViolationNum());
            }
            continueViewing = userPrompt.confirmContinue("Viewing Borrower's Information");
        }
    }

private static Borrowers findBorrowersById(int id) {
        for (Borrowers borrower : borrowers) {
            if (borrower.getId() == id) {
                return borrower;
            }
        }
        return null;
    }

private static void loadBorrowersFromFile() {
        FileReader fr = null;
        try {
            fr = new FileReader("borrowers.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Borrowers borrower = new Borrowers(0, "", "", "", "", "", "", "", "");
                borrower.setId(Integer.parseInt(data[0]));
                borrower.setLastName(data[1]);
                borrower.setFirstName(data[2]);
                borrower.setMiddleName(data[3]);
                borrower.setGender(data[4]);
                borrower.setBirthday(data[5]);
                borrower.setContactNumber(data[6]);
                borrower.setEmail(data[7]);
                borrower.setAddress(data[8]);
                borrowers.add(borrower);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading borrowers from file: " + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing FileReader: " + e.getMessage());
            }
        }
    }

private static void saveBorrowersToFile() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("borrowers.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Borrowers borrower : borrowers) {
                bw.write(borrower.getId() + "," + borrower.getLastName() + "," + borrower.getFirstName() + ","
                        + borrower.getMiddleName() + "," + borrower.getGender() + "," + borrower.getBirthday() + ","
                        + borrower.getContactNumber() + "," + borrower.getEmail() + "," + borrower.getAddress());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving borrowers to file: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing FileWriter: " + e.getMessage());
            }
        }
    }

