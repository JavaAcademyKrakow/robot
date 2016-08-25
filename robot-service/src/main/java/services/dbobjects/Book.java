package services.dbobjects;

import javax.persistence.*;

/**
 * Created by daniel on 25.08.16.
 */
@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String type;

    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return type;
    }

    public void setName(String name) {
        this.type = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
