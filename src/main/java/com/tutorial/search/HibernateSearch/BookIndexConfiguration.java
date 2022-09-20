package com.tutorial.search.HibernateSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.search.Model.Book;

@Component
public class BookIndexConfiguration implements CommandLineRunner
{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public void run(String ...args) throws Exception
    {
        SearchSession searchSession = Search.session(entityManager);
        MassIndexer indexer = searchSession.massIndexer(Book.class);
        indexer.startAndWait();
    }
} 