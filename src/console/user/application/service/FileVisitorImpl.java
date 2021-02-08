package console.user.application.service;

import console.user.application.model.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FileVisitorImpl extends SimpleFileVisitor<Path> {

    private final List<User> users;

    public FileVisitorImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("users/" + file.getFileName().toString()))) {
            User user =  (User) inputStream.readObject();
            users.add(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }
}

