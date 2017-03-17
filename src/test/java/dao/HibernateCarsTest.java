package dao;

import model.Car;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Работа с базой данных автомобилей
 */
public class HibernateCarsTest {
    // Определяет подключение к БД - какую БД сейчас использовать?
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("Cars-MySQL");
        em = emf.createEntityManager();
    }

    @Test
    public void testAddCar() throws Exception {
        // Создаём объект в базе данных
        Car car = new Car();
        car.setNumber("123456789");
        em.persist(car);
    }
}
