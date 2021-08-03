package com.example.ordermanagement.xport.client;

import com.example.ordermanagement.domain.valueobjects.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Collections;
import java.util.List;

@Service
public class TicketClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;
//url za ticket catalog, postavuvam serverUrl, restTemplate
    public TicketClient(@Value("${app.ticket-catalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Ticket> findAll() {
        try {
            //kontrolerot na ticket e na /api/tickets, akcijata e get
            return restTemplate.exchange(uri().path("/api/tickets").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Ticket >>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
