package com.serghienco.ibm.newsservice.controller;

import com.serghienco.ibm.newsservice.domain.News;
import com.serghienco.ibm.newsservice.service.NewsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RepositoryRestController
public class NewsController {

    private final NewsService service;

    @Autowired
    public NewsController(@NonNull NewsService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/news")
    public ResponseEntity<PersistentEntityResource> createNews(
            @RequestBody @Valid News news,
            PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toResource(service.save(news)));
    }
}
