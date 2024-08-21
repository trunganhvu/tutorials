package com.example.springjparest.manytomany.simplepk.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
@Entity
@Table(name = "writers")
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToMany(mappedBy = "writers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Book> books = new LinkedHashSet<>();

    @Column(nullable = false)
    private String name;

    public void addBook(Book book) {
        this.books.add(book);
        book.getWriters().add(this);
    }

    public void addBooks(Set<Book> books) {
        this.books.clear();
        this.books.addAll(books);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.getWriters().remove(this);
    }
}
