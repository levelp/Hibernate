package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Пользователь системы
 */
@Entity
@Table(name = "`user`")
@NamedQueries({
        @NamedQuery(name = User.ALL_USERS,
                query = "select u from User u"),
        @NamedQuery(name = User.FIND_BY_LOGIN,
                query = "select u from User u where u.login = :login")
})
public class User { // UserInner
    public static final String ALL_USERS = "User.allUsers";
    static final String FIND_BY_LOGIN = "User.findByLogin";
    // Группа пользователя:
    //  много пользователей входят в одну группу
    // FetchType.EAGER - ранняя загрузка
    // FetchType.LAZY
    @ManyToOne(targetEntity = Group.class, fetch = FetchType.EAGER)
    Group group;
    @ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "user_friend_id", referencedColumnName = "USER_ID"))
    List<User> friends = new ArrayList<>();
    /**
     * id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", insertable = false, updatable = false)
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<User> getFriends() {
        return friends;
    }

}
