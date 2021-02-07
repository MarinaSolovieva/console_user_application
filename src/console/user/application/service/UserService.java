package console.user.application.service;

import console.user.application.model.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void save(User user) throws IOException;

    User get(String name, String secondName);

    List<User> getAll();

    void delete(String name, String secondName);
}
