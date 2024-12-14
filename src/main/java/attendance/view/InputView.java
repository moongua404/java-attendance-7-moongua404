package attendance.view;

import attendance.utils.MessageConstants;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String getString(MessageConstants messageConstants) {
        System.out.println(messageConstants.getMessage());
        return Console.readLine();
    }

    public String getString(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    public int getInt(MessageConstants messageConstants) {
        System.out.println(messageConstants.getMessage());
        return Integer.parseInt(Console.readLine());
    }
}
