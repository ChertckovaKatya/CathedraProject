package cathedra.model;

import java.util.Objects;

public class User {
    private String name;
    private String password;
    private ROLE role;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, password);
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ROLE getRole() {
        return role;
    }

    public User(String name, String password, ROLE role) {

        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }



    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}

