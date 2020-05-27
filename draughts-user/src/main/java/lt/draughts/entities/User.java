package lt.draughts.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Inheritance
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@NamedQueries({
        @NamedQuery(name = "User.FindUserByEmail", query = "select u from User as u " +
                "WHERE u.email=:email"),
        @NamedQuery(name = "User.ExistByEmail", query = "SELECT COUNT(u)>0 " +
                "FROM User as u " +
                "WHERE u.email=:email"),
        @NamedQuery(name = "User.GetAll", query = "SELECT u " +
                "FROM User as u "),
        @NamedQuery(name = "User.GetStudents", query = "SELECT u " +
                "FROM User as u " +
                "JOIN u.role as r " +
                "WHERE r.id=2"),
        @NamedQuery(name = "User.GetCoach", query = "SELECT u " +
                "FROM User as u " +
                "JOIN u.role as r " +
                "WHERE r.id=1"),
        @NamedQuery(name = "Coach.FindCoachByEmail", query = "SELECT u " +
                "FROM User as u " +
                "JOIN u.role as r " +
                "WHERE r.id=1 AND u.email=:email"),
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Email cannot be null.")
    @Email(message = "Wrong email format.")
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @NotNull(message = "First name cannot be null.")
    @Column(name = "firstName")
    private String firstname;

    @NotNull(message = "Last name cannot be null.")
    @Column(name = "lastName")
    private String lastName;

    @NotNull(message = "Phone cannot be null")
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "Role cannot be null")
    @ManyToOne
    private Role role;

    public User(String firstname, String lastName, String email, String password, String phone) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User() {
    }
}