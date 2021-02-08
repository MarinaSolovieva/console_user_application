package console.user.application.util;

import console.user.application.operation.Operation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ConsoleInputUtil {

    private static final String EMPTY_LINE = "Пустая строка не является валидным значением!";
    private static final String CHOOSE_POSITION = "Выберите операцию указав соответствующее число от 1 до ";
    private static final String EXIT_TEXT = ". Напишите exit, что бы остановить приложение.";
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    private ConsoleInputUtil() {
    }

    public static BufferedReader getConsoleInput() {
        return BUFFERED_READER;
    }

    public static String consoleInput() throws IOException {
        BufferedReader reader = ConsoleInputUtil.getConsoleInput();
        String result;
        while ((result = reader.readLine()) != null) {
            if (result.equals("")) {
                System.out.println(EMPTY_LINE);
            } else {
                return result;
            }
        }
        return null;
    }

    public static void allOperationConsoleOutput(List<Operation> allOperation) {
        System.out.println(CHOOSE_POSITION + (allOperation.size() + 1));
        for (int i = 0; i < allOperation.size(); i++) {
            System.out.println(i + 1 + ". " + allOperation.get(i).getDescription());
        }
        System.out.println(allOperation.size() + 1 + EXIT_TEXT);
    }

    public static void close() throws IOException {
        BUFFERED_READER.close();
    }
}
