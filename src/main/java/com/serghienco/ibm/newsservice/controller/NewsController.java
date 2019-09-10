package com.serghienco.ibm.newsservice.controller;

import com.serghienco.ibm.newsservice.domain.News;
import com.serghienco.ibm.newsservice.domain.NewsProjection;
import com.serghienco.ibm.newsservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RepositoryRestController
@ExposesResourceFor(News.class)
public class NewsController {

    @Autowired
    private NewsService service;

    @Autowired
    private PagedResourcesAssembler<NewsProjection> assembler;

    @RequestMapping(method = RequestMethod.POST, path = "/news", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PersistentEntityResource> createNews(
            @RequestBody @Valid News news,
            PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toResource(service.save(news)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/news/recommended/{date}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getRecommendedNews(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable(name = "date") LocalDate date,
            Pageable pageable) {
        Page<News> news = service.get(date, pageable);
        Page<NewsProjection> newsProjections = news.map(n -> new NewsProjection(n));
        PagedResources<Resource<NewsProjection>> resources = assembler.toResource(newsProjections);
        return ResponseEntity.ok(resources);
    }
}
