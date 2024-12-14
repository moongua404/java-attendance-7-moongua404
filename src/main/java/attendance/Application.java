package attendance;

import attendance.controller.AttendanceController;
import attendance.service.AttendanceService;
import attendance.view.InputView;
import attendance.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        AttendanceService attendanceService = new AttendanceService();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        AttendanceController attendanceController = new AttendanceController(attendanceService, inputView, outputView);

        attendanceController.run();
    }
}
