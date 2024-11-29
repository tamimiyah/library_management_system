
public class Magazine extends Material {
    private String magazineName;

    public Magazine(String materialID, String journalName, String yearPublished, String publisher, int copies) {
        super( materialID,  yearPublished,  publisher,  copies);
        this.magazineName = magazineName;
    }

    public void setMagazineName(String journalName) {
        this.magazineName = magazineName;
    }

    public String getMagazineName() {
        return magazineName;
    }

    @Override
    public String toString() {
        return "Material ID: " + getMaterialID() +
                "\nType: Magazine" +
                "\nMagazine Name: " + magazineName +
                "\nYear Published: " + getYearPublished() +
                "\nPublisher: " + getPublisher() +
                "\nCopies: " + getCopies();
    }

    @Override
    public int getReturnDateDays() {
        return 1; // 1 day for magazines (same-day return)
    }
}
