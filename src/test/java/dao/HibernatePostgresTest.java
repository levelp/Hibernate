package dao;

import model.User;
import model.UserAlreadyExistsException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class HibernatePostgresTest extends Assert {
    // Определяет подключение к БД - какую БД сейчас использовать?
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("Unit-tests-Postgres");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Close EntityManagerFactory");
        emf.close();
    }

    /**
     * Добавляем одного пользователя, находим его по id
     */
    @Test
    public void testOneUser() throws Exception {
        String userName = "admin";
        em.getTransaction().begin();
        try {
            if (findByLogin(userName) != null) {
                throw new UserAlreadyExistsException();
            }
            User user = new User();
            user.setLogin(userName);
            em.persist(user);

            // Печатаем всех пользователей
            for (User u : em.createNamedQuery(User.ALL_USERS, User.class).getResultList()) {
                System.out.println(u.getId() + " login = " + u.getLogin());
            }

            // Это первый сохранённый пользователь, его id 1
            User user1 = em.find(User.class, 1);
            assertEquals(userName, user1.getLogin());
        } finally {
            em.getTransaction().commit();
        }
    }

    /**
     * Получение пользователя по логину из БД
     *
     * @param login логин
     * @return Пользователь или null если пользователь не найден
     */
    private User findByLogin(String login) {
        try {
            return (User) em.createQuery(
                    "SELECT u FROM User u WHERE u.login = :login").
                    setParameter("login", login).getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

    private String getPersistenceProperty(String propertyName) {
        return (String) emf.getProperties().get(propertyName);
    }

    @Test
    public void testGetConnectionProperties() {
        String database = getPersistenceProperty("javax.persistence.jdbc.url");
        String dbUser = getPersistenceProperty("javax.persistence.jdbc.user");
        // Невозможно получить пароль в явном виде
        String dbPassword = getPersistenceProperty("javax.persistence.jdbc.password");
        System.out.println("database = " + database);
        System.out.println("dbUser = " + dbUser);
        System.out.println("dbPassword = " + dbPassword);
    }
}