package com.tutorial.search.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Indexed
@Table(name = "book")
@Document(indexName = "books", type = "book")
public class Book {
    @Id
    private int id;
    
    @FullTextField
    @Field(type = FieldType.Text, name = "name")
    private String name;

    
    @FullTextField
    @Field(type = FieldType.Text, name = "isbn")
    private String isbn;

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }
}
