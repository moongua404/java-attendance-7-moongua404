package attendance.utils;

import java.io.IOException;

public enum ExceptionConstants {
    INVALID_INPUT(IllegalArgumentException.class, "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
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