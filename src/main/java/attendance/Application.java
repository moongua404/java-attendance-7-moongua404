package attendance;

import attendance.controller.AttendanceController;
import attendance.model.Repository;
import attendance.service.AttendanceService;
import attendance.view.InputView;
import attendance.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        AttendanceService service = new AttendanceService();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Repository repository = new Repository();
        AttendanceController controller = new AttendanceController(service, inputView, outputView, repository);

        try {
            controller.run();
        } catch (Exception exception) {
            System.out.println(exception.fillInStackTrace());
        }
    }
}
