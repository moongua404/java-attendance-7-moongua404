package attendance.service;

import attendance.model.TupleDto;
import attendance.utils.ExceptionConstants;
import attendance.utils.Parser;
import camp.nextstep.edu.missionutils.DateTimes;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceService {
    private static final String LINE_DELIMITER = "\n";
    private static final String CONTENT_DELIMITER = ",| ";

    private final String FILE_PATH = "src/main/resources/attendances.csv";

    public List<TupleDto> loadAttendance() throws Exception {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = splitLine(getFileData(path));
        return lines.stream()
                .skip(1)
                .map(this::parseLineToTupleDto)
                .collect(Collectors.toList());
    }

    public String getToday() throws Exception {
        try {
            return DateTimes.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw ExceptionConstants.INVALID_FORMAT.getException();
        }
    }

    private List<String> splitLine(String bundle) {
        return Arrays.stream(bundle.split(LINE_DELIMITER)).toList();
    }

    private TupleDto parseLineToTupleDto(String line) {
        String[] parts = line.split(CONTENT_DELIMITER);
        return new TupleDto(parts[0], parts[1], Parser.StringToTime(parts[2]));
    }

    private String getFileData(Path path) throws Exception {
        try {
            return Files.readString(path);
        } catch (Exception exception) {
            throw ExceptionConstants.INVALID_FILE_IO.getException();
        }
    }

    public char getWeek(String date) {
        int target = Integer.parseInt(date.split("-")[2]);
        if (target % 7 == 1) {
            return '일';
        }
        if (target % 7 == 2) {
            return '월';
        }
        if (target % 7 == 3) {
            return '화';
        }
        if (target % 7 == 4) {
            return '수';
        }
        if (target % 7 == 5) {
            return '목';
        }
        if (target % 7 == 6) {
            return '금';
        }
        return '토';
    }

    public int getStartTime(String date) {
        if (getWeek(date) == '월') {
            return 13 * 60;
        }
        return 10 * 60;
    }
}
