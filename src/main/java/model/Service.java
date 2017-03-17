package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service") // "Service"
public class Service {
    @Column(name = "name")
    String name;
    // Типы загрузки:
    // FetchType.EAGER - загружаем все объекты сразу
    // FetchType.LAZY - загружать только когда обратимся к полю
    @ManyToMany(targetEntity = Car.class, fetch = FetchType.EAGER)
    List<Car> cars = new ArrayList<>();
    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
