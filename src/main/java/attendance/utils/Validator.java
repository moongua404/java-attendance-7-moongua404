package attendance.utils;

public class Validator {
    private static final String TIME_DELIMITER = ":";
    private static final int MAX_TIME = 1440;

    public static void checkTime(String time) throws Exception {
        String[] parts = time.split(TIME_DELIMITER);
        if (Integer.parseInt(parts[0]) < 0 || Integer.parseInt(parts[0]) >= 24
                || Integer.parseInt(parts[1]) < 0 || Integer.parseInt(parts[0]) >= 60
                || Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]) >= MAX_TIME) {
            throw ExceptionConstants.INVALID_FORMAT.getException();
        }
    }
}
