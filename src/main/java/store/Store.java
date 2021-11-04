package store;

import model.Item;
import model.User;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.NoResultException;
import java.util.List;

public interface Store {
    void init();

    Item add(Item item);

    boolean delete(int id);

    List<Item> findAll();

    List<Item> findAllByUser(User user);

    List<Item> findByName(String key);

    List<Item> findAllActiveItems();

    List<Item> findAllCompItems();

    Item findById(int id);

    User findByNameUser(String name) throws NoResultException;

    boolean isDone(int id);

    boolean isNotDone(int id);

    boolean deleteCompletedItems();

    User addUser(User user) throws ConstraintViolationException;


}
