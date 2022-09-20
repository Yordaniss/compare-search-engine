package com.tutorial.search.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.search.Elasticsearch.BookElasticsearchService;
import com.tutorial.search.HibernateSearch.BookSearchService;
import com.tutorial.search.Model.Book;
import com.tutorial.search.Repository.BookRepository;

@RestController
@RequestMapping(value = "book")
public class BookController {    
    @Autowired
    BookSearchService searchService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookElasticsearchService bookElasticsearchService;

    @GetMapping("/database")
    ResponseEntity<Iterable<Book>> getBooksFromDatabase(@RequestParam("query") String query)
    {
        Iterable<Book> booksFromDatabase = bookRepository.findByIsbn(query);

        return ResponseEntity.ok(booksFromDatabase);
    }

    @GetMapping("/hibernate")
    ResponseEntity<Iterable<Book>> getBooksFromHibernate(Pageable pageable, @RequestParam("query") String query)
    {
        Iterable<Book> booksFromHibernate = searchService.search(pageable, query);

        return ResponseEntity.ok(booksFromHibernate);
    }

    @GetMapping("/elasticsearch")
    ResponseEntity<Iterable<Book>> getBooksFromElasticsearch(@RequestParam("query") String query)
    {
        Iterable<Book> booksFromElasticSearch = bookElasticsearchService.findBookByIsbn(query);

        return ResponseEntity.ok(booksFromElasticSearch);
    }
}
