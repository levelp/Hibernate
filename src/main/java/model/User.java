package model;

import javax.persistence.*;

/**
 * Пользователь системы
 */
@Entity
@Table(name = "`user`")
@NamedQueries({
        @NamedQuery(name = User.ALL_USERS, query = "select u from User u"),
        @NamedQuery(name = User.FIND_BY_LOGIN, query = "select u from User u where u.login = :login")
})
public class User {
    public static final String ALL_USERS = "User.allUsers";
    static final String FIND_BY_LOGIN = "User.findByLogin";

    /**
     * id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private int id;

    /**
     * Логин пользователя
     * Должен быть уникальным
     */
    @Column(unique = true)
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }
}
