package attendance.controller;

import attendance.service.AttendanceService;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.NoSuchElementException;

public class AttendanceController {
    AttendanceService attendanceService;
    InputView inputView;
    OutputView outputView;

    public AttendanceController(AttendanceService attendanceService, InputView inputView, OutputView outputView) {
        this.attendanceService = attendanceService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        initialize();
        try {
            inputLoop();
        } catch (Exception exception) {
            outputView.printMessageLn(exception.getMessage());
        }
    }

    private void initialize() {
        
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
