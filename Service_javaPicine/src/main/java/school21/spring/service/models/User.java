package school21.spring.service.models;

import java.util.Objects;

public class User {

    private final Long ID;
    private String email;

    public User(Long id, String email) {
        this.ID = id;
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID.equals(user.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
