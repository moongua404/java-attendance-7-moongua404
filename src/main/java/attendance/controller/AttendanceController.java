package attendance.controller;

import attendance.service.AttendanceService;
import attendance.view.InputView;
import attendance.view.OutputView;

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
        System.out.println("Hello World");
    }
}
