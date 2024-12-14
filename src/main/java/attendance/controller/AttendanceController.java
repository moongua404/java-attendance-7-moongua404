package attendance.controller;

import attendance.model.Repository;
import attendance.model.Tuple;
import attendance.model.TupleDto;
import attendance.service.AttendanceService;
import attendance.utils.Parser;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.List;
import java.util.NoSuchElementException;

public class AttendanceController {
    AttendanceService attendanceService;
    InputView inputView;
    OutputView outputView;

    Repository attendanceRepository;

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

            } catch (Exception exception) {
                handleInputException(exception);
            }
        }
    }


    private void handleInputException(Exception exception) throws Exception {
        if (exception instanceof NoSuchElementException) {
            throw exception;
        }
        outputView.printMessageLn(exception.getMessage());
    }
}
