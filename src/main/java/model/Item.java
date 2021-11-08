package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private boolean done;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> categories = new ArrayList<>();

    public Item() {

    }

    public Item(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Item(String description, Date created, User user) {
        this.description = description;
        this.created = new Date(System.currentTimeMillis());
        this.done = false;
        this.user = user;
    }

    public Item(String description, Date created, boolean done, User user) {
        this.description = description;
        this.created = new Date(System.currentTimeMillis());
        this.done = false;
        this.user = user;
    }

    public Item(String description) {
        this.description = description;
        created = new Date(System.currentTimeMillis());
        done = false;
    }

    public Item(String description, boolean done) {
        this.description = description;
        this.done = done;
        created = new Date(System.currentTimeMillis());
    }

    public Item(String description, Date created) {
        this.description = description;
        this.created = created;
    }

    public Item(String description, Date created, boolean done) {
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Item(int id, String description, Date created, boolean done) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Item(String description, User user, List<Category> categories) {
        this.description = description;
        this.user = user;
        this.categories = categories;
    }

    public Item(String description, Date created, boolean done, User user, List<Category> categories) {
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
        this.categories = categories;
    }

    public Item(int id, String description, Date created, boolean done, User user, List<Category> categories) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
        this.categories = categories;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", created=" + created
                + ", done=" + done
                + ", user=" + user
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done, user);
    }
}
