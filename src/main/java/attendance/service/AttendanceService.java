package attendance.service;

import attendance.model.TupleDto;
import attendance.utils.ExceptionConstants;
import attendance.utils.Parser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /*
    private String getFileData(String path) throws Exception {
        try {
            File csv = new File(path);
            return readAllLine(csv);
        } catch (Exception exception) {
            throw ExceptionConstants.INVALID_FILE_IO.getException();
        }
    }

    private String readAllLine(File file) throws Exception {
        BufferedReader br = null;
        StringBuilder data = new StringBuilder();
        br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            data.append(line + "\n");
        }
        return data.toString();
    }
     */
}
