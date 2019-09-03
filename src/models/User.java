package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "users")
@NamedQueries({
    @NamedQuery(
            name = "getAllUsers",
            query = "SELECT u FROM User AS u ORDER BY u.id DESC"
            ),
    @NamedQuery(
            name = "getUsersCount",
            query ="SELECT COUNT(u) FROM User AS u"
            ),
    @NamedQuery(
            name = "checkRegisteredUser_id",
            query = "SELECT COUNT(u) FROM User AS u WHERE u.user_id = :user_id"
            ),
    @NamedQuery(
            name = "checkRegisteredEmail",
            query = "SELECT COUNT(u) FROM User AS u WHERE u.email = :email"
            ),
    @NamedQuery(
            name = "checkLoginUser_idAndPassword",
            query = "SELECT u FROM User AS u WHERE u.delete_flag = 0 AND u.user_id = :user_id AND u.password = :pass"
            ),
    @NamedQuery(
            name = "findUserByUser_id",
            query = "SELECT u FROM User AS u WHERE u.user_id = :user_id"
            )
})

@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String user_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
