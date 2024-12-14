package attendance.controller;

import attendance.model.AttendanceStatus;
import attendance.model.Repository;
import attendance.model.Tuple;
import attendance.model.TupleDto;
import attendance.service.AttendanceService;
import attendance.utils.ExceptionConstants;
import attendance.utils.MessageConstants;
import attendance.utils.Parser;
import attendance.utils.Validator;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AttendanceController {
    AttendanceService attendanceService;
    InputView inputView;
    OutputView outputView;
    Repository attendanceRepository;

    private static final String QUIT_MARK = "Q";

    public AttendanceController(AttendanceService attendanceService, InputView inputView, OutputView outputView,
                                Repository repository) {
        this.attendanceService = attendanceService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.attendanceRepository = repository;
    }

    public void run() throws Exception {
        initialize();
        try {
            inputLoop();
        } catch (Exception exception) {
            outputView.printMessageLn(exception.getMessage());
        }
    }

    private void initialize() throws Exception {
        List<TupleDto> tupleDto = attendanceService.loadAttendance();
        List<Tuple> tuples = tupleDto.stream().map(Parser::dtoToTuple).toList();
        attendanceRepository.addAll(tuples);
    }

    private void inputLoop() throws Exception {
        while (true) {
            try {
                String response = getResponse();
                if (Objects.equals(response, QUIT_MARK)) {
                    break;
                }
                outputView.newLine();
                handleRequest(response);
            } catch (Exception exception) {
                handleInputException(exception);
            }
        }
    }

    private String getResponse() throws Exception {
        String[] today = attendanceService.getToday().split("-");
        return inputView.getString(
                String.format(MessageConstants.START_GUIDE.getMessage(), today[1], today[2],
                        attendanceService.getWeek(attendanceService.getToday())));
    }

    private void handleRequest(String response) throws Exception {
        if (Objects.equals(response, "1")) {
            attendanceCheck();
            return;
        }
        if (Objects.equals(response, "2")) {
            attendanceModify();
            return;
        }
        if (Objects.equals(response, "3")) {
            memberAttendanceCheck();
            return;
        }
        if (Objects.equals(response, "4")) {
            dangerousMemberCheck();
            return;
        }
        throw ExceptionConstants.INVALID_FORMAT.getException();
    }

    private String getNickname(MessageConstants messageConstants) throws Exception {
        String name = inputView.getString(messageConstants);
        if (attendanceRepository.findAll(name) == null) {
            throw ExceptionConstants.NICKNAME_NOT_FOUND.getException();
        }
        if (attendanceRepository.findOne(name, attendanceService.getToday()) != null) {
            throw ExceptionConstants.DUPLICATED_ATTENDANCE.getException();
        }
        return name;
    }

    private int getTime(MessageConstants messageConstants) throws Exception {
        String time = inputView.getString(messageConstants);
        Validator.checkTime(time);
        return Parser.StringToTime(time);
    }

    private int getDay(MessageConstants messageConstants) {
        int day = inputView.getInt(messageConstants);
        return day;
    }

    private void printAttendance(Tuple target, int time) {
        outputView.newLine();
        AttendanceStatus status = AttendanceStatus.distinguish(attendanceService.getStartTime(target.getDate()), time);
        outputView.printAttendance(target, attendanceService.getWeek(target.getDate()), status);
        outputView.newLine();
    }

    private void printAttendanceChange(Tuple target1, int time1, Tuple target2, int time2) {
        outputView.newLine();
        AttendanceStatus status1 = AttendanceStatus.distinguish(attendanceService.getStartTime(target1.getDate()),
                time1);
        outputView.printAttendanceWithNoIndent(target1, attendanceService.getWeek(target1.getDate()), status1);
        outputView.printMessage(MessageConstants.OUTPUT_MODIFY_MARK);
        AttendanceStatus status2 = AttendanceStatus.distinguish(attendanceService.getStartTime(target2.getDate()),
                time2);
        outputView.printAttendanceWithNoIndent(target2, attendanceService.getWeek(target2.getDate()), status2);
        outputView.newLine();
        outputView.newLine();
    }

    private void attendanceCheck() throws Exception {
        String name = getNickname(MessageConstants.INPUT_NICKNAME_GUIDE);
        int time = getTime(MessageConstants.INPUT_TIME_GUIDE);
        Tuple extra = new Tuple(name, attendanceService.getToday(), time);
        attendanceRepository.add(extra);
        printAttendance(extra, time);
    }

    private void attendanceModify() throws Exception {
        String name = getNickname(MessageConstants.INPUT_MODIFY_NICKNAME_GUIDE);
        String day = Parser.intToDate(2024, 12, getDay(MessageConstants.INPUT_MODIFY_DATE_GUIDE));
        int time = getTime(MessageConstants.INPUT_MODIFY_TIME_GUIDE);
        Tuple modify = new Tuple(name, day, time);
        Tuple exist = attendanceRepository.findOne(name, day);
        Tuple legacy = new Tuple(exist.getName(), exist.getDate(), exist.getTime());
        if (exist == null) {
            attendanceRepository.add(modify);
            printAttendanceChange(attendanceService.getTrashTuple(modify), -1, modify, time);
            return;
        }
        attendanceRepository.modify(modify);
        printAttendanceChange(legacy, legacy.getTime(), modify, time);
    }

    private void memberAttendanceCheck() {

    }

    private void dangerousMemberCheck() {

    }

    private void handleInputException(Exception exception) throws Exception {
        if (exception instanceof NoSuchElementException) {
            throw exception;
        }
        outputView.printMessageLn(exception.getMessage());
    }
}
