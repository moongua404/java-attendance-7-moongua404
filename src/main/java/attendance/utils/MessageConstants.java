package attendance.utils;

public enum MessageConstants {
    START_GUIDE("오늘은 %02d월 %02d일 금요일입니다. 기능을 선택해 주세요.\n"
            + "1. 출석 확인\n"
            + "2. 출석 수정\n"
            + "3. 크루별 출석 기록 확인\n"
            + "4. 제적 위험자 확인\n"
            + "Q. 종료"),
    INPUT_NICKNAME_GUIDE("닉네임을 입력해 주세요."),
    INPUT_TIME_GUIDE("등교 시간을 입력해 주세요."),
    INPUT_MODIFY_NICKNAME_GUIDE("출석을 수정하려는 크루의 닉네임을 입력해 주세요."),
    INPUT_MODIFY_DATE_GUIDE("수정하려는 날짜(일)를 입력해 주세요."),
    INPUT_MODIFY_TIME_GUIDE("언제로 변경하겠습니까?"),

    OUTPUT_ATTENDANCE_NORMAL("%02d월 %902d일 %c요일 %02d:%02d (%s)"),
    OUTPUT_ATTENDANCE_NULL("%02d월 %02d일 %c요일 --:-- (결석)"),
    OUTPUT_MODIFY_MARK(" -> "),
    OUTPUT_MODIFY_MESSAGE(" 수정 완료!"),
    OUTPUT_ATTENDANCE_MESSAGE("이번 달 %s의 출석 기록입니다."),
    OUTPUT_DANGEROUS_MESSAGE("제적 위험자 조회 결과"),
    OUTPUT_ATTENDANCE_WITH_MEMBER("- %s: 결석 %d회, 지각 %d회 (%s)");

    private final String message;

    MessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}








