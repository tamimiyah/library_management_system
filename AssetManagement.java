import java.util.ArrayList;
import java.util.Scanner;

public class AssetsManagement {

    private static ArrayList<Material> library = new ArrayList<>();
    private static userPrompt userPrompt = new userPrompt();
    private static Scanner input = new Scanner(System.in);

    public static void manageAssets() {
        boolean continueManaging = true;

        while (continueManaging) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Assets Management");
            System.out.println("********************************************************");
            System.out.println("[1] Add Material");
            System.out.println("[2] Edit Material");
            System.out.println("[3] Delete Material");
            System.out.println("[4] View Materials");
            System.out.println("[5] Back to Main Menu");

            int choice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (choice) {
                case 1:
                    AddMaterial();
                    break;
                case 2:
                    EditMaterial();
                    break;
                case 3:
                    DeleteMaterial();
                    break;
                case 4:
                    ViewMaterials();
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
private static void AddMaterial() {
        boolean continueAdding = true;
        while (continueAdding) {

            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("           Material Information Management");
            System.out.println("********************************************************");
            System.out.println("Choose Material Type:");
            System.out.println("[1] Book ");
            System.out.println("[2] Journal ");
            System.out.println("[3] Magazine ");
            System.out.println("[4] Thesis Book ");
            System.out.println("[5] Exit ");

            int choice = userPrompt.getValidIntegerInput("Enter Choice: ");

            if (choice == 5) {
                continueAdding = false;
                continue;
            }

            String materialID = getUniqueMaterialID();

            int copies = userPrompt.promptForValidInteger("Enter # of Copies:");
            String yearPublished = userPrompt.promptForValidYear("Enter Year (YYYY):");
            String publisher = userPrompt.promptForValidString("Enter Publisher:");

            Material material = null;

            switch (choice) {
                case 1: // Book
                    String bookTitle = userPrompt.promptForValidString("Enter Title: ");
                    String author = userPrompt.promptForValidString("Enter Author: ");
                    material = new Book(materialID, bookTitle, author, yearPublished, publisher, copies);
                    break;

                case 2: // Journal
                    String journalName = userPrompt.promptForValidString("Enter Journal Name: ");
                    material = new Journal(materialID, journalName, yearPublished, publisher, copies);
                    break;

                case 3: // Magazine
                    String magazineName = userPrompt.promptForValidString("Enter Magazine Name: ");
                    material = new Magazine(materialID, magazineName, yearPublished, publisher, copies);
                    break;

                case 4: // Thesis Book
                    String title = userPrompt.promptForValidString("Enter Title: ");
                    String authorThesis = userPrompt.promptForValidString("Enter Author: ");
                    material = new ThesisBook(materialID, title, authorThesis, yearPublished, publisher, copies);
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid material type.");
                    continue;
            }

            if (material != null) {
                library.add(material);
                System.out.println(material.getClass().getSimpleName() + " Successfully Added!");
                saveAssets();

                continueAdding = userPrompt.confirmContinue("Adding Another Material");
            }
        }
    }

private static void EditMaterial() {
        loadAssets();
        boolean continueEditing = true;

        while (continueEditing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Edit Material Information");
            System.out.println("********************************************************");
            String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);

            // Find the material by ID
            Material material = findMaterialByID(materialID);
            while (material == null) {
                System.out.println("Material ID not found. Please enter a valid Material ID.");
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                material = findMaterialByID(materialID); // Check again with the new input
            }

            // Show the material type and provide options for editing based on the type
            System.out.println("Material Type: " + material.getClass().getSimpleName());
            System.out.println("\nChoose What to Edit:");

            // Show editing options based on the type of the material
            if (material instanceof Book) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Title");
                System.out.println("[2] Author");
                System.out.println("[3] Year Published");
                System.out.println("[4] Publisher");
                System.out.println("[5] Copies");
            } else if (material instanceof Journal) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Journal Name");
                System.out.println("[2] Year Published");
                System.out.println("[3] Publisher");
                System.out.println("[4] Copies");
            } else if (material instanceof Magazine) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Magazine Name");
                System.out.println("[2] Year Published");
                System.out.println("[3] Publisher");
                System.out.println("[4] Copies");
            } else if (material instanceof ThesisBook) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Title");
                System.out.println("[2] Author");
                System.out.println("[3] Year Published");
                System.out.println("[4] Publisher");
                System.out.println("[5] Copies");
            } else {
                System.out.println("[0] Enter Another Material ID to Edit");
                continue;  // In case of unsupported material types
            }

            int editChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (editChoice) {
                case 0:
                    continue;

                case 1: // Title/Name Editing
                    if (material instanceof Book || material instanceof ThesisBook) {
                        String title = userPrompt.promptForValidString("Enter New Title: ");
                        ((Book) material).setTitle(title);
                    } else if (material instanceof Journal) {
                        String journalName = userPrompt.promptForValidString("Enter New Journal Name: ");
                        ((Journal) material).setJournalName(journalName);
                    } else if (material instanceof Magazine) {
                        String magazineName = userPrompt.promptForValidString("Enter New Magazine Name: ");
                        ((Magazine) material).setMagazineName(magazineName);
                    }
                    break;

                case 2: // Author/Journal/Publisher Editing
                    if (material instanceof Book || material instanceof ThesisBook) {
                        String author = userPrompt.promptForValidString("Enter New Author: ");
                        ((Book) material).setAuthor(author);
                    } else if (material instanceof Journal) {
                        String journalName = userPrompt.promptForValidString("Enter New Journal Name: ");
                        ((Journal) material).setJournalName(journalName);
                    }
                    break;

                case 3: // Year Published Editing
                    String yearPublished = userPrompt.promptForValidYear("Enter New Year Published (YYYY): ");
                    material.setYearPublished(yearPublished);
                    break;

                case 4: // Publisher Editing
                    String publisher = userPrompt.promptForValidString("Enter New Publisher: ");
                    material.setPublisher(publisher);
                    break;

                case 5: // Copies Editing
                    int copies = userPrompt.promptForValidInteger("Enter New # of Copies: ");
                    material.setCopies(copies);
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    continue;
            }

            // Save changes to the library
            saveAssets();
            System.out.println("Material Information Edited Successfully!");
            continueEditing = userPrompt.confirmContinue("Continue Editing Another Material?");
        }
    }


private static void DeleteMaterial() {
        loadAssets();
        boolean continueDeleting = true;
        while (continueDeleting) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Delete Material Information");
            System.out.println("********************************************************");
            String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);

            // Find the material by ID
            Material material = findMaterialByID(materialID);
            while (material == null) {
                System.out.println("Material ID not found. Please enter a valid Material ID.");
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                material = findMaterialByID(materialID); // Check again with the new input
            }

            // Show the material type and provide options for editing based on the type
            System.out.println("Material Type: " + material.getClass().getSimpleName());
            System.out.println("Are you sure you want to delete this material?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");

            int deleteChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (deleteChoice) {
                case 1:
                    library.remove(material);
                    System.out.println("Material Information Deleted Successfully!");
                    saveAssets();
                    break;

                case 2:
                    System.out.println("Material Information Deletion Cancelled.");
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    continue;
            }

            continueDeleting = userPrompt.confirmContinue("Continue Deleting Another Material?");
        }
    }

private static void ViewMaterials() {
        loadAssets();
        boolean continueViewing = true;

        while (continueViewing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              View Material Information");
            System.out.println("********************************************************");
            String materialID = userPrompt.promptForValidMaterialID("ENTER MATERIAL ID: ", library);

            // Find the material by ID
            Material material = findMaterialByID(materialID);
            while (material == null) {
                System.out.println("Material ID not found. Please enter a valid Material ID.");
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                material = findMaterialByID(materialID); // Check again with the new input
            }

            System.out.println("MATERIAL TYPE: " + material.getClass().getSimpleName().toUpperCase());
            if (material instanceof Book) {
                System.out.println("TITLE:\n\t" + ((Book) material).getTitle().toUpperCase());
                System.out.println("AUTHOR:\n\t" + ((Book) material).getAuthor().toUpperCase());
            } else if (material instanceof Journal) {
                System.out.println("JOURNAL NAME:\n\t" + ((Journal) material).getJournalName().toUpperCase());
            } else if (material instanceof Magazine) {
                System.out.println("MAGAZINE NAME:\n\t" + ((Magazine) material).getMagazineName().toUpperCase());
            } else if (material instanceof ThesisBook) {
                System.out.println("TITLE:\n\t" + ((ThesisBook) material).getTitle().toUpperCase());
                System.out.println("AUTHOR:\n\t" + ((ThesisBook) material).getAuthor().toUpperCase());
            }
            System.out.println("YEAR PUBLISHED:\n\t" + material.getYearPublished());
            System.out.println("PUBLISHER:\n\t" + material.getPublisher());
            System.out.println("AVAILABLE COPIES:\n\t" + material.getCopies());

            continueViewing = userPrompt.confirmContinue("Viewing Material Information");
        }

    }

private static Material findMaterialByID(String materialId) {
        if (materialId == null || library == null) {
            return null;
        }
        for (Material material : library) {
            if (material.getMaterialID().equalsIgnoreCase(materialId)) {
                return material;
            }
        }
        return null;
    }

    private static String getUniqueMaterialID() {
        while (true) {
            String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
            // Using findMaterialID() to check if the ID exists
            if (!isMaterialIDUnique(materialID)) {
                return materialID;  // Return the valid, unique material ID
            } else {
                System.out.println("Material ID already exists! Please enter a unique Material ID.");
            }
        }
    }

    private static boolean isMaterialIDUnique(String materialID) {
        for (Material material : library) {
            if (material.getMaterialID().equalsIgnoreCase(materialID)) {
                return false;
            }
        }
        return true;
    }

    private static void loadAssets() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("assets.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String type = parts[0];
                String materialID = parts[1];
                String yearPublished = parts[2];
                String publisher = parts[3];
                int copies = Integer.parseInt(parts[4]);

                Material material;
                switch (type.toLowerCase()) {
                    case "book":
                        if (parts.length < 7) {
                            System.out.println("Skipping invalid book line: " + line);
                            continue;
                        }
                        String bookTitle = parts[5];
                        String author = parts[6];
                        material = new Book(materialID, bookTitle, author, yearPublished, publisher, copies);
                        break;
                    case "journal":
                        if (parts.length < 6) {
                            System.out.println("Skipping invalid journal line: " + line);
                            continue;
                        }
                        String journalName = parts[5];
                        material = new Journal(materialID, journalName, yearPublished, publisher, copies);
                        break;
                    case "magazine":
                        if (parts.length < 6) {
                            System.out.println("Skipping invalid magazine line: " + line);
                            continue;
                        }
                        String magazineName = parts[5];
                        material = new Magazine(materialID, magazineName, yearPublished, publisher, copies);
                        break;
                    case "thesisbook":
                        if (parts.length < 7) {
                            System.out.println("Skipping invalid thesis book line: " + line);
                            continue;
                        }
                        String thesisTitle = parts[5];
                        String thesisAuthor = parts[6];
                        material = new ThesisBook(materialID, thesisTitle, thesisAuthor, yearPublished, publisher, copies);
                        break;
                    default:
                        System.out.println("Unknown material type: " + type);
                        continue;
                }
                library.add(material);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void saveAssets() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("assets.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Material material : library) {
                StringBuilder line = new StringBuilder();

                if (material instanceof Book) {
                    Book book = (Book) material;
                    line.append("book,")
                            .append(book.getMaterialID()).append(",")
                            .append(book.getYearPublished()).append(",")
                            .append(book.getPublisher()).append(",")
                            .append(book.getCopies()).append(",")
                            .append(book.getTitle()).append(",")
                            .append(book.getAuthor());
                } else if (material instanceof Magazine) {
                    Magazine magazine = (Magazine) material;
                    line.append("magazine,")
                            .append(magazine.getMaterialID()).append(",")
                            .append(magazine.getYearPublished()).append(",")
                            .append(magazine.getPublisher()).append(",")
                            .append(magazine.getCopies()).append(",")
                            .append(magazine.getMagazineName());
                } else if (material instanceof Journal) {
                    Journal journal = (Journal) material;
                    line.append("journal,")
                            .append(journal.getMaterialID()).append(",")
                            .append(journal.getYearPublished()).append(",")
                            .append(journal.getPublisher()).append(",")
                            .append(journal.getCopies()).append(",")
                            .append(journal.getJournalName());
                } else if (material instanceof ThesisBook) {
                    ThesisBook thesisBook = (ThesisBook) material;
                    line.append("thesis book,")
                            .append(thesisBook.getMaterialID()).append(",")
                            .append(thesisBook.getYearPublished()).append(",")
                            .append(thesisBook.getPublisher()).append(",")
                            .append(thesisBook.getCopies()).append(",")
                            .append(thesisBook.getTitle()).append(",")
                            .append(thesisBook.getAuthor());
                } else {
                    System.out.println("Unknown material type: " + material.getClass().getSimpleName());
                    continue; // Skip unknown material types
                }

                bw.write(line.toString());
                bw.newLine(); // Add a new line for each material
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving assets to file: " + e.getMessage());
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
