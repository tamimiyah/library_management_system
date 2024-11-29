
public class Journal extends Material {
    private String journalName;

    public Journal(String materialID, String journalName, String yearPublished, String publisher, int copies) {
        super( materialID,  yearPublished,  publisher,  copies);
        this.journalName = journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getJournalName() {
        return journalName;
    }

    @Override
    public String toString() {
        return "Material ID: " + getMaterialID() +
                "\nType: Journal" +
                "\nJournal Name: " + journalName +
                "\nYear Published: " + getYearPublished() +
                "\nPublisher: " + getPublisher() +
                "\nCopies: " + getCopies();
    }

    @Override
    public int getReturnDateDays() {
        return 3; // 3 days for journals
    }
}
