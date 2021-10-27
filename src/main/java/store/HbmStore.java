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
import java.util.List;
import java.util.function.Function;

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
        tx(session -> session.save(item));
        return item;
    }

    @Override
    public boolean isDone(int id) {
        String hql = "update model.Item "
                + "SET done  = :done"
                + " where id = :idParam";
        return tx(session -> {
                    Query query = session.createQuery(hql);
                    query.setParameter("idParam", id);
                    query.setParameter("done", true);
                    return query.executeUpdate() > 0;
                }
        );
    }

    @Override
    public boolean isNotDone(int id) {
        String hql = "update model.Item "
                + "SET done  = :done"
                + " where id = :idParam";
        return tx(session -> {
                    Query query = session.createQuery(hql);
                    query.setParameter("idParam", id);
                    query.setParameter("done", false);
                    return query.executeUpdate() > 0;
                }
        );
    }


    @Override
    public boolean deleteCompletedItems() {
        return tx(session -> session
                .createQuery("delete from model.Item "
                        + "as item where item.done=:done").
                        setParameter("done", true)
                .executeUpdate() > 0);
    }

    @Override
    public boolean delete(int id) {
        return tx(session ->
                session.createQuery("delete from model.Item "
                        + "as item where item.id=:id").
                        setParameter("id", id)
                        .executeUpdate() > 0);
    }


    @Override
    public List<Item> findAll() {
        return tx(
                session -> session.createQuery("from model.Item").list());
    }

    @Override
    public List<Item> findAllActiveItems() {
        return tx(
                session -> session.createQuery("from model.Item as item where item.done=:done")
                        .setParameter("done", false)
                        .list());
    }

    @Override
    public List<Item> findAllCompItems() {
        return tx(
                session -> session.createQuery("from model.Item as item where item.done=:done")
                        .setParameter("done", true).list());
    }


    @Override
    public List<Item> findByName(String name) {
        return tx(
                session -> session
                        .createQuery("from model.Item as item where item.description=:name").
                                setParameter("name", name).list());
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

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        HbmStore hb = new HbmStore();
        System.out.println(hb.isDone(40));
    }
}

