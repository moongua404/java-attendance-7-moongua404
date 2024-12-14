package attendance.model;

public class Tuple {
    private final String name;
    private String date;
    private int time; // 편의상 분으로 합시다... 1440보다 작아야겠죠?

    public Tuple(String name, String date, int time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(int time) {
        this.time = time;
    }
}