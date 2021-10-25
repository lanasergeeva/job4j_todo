package store;

import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class HbmStore implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(HbmStore.class.getName());

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new HbmStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(item);
            return item;
        } finally {
            tx.commit();
            session.close();
        }
    }

    public boolean isDone(int id) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "update model.Item "
                + "SET done  = :done"
                + " where id = :idParam";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("idParam", id);
            query.setParameter("done", true);
            return query.executeUpdate() > 0;
        } finally {
            tx.commit();
            session.close();
        }
    }



    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "update model.Item "
                + "SET description  = :description"
                + ", created  = :created"
                + " where id = :idParam";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("idParam", id);
            query.setParameter("created", item.getCreated());
            query.setParameter("description", item.getDescription());
            return query.executeUpdate() > 0;
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public boolean deleteCompletedItems() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session
                    .createQuery("delete from model.Item "
                            + "as item where item.done=:done").
                            setParameter("done", true)
                    .executeUpdate() > 0;
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session
                    .createQuery("delete from model.Item "
                            + "as item where item.id=:id").
                            setParameter("id", id)
                    .executeUpdate() > 0;
        } finally {
            tx.commit();
            session.close();
        }
    }


    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from model.Item").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<Item> findAllActiveItems() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from model.Item as item where item.done=:done")
                    .setParameter("done", false).list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<Item> findAllCompItems() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from model.Item as item where item.done=:done")
                    .setParameter("done", true).list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<Item> findByName(String name) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session
                    .createQuery("from model.Item as item where item.description=:name").
                            setParameter("name", name).list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.get(Item.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public void init() {

    }

    public static void main(String[] args) {
        HbmStore hb = new HbmStore();

        Item item2 = new Item("Lana");
        Item item3 = new Item("Max");
        Item item4 = new Item("Maxon");
        //Item item3 = new Item("First3 iem");
        hb.add(item2);
        hb.add(item3);
        hb.add(item4);
        //hb.add(item2);hb.add(item3);

        System.out.println(hb.findAll());
        //System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }
}

