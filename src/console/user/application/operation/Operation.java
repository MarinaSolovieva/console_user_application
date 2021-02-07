package console.user.application.operation;

import java.io.BufferedReader;
import java.util.function.Consumer;

public class Operation {
    private final String description;
    private final Consumer<BufferedReader> consumer;

    public Operation(String description, Consumer<BufferedReader> consumer) {
        this.description = description;
        this.consumer = consumer;
    }

    public String getDescription() {
        return description;
    }

    public Consumer<BufferedReader> getConsumer() {
        return consumer;
    }
}
