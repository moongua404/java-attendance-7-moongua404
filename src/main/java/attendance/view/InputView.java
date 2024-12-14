package attendance.view;

import attendance.utils.MessageConstants;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    String getString(MessageConstants messageConstants) {
        System.out.println(messageConstants.getMessage());
        return Console.readLine();
    }

    int getIne(MessageConstants messageConstants) {
        System.out.println(messageConstants.getMessage());
        return Integer.parseInt(Console.readLine());
    }
}
