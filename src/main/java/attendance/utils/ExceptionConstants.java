package attendance.utils;

import java.io.IOException;

public enum ExceptionConstants {
    INVALID_FORMAT(IllegalArgumentException.class, "잘못된 형식을 입력하였습니다."),
    NICKNAME_NOT_FOUND(IllegalStateException.class, "등록되지 않은 닉네임입니다."),
    HOLIDAY_ACCESS(IllegalStateException.class, "%02d월 %02d일 %c요일은 등교일이 아닙니다."),
    FUTURE_ACCESS(IllegalStateException.class, "아직 수정할 수 없습니다."),
    NOT_RUNNING_TIME(IllegalStateException.class, "캠퍼스 운영 시간에만 출석이 가능합니다."),
    DUPLICATED_ATTENDANCE(IllegalStateException.class, "이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요."),

    INVALID_FILE_IO(IOException.class, "파일 입출력 과정에서 문제가 생겼습니다."),
    EMPTY_INPUT(UnsupportedOperationException.class, "빈 값이 입력되어 프로그램의 안정성을 위해 종료합니다.");

    private final Class<? extends Exception> exception;
    private final String message;

    ExceptionConstants(Class<? extends Exception> exception, String message) {
        this.exception = exception;
        this.message = "[ERROR] " + message;
    }

    public Exception getException() {
        try {
            return this.exception.getConstructor(String.class).newInstance(message);
        } catch (Exception exception) {
            throw new RuntimeException("[ERROR] 예외처리 과정에서 문제가 생겼습니다. ");
        }
    }
}