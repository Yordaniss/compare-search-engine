package com.tutorial.search.Elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tutorial.search.Model.Book;

public interface BookElasticsearchRepository extends ElasticsearchRepository<Book, String> {}
