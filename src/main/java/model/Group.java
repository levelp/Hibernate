package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// TABLE_NAME = Group
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    /**
     * Название группы
     */
    String name;

    @OneToMany(targetEntity = User.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "group")
    List<User> users = new ArrayList<>();

    protected Group() {
    }

    public Group(String name) {
        this.name = name;
    }
}
