package pl.library.model;

public class Location {

    private int id;
    private String section;
    private String shelf;
    private String rack;

    public Location(int id, String section, String shelf, String rack) {
        this.id = id;
        this.section = section;
        this.shelf = shelf;
        this.rack = rack;
    }

    public int getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public String getShelf() {
        return shelf;
    }

    public String getRack() {
        return rack;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }
}
