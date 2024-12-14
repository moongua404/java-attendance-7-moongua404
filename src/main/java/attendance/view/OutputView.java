package attendance.view;

import attendance.model.AttendanceStatus;
import attendance.model.Tuple;
import attendance.utils.MessageConstants;
import attendance.utils.Parser;

public class OutputView {
    public void printMessage(MessageConstants messageConstants) {
        System.out.print(messageConstants.getMessage());
    }

    public void printMessageLn(MessageConstants messageConstants) {
        System.out.println(messageConstants.getMessage());
    }

    public void printMessageLn(String message) {
        System.out.println(message);
    }

    public void newLine() {
        System.out.println();
    }

    public void printAttendance(Tuple tuple, char weekDay, AttendanceStatus status) {
        String[] date = tuple.getDate().split("-");
        System.out.printf((MessageConstants.OUTPUT_ATTENDANCE_NORMAL.getMessage()) + "%n", date[1], date[2], weekDay,
                Parser.timeToString(tuple.getTime()), status.getMessage());
    }
}