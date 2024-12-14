package attendance.utils;

import attendance.model.Tuple;
import attendance.model.TupleDto;

public class Parser {
    private static final String TIME_DELIMITER = ":";

    public static int StringToTime(String time) {
        String[] parts = time.split(TIME_DELIMITER);
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public static String timeToString(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }

    public static Tuple dtoToTuple(TupleDto dto) {
        return new Tuple(dto.getName(), dto.getDate(), dto.getTime());
    }

    public static String intToDate(int year, int month, int day) {
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}
