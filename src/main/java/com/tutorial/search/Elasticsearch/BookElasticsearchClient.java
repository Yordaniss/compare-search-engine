package com.tutorial.search.Elasticsearch;

import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class BookElasticsearchClient {
    private ElasticsearchClient client;

    public ElasticsearchClient getClient()
    {
        if (client == null) {
            initClient();
        }

        return client;
    }

    private void initClient()
    {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        client = new ElasticsearchClient(transport);
    }
}
