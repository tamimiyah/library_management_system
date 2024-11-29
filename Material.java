
import java.time.LocalDate;

public class Material {

    private String materialID, yearPublished, publisher;
    private int copies;
    private boolean borrowed;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Material(String materialID, String yearPublished, String publisher, int copies) {
        this.materialID = materialID;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.copies = copies;
        this.borrowed = false;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public boolean isAvailable() {
        return copies > 0 && !borrowed;
    }

    public void borrow() {
        if (isAvailable()) {
            borrowed = true;
            copies--;
            borrowDate = LocalDate.now();
        }
    }

    public void returnMaterial(LocalDate returnDate) {
        if (borrowed) {
            borrowed = false;
            copies--;
            this.returnDate = returnDate;
        }
    }

    public void returnMaterial() {
        if (borrowed) {
            borrowed = false;
            copies++;
            borrowDate = null;
            returnDate = null;
        }
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getReturnDateInDays() {
        return (returnDate != null) ? returnDate.getDayOfYear() - borrowDate.getDayOfYear() : 0;
    }

    // This method should be overridden by subclasses
    public int getReturnDateDays() {
        return 0;
    }
}
