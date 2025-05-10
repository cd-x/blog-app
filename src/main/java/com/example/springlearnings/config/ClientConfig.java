package com.example.springlearnings.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .build();
        RestClientTransport restClientTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(restClientTransport);
    }
}
