package com.tutorial.search.Elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.tutorial.search.Model.Book;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Configuration
public class BookElasticsearchService {
    BookElasticsearchClient client = new BookElasticsearchClient();

    private static final String BOOK_INDEX = "books";

    public void createBooksIndexBulk(final List<Book> books)
    {
        BulkRequest.Builder builder = new BulkRequest.Builder();

        for (Book book : books)
        {
            builder.operations(op -> op
                .index(index -> index
                    .index(BOOK_INDEX)
                    .id(String.valueOf(book.getId()))
                    .document(book)
                )
            );

            try {
                client.getClient().bulk(builder.build());
            } catch (ElasticsearchException exception) {
                exception.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Book> findBookByIsbn(String query)
    {
        List<Book> books = new ArrayList<>();
        
        try {
            SearchResponse<Book> search = client.getClient().search(s -> s
                .index(BOOK_INDEX)
                .query(q -> q
                    .match(t -> t
                        .field("isbn")
                        .query(query))),
                Book.class);

            List<Hit<Book>> hits = search.hits().hits();
            for (Hit<Book> hit: hits) {
                books.add(hit.source());
            }

            return books;
        } catch (ElasticsearchException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
