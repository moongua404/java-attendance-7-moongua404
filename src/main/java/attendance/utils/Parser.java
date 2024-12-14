package attendance.utils;

import attendance.model.Tuple;
import attendance.model.TupleDto;

public class Parser {
    private static final String TIME_DELIMITER = ":";

    public static int StringToTime(String time) {
        String[] parts = time.split(TIME_DELIMITER);
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public static Tuple dtoToTuple(TupleDto dto) {
        return new Tuple(dto.getName(), dto.getDate(), dto.getTime());
    }
}
