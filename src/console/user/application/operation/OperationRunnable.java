package console.user.application.operation;

import console.user.application.util.ConsoleInputUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static console.user.application.util.ConsoleInputUtil.allOperationConsoleOutput;
import static console.user.application.util.ParamValidator.isCorrectPosition;

public class OperationRunnable implements Runnable {

    private final List<Operation> allOperation = new OperationFactory().getAllOperation();

    @Override
    public void run() {
        while (true) {
            allOperationConsoleOutput(allOperation);
            BufferedReader reader = ConsoleInputUtil.getConsoleInput();
            String input;
            try {
                while ((input = reader.readLine()) != null) {
                    if ("exit".equalsIgnoreCase(input)) {
                        return;
                    } else if (isCorrectPosition(input, allOperation)) {
                        int operationPosition = Integer.parseInt(input) - 1;
                        allOperation.get(operationPosition).getConsumer().accept(reader);
                    } else {
                        System.out.println("Указано некорректное число. Пожалуйста повторите попытку...");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
