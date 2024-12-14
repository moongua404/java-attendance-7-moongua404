package attendance.model;

public enum AttendanceStatus {
    ATTEND("출석"),
    LATE("지각"),
    ABSENT("결석");

    private String called;

    AttendanceStatus(String s) {
        called = s;
    }

    public String getMessage() {
        return called;
    }

    public static AttendanceStatus distinguish(int startTime, int currentTime) {
        if (currentTime - startTime <= 5) {
            return ATTEND;
        }
        if (currentTime - startTime < 30) {
            return LATE;
        }
        return ABSENT;
    }
}
