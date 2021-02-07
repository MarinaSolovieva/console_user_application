package console.user.application.service;

import console.user.application.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final String USERS_PATH = "users";

    @Override
    public void save(User user) throws IOException {
        String fileName = user.getName() + user.getSecondName();
        if (!Files.exists(Paths.get(USERS_PATH))) {
            Files.createDirectory(Paths.get(USERS_PATH));
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("users/" + fileName.toLowerCase()))) {
            outputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String name, String secondName) {
        String fileName = name + secondName;
        User user = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("users/" + fileName.toLowerCase()))) {
            user = (User) inputStream.readObject();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Path path = Paths.get(USERS_PATH);
        try {
            Files.walkFileTree(path, new FileVisitorImpl(users));
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return users;
    }

    @Override
    public void delete(String name, String secondName) {
        String fileName = name + secondName;
        try {
            Files.delete(Paths.get("users/" + fileName.toLowerCase()));
            System.out.println("Файл " + fileName + " успешно удален");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
