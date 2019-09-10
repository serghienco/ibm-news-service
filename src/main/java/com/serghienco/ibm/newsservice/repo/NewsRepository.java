package com.serghienco.ibm.newsservice.repo;

import com.serghienco.ibm.newsservice.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDate;

@RepositoryRestResource(path = "news", collectionResourceRel = "news")
public interface NewsRepository extends PagingAndSortingRepository<News, Long> {

    @RestResource(exported = false)
    Page<News> findByPublishedAt(LocalDate publishedAt, Pageable pageable);
}
