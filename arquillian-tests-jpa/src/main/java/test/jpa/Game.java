package test.jpa;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;

@Entity
public class Game implements Serializable {
    private Long id;
    private String title;

    public Game() {}

    public Game(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Game@" + hashCode() +
                "[id = " + id + "; title = " + title + "]";
    }
}