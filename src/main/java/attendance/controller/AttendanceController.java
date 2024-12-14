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
                String response = inputView.getString(MessageConstants.START_GUIDE);
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

    private String getNickname() throws Exception {
        String name = inputView.getString(MessageConstants.INPUT_NICKNAME_GUIDE);
        if (attendanceRepository.findAll(name) == null) {
            throw ExceptionConstants.NICKNAME_NOT_FOUND.getException();
        }
        if (attendanceRepository.findOne(name, attendanceService.getToday()) != null) {
            throw ExceptionConstants.DUPLICATED_ATTENDANCE.getException();
        }
        return name;
    }

    private int getInputTime() throws Exception {
        String time = inputView.getString(MessageConstants.INPUT_TIME_GUIDE);
        Validator.checkTime(time);
        return Parser.StringToTime(time);
    }

    private void attendanceCheck() throws Exception {
        String name = getNickname();
        int time = getInputTime();
        Tuple extra = new Tuple(name, attendanceService.getToday(), time);
        attendanceRepository.add(extra);
        outputView.newLine();
        AttendanceStatus status = AttendanceStatus.distinguish(attendanceService.getStartTime(extra.getDate()), time);
        outputView.printAttendance(extra, attendanceService.getWeek(extra.getDate()), status);
        outputView.newLine();
    }

    private void attendanceModify() {

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
