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
        if (tuple.getTime() == -1) {
            System.out.printf((MessageConstants.OUTPUT_ATTENDANCE_NULL.getMessage()) + "%n", date[1], date[2], weekDay);
        }
        System.out.printf((MessageConstants.OUTPUT_ATTENDANCE_NORMAL.getMessage()) + "%n", date[1], date[2], weekDay,
                Parser.timeToString(tuple.getTime()), status.getMessage());
    }

    public void printAttendanceWithNoIndent(Tuple tuple, char weekDay, AttendanceStatus status) {
        String[] date = tuple.getDate().split("-");
        System.out.printf((MessageConstants.OUTPUT_ATTENDANCE_NORMAL.getMessage()), date[1], date[2], weekDay,
                Parser.timeToString(tuple.getTime()), status.getMessage());
    }

    public void printPersonal(Tuple tuple, char weekDay, AttendanceStatus status) {
        String[] date = tuple.getDate().split("-");
        if (tuple.getTime() == -1) {
            System.out.printf("12월 %s일 %c요일 --:-- (결석)\n", date[2], weekDay);
        }
        System.out.printf("12월 %s일 %c요일 %02d:%02d (%s)\n", date[2], weekDay, tuple.getTime() / 60,
                tuple.getTime() % 60, status.getMessage());
    }
}