package store;

import model.Item;

import java.util.List;

public interface Store {
    void init();

    Item add(Item item);


    boolean delete(int id);

    List<Item> findAll();

    List<Item> findByName(String key);

    List<Item> findAllActiveItems();

    List<Item> findAllCompItems();

    Item findById(int id);

    boolean isDone(int id);

    boolean isNotDone(int id);

    boolean deleteCompletedItems();

}
