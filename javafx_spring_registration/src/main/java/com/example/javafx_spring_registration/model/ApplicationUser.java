package com.example.javafx_spring_registration.model;

import javax.persistence.*;
import java.security.MessageDigest;
import static java.nio.charset.StandardCharsets.UTF_8;

@Entity(name = "application_user")
@Table(name = "application_user",
        uniqueConstraints = {
            @UniqueConstraint(name = "application_user_unique_email", columnNames = "email")
        }
)
public class ApplicationUser {

    @Id
    @SequenceGenerator(
            name = "application_user_sequence",
            sequenceName = "application_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "application_user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "user_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String userName;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    public ApplicationUser(Long id, String userName, String email, String password) {
        this.id = id;
        setUserName(userName);
        setEmail(email);
        setPassword(password);
    }

    public ApplicationUser(String userName, String email, String password) {
        setUserName(userName);
        setEmail(email);
        setPassword(password);
    }

    public ApplicationUser() {

    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encrpytPasswd(password);
    }

    public static String encrpytPasswd(String password)
    {
        try
        {
            MessageDigest mg = MessageDigest.getInstance("SHA-256");
            byte[] digestedData = mg.digest(password.getBytes(UTF_8));

            StringBuilder out = new StringBuilder();
            for (byte b : digestedData) {
                out.append(String.format("%02x", b));
            }
            return out.toString();
        }
        catch (Exception e)
        {
            System.out.println("could not encrypt password");
            return password;
        }
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
