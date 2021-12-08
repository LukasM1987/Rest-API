package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private static final String nameFragment = "kodilla";

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.forEach(trelloBoardDto -> {
            Pattern patternFragment = Pattern.compile(nameFragment);
            Matcher matchFragment = patternFragment.matcher(trelloBoardDto.getName().toLowerCase());
            if (trelloBoardDto.getId() != null && trelloBoardDto.getName() != null && matchFragment.find()) {
                System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
            }
        });
    }
}