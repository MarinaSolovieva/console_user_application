package console.user.application.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputUtil {

    public static final String EMPTY_LINE = "Пустая строка не является валидным значением!";

    private ConsoleInputUtil() {
    }

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

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

    public static void close() throws IOException {
        BUFFERED_READER.close();
    }
}
