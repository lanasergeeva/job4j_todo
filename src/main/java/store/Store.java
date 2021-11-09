package store;

import model.Category;
import model.Item;
import model.User;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.NoResultException;
import java.util.List;

public interface Store {

    Item add(Item item);

    boolean delete(int id);

    List<Item> findAll();

    List<Category> findAllCategories();

    List<Item> findAllByUser(User user);

    List<Item> findAllActiveItemsByUser(User user);

    List<Item> findByName(String key);

    List<Item> findAllActiveItems();

    List<Item> findAllCompItems();

    List<Item> findAllCompItemsByUser(User user);

    User findByNameUser(String name) throws NoResultException;

    boolean isDone(int id);

    boolean isNotDone(int id);

    boolean deleteCompletedItems();

    User addUser(User user) throws ConstraintViolationException;


}
