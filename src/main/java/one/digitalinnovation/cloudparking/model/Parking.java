package one.digitalinnovation.cloudparking.model;

public class Parking {
    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private String entryDate;
    private String exitDate;
    private String bill;
    
    public Parking(String id, String license, String state, String model, String color) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }

    public Parking () {}
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
    public String getExitDate() {
        return exitDate;
    }
    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }
    public String getBill() {
        return bill;
    }
    public void setBill(String bill) {
        this.bill = bill;
    }
}
