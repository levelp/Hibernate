package model;

/**
 * Исключение если пользователь уже есть
 */
public class UserAlreadyExistsException extends Exception {
    @Override
    public String toString() {
        return "A user with the same login is already exists";
    }
}
