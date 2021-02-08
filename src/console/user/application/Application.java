package console.user.application;

import console.user.application.operation.OperationRunnable;
import console.user.application.util.ConsoleInputUtil;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Добро пожаловть в приложение!");

        Thread mainProgram = new Thread(new OperationRunnable());
        mainProgram.start();
        mainProgram.join();
        ConsoleInputUtil.close();

        System.out.println("Приложение остановлено!");
    }
}
