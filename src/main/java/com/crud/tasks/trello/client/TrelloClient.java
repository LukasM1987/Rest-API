package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;


@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.username}")
    private String userName;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;

    public List<TrelloBoardDto> getTrelloBoards() {

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildURL(), TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    private URI buildURL() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + userName + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .build().encode().toUri();
        return url;
    }
}