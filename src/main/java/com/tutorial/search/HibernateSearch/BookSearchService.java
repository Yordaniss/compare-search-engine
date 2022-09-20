package com.tutorial.search.HibernateSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.search.Model.Book;

@Service
public class BookSearchService {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Iterable<Book> search(Pageable pageable, String query)
    {
        SearchSession session = Search.session(entityManager);

        SearchResult<Book> result = session.search(Book.class)
            .where(
                    f -> f.match().
                    fields("name", "isbn").
                    matching(query)
                )
            .fetch((int) pageable.getOffset(), pageable.getPageSize())
        ;
        return result.hits();
    }
}
