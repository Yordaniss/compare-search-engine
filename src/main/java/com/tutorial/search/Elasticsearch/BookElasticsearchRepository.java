package com.tutorial.search.Elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tutorial.search.Model.Book;

public interface BookElasticsearchRepository extends ElasticsearchRepository<Book, String> {
    List<Book> findByName(String name);
    List<Book> findByIsbn(String isbn);
}
