package pl.library.dao.help;

public class MonthView {

    private int month;
    private int total;

    public MonthView(int month, int total) {
        this.month = month;
        this.total = total;
    }

    public int getMonth() {
        return month;
    }

    public int getTotal() {
        return total;
    }

}
