package console.user.application.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 1;
    private final String name;
    private final String secondName;
    private final String email;
    private final List<Role> roles;
    private final List<String> phones;

    public User(String name, String secondName, String email, List<Role> roles, List<String> phones) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.roles = roles;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<String> getPhones() {
        return phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, email, roles, phones);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", phones=" + phones +
                '}';
    }
}
