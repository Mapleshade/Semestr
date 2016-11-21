package DAO;

import Classes.User;
import java.util.List;

public interface UsersDAO {

    User find(int id);

    User find(String login);

    List<User> findAll();

    void save(User user);

    void delete(int id);

    void update(User user);

}
