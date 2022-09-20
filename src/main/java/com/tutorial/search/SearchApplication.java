package com.tutorial.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tutorial.search.Elasticsearch.BookElasticsearchRepository;

@EnableElasticsearchRepositories(basePackageClasses = BookElasticsearchRepository.class)
@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
	type = FilterType.ASSIGNABLE_TYPE, value = BookElasticsearchRepository.class
))
@SpringBootApplication
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

}
