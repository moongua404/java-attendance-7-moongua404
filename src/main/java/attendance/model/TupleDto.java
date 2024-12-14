package attendance.model;

public class TupleDto {
    private String name;
    private String date;
    private int time;

    public TupleDto(String name, String date, int time) {
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
}
