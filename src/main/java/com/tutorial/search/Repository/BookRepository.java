package com.tutorial.search.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tutorial.search.Model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer>
{
    List<Book> findByName(String name);    
    List<Book> findByIsbn(String isbn);    
}
