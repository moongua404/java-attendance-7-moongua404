package attendance.view;

import attendance.utils.MessageConstants;

public class OutputView {
    public void printMessage(MessageConstants messageConstants) {
        System.out.print(messageConstants.getMessage());
    }

    public void printMessageLn(MessageConstants messageConstants) {
        System.out.println(messageConstants.getMessage());
    }
}